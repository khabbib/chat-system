package model.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

import model.Message;
import model.User;
import view.panel.SouthPanel;

import javax.swing.*;

public class Server extends JFrame {
    private JFrame frame;
    private static JLabel logger;
    private ServerSocket serverSocket;
    private static User user;


    public static ArrayList<ClientHandler> clientH_HashList; //Håller koll på alla klienter. Låter oss  också kunna strema meddelande så att alla klienter kan se det
    private static HashMap<User, ClientHandler> hashClients =  new HashMap<>();
    private static ArrayList<User> clients = new ArrayList<>();

    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;
    ClientHandler clientHandler;




    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(4433);
        Server server = new Server(serverSocket);
        server.startServer();
    }



    public Server(ServerSocket serverSocket) {
        super("Chat Server");
        this.serverSocket = serverSocket;

        this.clientH_HashList = new ArrayList<ClientHandler>();
        this.frame = new JFrame();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocation(0, 0);
        this.setSize(300, 400);
        this.setLayout(null);
        this.setVisible(true);

        logger = new JLabel("Logger");
        logger.setLocation(0, 0);
        logger.setSize(250, 250);
        System.out.println("Gui made");
        this.add(logger);
    }

    public void startServer() throws ClassNotFoundException {
        try {
            while (!serverSocket.isClosed()) {
                Socket client = serverSocket.accept();
                System.out.println("new client");
                 clientHandler = new ClientHandler(client, this);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeUser(User user){
        this.clients.remove(user);
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

    public static class ClientHandler implements Runnable {
        private Socket socket; //Socket som kommer från Server klassen, är nödvändigt för att hålla kommunikation med client
        private ObjectOutputStream objectOutputStream; //Denna instans används för att läsa data från klienten
        private ObjectInputStream objectInputStream; //Denna instans används för att skicka data till klienten
        private User clientUser;
        private Server server;

        public ClientHandler(Socket socket, Server server) {
            try {
                this.server = server;
                objectInputStream = new ObjectInputStream(socket.getInputStream());
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                user = (User) objectInputStream.readObject();
                clients.add(user);
                hashClients.put(user, this);
                //user.setStreamOut(new PrintStream(socket.getOutputStream()));
                //user.setStreamIn(socket.getInputStream());

                broadcastAllUsers();
                showClient("Server: " + user.getUserName() + " Joined");
                this.socket = socket;
                this.clientUser = user;
                logger.setIcon(new ImageIcon(user.getUserImage().getImage()));
                server.clientH_HashList.add(this);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        // bara users
        public void broadcastAllUsers() throws IOException {
            System.out.println("brodcusting all");
            for (Map.Entry<User, ClientHandler> entry : hashClients.entrySet()){
                entry.getValue().updateClients(clients);
            }
        }

        // en message i taget
        public void broadcastMessage(String messageTosend) throws IOException {
            for (User client : clients) {
                System.out.println("brodcusting");
                objectOutputStream.writeObject(messageTosend);
                //client.getStreamOut().println(messageTosend);
            }
        }

        public void removeClienthandler() throws IOException {
            server.clientH_HashList.remove(this);
            broadcastMessage("Server: " + clientUser.getUserName() + " har lämnat chatten");
        }

        @Override
        public void run() {
            System.out.println("running clienthandler");
            while (true){
                try {
                    Message msg = (Message) objectInputStream.readObject();
                    showClient(msg); //show message to a specific person
                    //broadcastMessage(clientUser.getUserName()+": "+msg.getText());
                }catch (IOException | ClassNotFoundException e){
                    //e.printStackTrace();
                }
            }
        }


        // take out the spc user from the hashmap client list
        private void showClient(Object msg) {
            System.out.println(msg.toString() + " brodcust msg");
            for (User user : clients){
                System.out.println("brodcust is sending");
                hashClients.get(user).sendToClient(msg);
            }
        }
        // send to all clients which are actives a message
        public void sendToClient(Object msg){
            try {
                objectOutputStream.writeObject(msg);
                System.out.println("write the msg to strom");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void updateClients(ArrayList<User> users) {
            try {
                for (User item : users){
                    System.out.println("update client");
                    objectOutputStream.writeObject(item);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

