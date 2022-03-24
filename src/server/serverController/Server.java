package server.serverController;

import server.serverModel.Logger;
import server.serverView.ServerGUI;
import xcommon.Message;
import xcommon.Time;
import xcommon.Unsend;
import xcommon.User;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {

    private ServerSocket serverSocket;
    private ClientHandler clientHandler;
    private static User user;
    private Unsend unsend;
    private ServerGUI serverGUI;
    private Logger logger;

    private static ArrayList<ClientHandler> clientHashList; //Håller koll på alla klienter.
    private static HashMap<User, ClientHandler> hashMapClients =  new HashMap<>();
    private static ArrayList<User> clients = new ArrayList<>();

    // Main method
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(4434);
        Server server = new Server(serverSocket);
        server.startServer();
    }

    // Constructor
    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        this.unsend = new Unsend();
        this.clientHashList = new ArrayList<ClientHandler>();
        this.serverGUI = new ServerGUI();
    }

    // Start the server
    public void startServer() {
        try {
            while (!serverSocket.isClosed()) {
                Socket client = serverSocket.accept();
                clientHandler = new ClientHandler(client, this);
                Thread thread = new Thread(clientHandler);
                clientHandler.broadcastAllUsers();
                thread.start();
                System.out.println("[CONSOLE-LOG] Accepts the client. New thread for every client!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ClientHandler inner class
    public static class ClientHandler implements Runnable {
        private Socket socket;
        private ObjectOutputStream objectOutputStream;
        private ObjectInputStream objectInputStream;
        private User clientUser;
        private Server server;

        // Constructor
        public ClientHandler(Socket socket, Server server) {
            System.out.println("==================== ClientHandler Constructor ====================");
            System.out.println("[CONSOLE-LOG] New client joins.");
            try {
                this.server = server;
                objectInputStream = new ObjectInputStream(socket.getInputStream());
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                user = (User) objectInputStream.readObject();
                clients.add(user);
                hashMapClients.put(user, this);

                showClient("Server: " + user.getUserName() + " joined the server. [" + Time.getTime() + "]");
                logger("Server: " + user.getUserName() + " joined the server. " + " % " + Time.getTime() + "\n");
                this.socket = socket;
                this.clientUser = user;
                server.clientHashList.add(this);
                offlineMessage(clientUser);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        // Offline message
        public void offlineMessage(User user){
            if (server.unsend.get(user.getUserName()) != null) {
                if (server.unsend.get(user.getUserName()).size() != 0) {
                    for (int i = 0; i < server.unsend.get(user.getUserName()).size(); i++) {
                        System.out.println("[CONSOLE-LOG] Unsent messages found.");
                        sendUnsent(server.unsend.get(user.getUserName()).get(i));
                    }
                    server.unsend.clear(user);
                }
            } else {
                System.out.println("[CONSOLE-LOG] Unsent messages not found.");
            }
        }

        // Send to all users
        public void broadcastAllUsers() throws IOException {
            System.out.println("[CONSOLE-LOG] BroadcastAllUsers.");
            for (Map.Entry<User, ClientHandler> entry : hashMapClients.entrySet()){
                entry.getValue().updateClients(clients);
            }
        }

        // Remove clientHandler
        public void removeClientHandler() {
            System.out.println("[CONSOLE-LOG] Removes a clienthandler.");
            clientHashList.remove(this);
            hashMapClients.remove(this.clientUser, this);
            clients.remove(this.clientUser);
            for (Map.Entry<User, ClientHandler> entry : hashMapClients.entrySet()){
                //System.out.println(entry.getValue());
                entry.getValue().sendToClient("Server: " + clientUser.getUserName() + " left the server. [" + Time.getTime() + "]");
                logger("Server: " + clientUser.getUserName() +" left the server. " + " % " + Time.getTime() + "\n");
                entry.getValue().updateClients(clients);
            }
        }

        // Run method
        @Override
        public void run() {
            System.out.println("======================= Run method =======================");
            System.out.println("[CONSOLE-LOG] Run method runs.");
            if (fileExist()){
                try (FileInputStream fis = new FileInputStream("./contacts/"+user.getUserName()+".dat");
                     ObjectInputStream ois = new ObjectInputStream(fis)) {
                    Logger contacts = (Logger) ois.readObject();
                    objectOutputStream.writeObject(contacts);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

            while (!Thread.interrupted()) {
                try {
                    Object obj = objectInputStream.readObject(); // Varför inträffar det här felet? o.O /Sossio

                    if (obj instanceof Message) {
                        System.out.println("[CONSOLE-LOG] Is instance of message.");
                        Message msg = (Message) obj;
                        System.out.println(msg.getText() + " 1");
                        System.out.println(msg.getText() + " 2");
                        if (msg.getText().charAt(0) == '@') { // om texten är tom och du skickar bild, får du fel här. /Sossio
                            System.out.println("[CONSOLE-LOG] Contains '@': sending a private message!");
                            if (msg.getText().contains(" ")) {
                                int firstSpace = msg.getText().indexOf(" ");
                                String userPrivate = msg.getText().substring(1, firstSpace);
                                privateMessage(userPrivate, msg);
                                logger(clientUser.getUserName() + ": " + msg.getText() + " % " + Time.getTime() + "\n");
                            }
                        } else {
                            System.out.println("[CONSOLE-LOG] Message has not '@'. Sends to all users then.");
                            showClient(msg);
                            logger(clientUser.getUserName() + ": " + msg.getText() + " % " + Time.getTime() + "\n");
                        }
                    }

                    if (obj instanceof Logger) {
                        System.out.println("[CONSOLE-LOG] Is instance of Log.");
                        Logger log = (Logger) obj;
                        log.logContacts(log, clientUser);
                    }
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println("[CONSOLE-LOG] Inside run-method, catched!");
                    e.printStackTrace();
                    removeClientHandler();
                    break;
                }
            }
        }

        // File exists
        private boolean fileExist() {
            File f = new File("./contacts/"+user.getUserName()+".dat");
            if(f.exists() && !f.isDirectory()) {
                System.out.println("[CONSOLE-LOG] Exists in logs.");
                return true;
            }
            else {
                System.out.println("[CONSOLE-LOG] Does not exist in logs.");
            }
            return false;
        }

        // Send a private message
        public void privateMessage(String user, Message msg) throws IOException {
            for (ClientHandler client : clientHashList) {
                if (client.clientUser.getUserName().equals(user) && !clientUser.getUserName().equals(user)) {
                    System.out.println("[CONSOLE-LOG] User is online.");
                    client.objectOutputStream.writeObject(msg);
                    objectOutputStream.writeObject(msg);
                } else {
                    System.out.println("[CONSOLE-LOG] User is not online.");
                    server.unsend.put(user, msg);
                }
            }
        }

        // Unsent message
        private void sendUnsent(Message message){
            try {
                objectOutputStream.writeObject(message);
                System.out.println("[CONSOLE-LOG] Unsent message.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        // Take out the spc user from the hashmap client list
        private void showClient(Object msg) {
            System.out.println("[CONSOLE-LOG] Show clients.");
            for (User user : clients){
                hashMapClients.get(user).sendToClient(msg);
            }
        }

        // Send to all clients which is active
        public void sendToClient(Object msg){
            try {
                objectOutputStream.writeObject(msg);
                objectOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void updateClients(ArrayList<User> users) {
            try {
                for (User item : users){
                    System.out.println("[CONSOLE-LOG] Update clients!");
                    objectOutputStream.writeObject(item);
                    objectOutputStream.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * Log all traffic into an output text
         * @param msg is the log messages
         */
        private void logger(String msg) {
            try {
                System.out.println("[CONSOLE-LOG] Writes to a log file...");
                FileWriter fw = new FileWriter("./log/log.txt", true);
                fw.write(msg);
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

