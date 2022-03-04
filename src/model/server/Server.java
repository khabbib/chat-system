package model.server;

import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import model.User;
import model.client.Client;

import javax.swing.*;

public class Server extends JFrame {

    private JFrame frame;
    private JLabel logger;

    private ServerSocket serverSocket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    private ArrayList<User> clients;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(4433);
        Server server = new Server(serverSocket);
        server.startServer();
    }

    public void broadcastAllUsers() {
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < clients.size(); i++) {
            strings.add(clients.get(i).getUserName());
        }
        for (User client : clients) {
            client.getStreamOut().println(strings);
        }
    }

    public Server(ServerSocket serverSocket) {
        super("Chat Server");
        this.serverSocket = serverSocket;

        this.clients = new ArrayList<User>();
        this.frame = new JFrame();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocation(0, 0);
        this.setSize(300, 400);
        this.setLayout(null);
        this.setVisible(true);

        logger = new JLabel("Logger");
        logger.setLocation(0, 0);
        logger.setSize(250, 20);
        this.add(logger);
    }

    public void startServer() throws ClassNotFoundException {
        try {
            while (!serverSocket.isClosed()) {
                Socket client = serverSocket.accept();
                logger.setText("A new client has been connected.");
                ObjectInputStream objectInputStream = new ObjectInputStream(client.getInputStream());
                User newUser = (User) objectInputStream.readObject();
                clients.add(newUser);
                System.out.println(clients.get(0).getUserName());
                newUser.setStreamOut(new PrintStream(client.getOutputStream()));
                ClientHandler clientHandler = new ClientHandler(client, newUser, this);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Denna metod kommer stänga ner server socket om ett problem skulle uppstå
    public void closeServerSocket() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>(); //Håller koll på alla klienter. Låter oss  också kunna strema meddelande så att alla klienter kan se det
        private Socket socket; //Socket som kommer från Server klassen, är nödvändigt för att hålla kommunikation med client
        private BufferedReader bufferedReader; //Denna instans används för att läsa data från klienten
        private BufferedWriter bufferedWriter; //Denna instans används för att skicka data till klienten
        private User clientUser;
        private Server server;

        public ClientHandler(Socket socket, User user, Server server) {
            try {
                this.socket = socket;
                this.clientUser = user;
                this.server = server;
                this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                server.broadcastAllUsers();
                clientHandlers.add(this);

                broadcastMessage("Server: " + user.getUserName() + " har deltagit i chatten!");
            } catch (IOException e) {
                Client.closeEverything(socket, bufferedReader, bufferedWriter);
                e.printStackTrace();
            }
        }

        public void broadcastMessage(String messageTosend) {
            for (ClientHandler clientHandler : clientHandlers) {
                try {
                    if (!clientHandler.clientUser.getUserName().equals(clientUser.getUserName())) {
                        clientHandler.bufferedWriter.write(clientUser.getUserName() + ": " + messageTosend);
                        clientHandler.bufferedWriter.newLine();
                        clientHandler.bufferedWriter.flush();
                    }
                } catch (IOException e) {
                    Client.closeEverything(socket, bufferedReader, bufferedWriter);
                    e.printStackTrace();
                }
            }
        }

        public void removeClienthandler() {
            clientHandlers.remove(this);
            broadcastMessage("Server: " + clientUser.getUserName() + " har lämnat chatten");
        }

        @Override
        public void run() {
            while (socket.isConnected()) {
                try {
                    String messageFromClient = bufferedReader.readLine();
                    broadcastMessage(messageFromClient);
                    Thread.sleep(1000);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

