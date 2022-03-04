package model.client;

import model.Login;
import model.User;
import model.server.Server;
import view.ButtonType;
import view.Contact;
import view.MainFrame;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class Client {

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private User user;
    private Thread read;

    private MainFrame view;

    public Client(Socket socket, User user) {
        view = new MainFrame(this);
        try {
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            this.user = user;
            user.setStreamOut(new PrintStream(socket.getOutputStream()));

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(user);

            read = new Read();
            read.start();

        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
            e.printStackTrace();
        }
    }

    public void listenForMessage() {
        // Listen for messages
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msgFromGruppChat;
                while (socket.isConnected()) {
                    try {
                        msgFromGruppChat = bufferedReader.readLine();
                        //System.out.println(msgFromGruppChat);
                        addMessage(msgFromGruppChat);
                        view.setTxtMsg("");
                        view.updateMessageScreen(getMessageString());
                    } catch (IOException e) {
                        closeEverything(socket, bufferedReader, bufferedWriter);
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void sendMessage() {
        try {
            while (socket.isConnected()) {

                if(view.getTxtMsg() != null){bufferedWriter.write(view.getTxtMsg());}
                //bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch(IOException e){
            closeEverything(socket, bufferedReader, bufferedWriter);
            e.printStackTrace();
        }
    }

    public void buttonPressed(ButtonType button) {
        switch (button) {
            case ContactList:
                System.out.println("Button 'Contact' is clicked!");
                Contact contact = new Contact();
                break;

            case ContactAdd:
                System.out.println("Button 'Add contact' is clicked!");
                break;

            case File:
                System.out.println("Button 'File' is clicked!");
                System.out.println("[-!-] Attention: This button is modified in SouthPanel!");
                break;

            case Send:
                System.out.println("Button 'Send' is clicked!");

                // Text Area
                //view.setTxtScreen(view.getTxtMsg());
                //view.setTxtMsg("");

                // JList
                addMessage(view.getTxtMsg());
                view.setTxtMsg("");
                view.updateMessageScreen(getMessageString());

                break;

            case Logout:
                System.out.println("Button 'Logout' is clicked!");
                System.out.println("Bye bye!");
                //Server.removeClienthandler();
                System.exit(0);
                break;
        }
    }

    public static void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter)  {
        try {
            if (bufferedReader != null) {bufferedReader.close();}
            if (bufferedWriter != null) {bufferedWriter.close();}
            if (socket != null) {socket.close(); }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String[] messages = new String[100];
    private int nbrOfMessages = 0;

    public void addMessage(String message) {

        for(int i = 0; i <= nbrOfMessages; i++){
            if(messages[i] == null){
                messages[i] = message;
            }
        }
        nbrOfMessages++;
    }

    public String[] getMessageString() {
        String[] infoStrings = new String[nbrOfMessages];
        for(int i = 0; i < messages.length; i++){
            if(messages[i] != null){
                infoStrings[i] = messages[i].toString();
            }
        }
        return infoStrings;
    }

    // main
    public static void main(String[] args) throws IOException {
        Login login = new Login();
        login.setVisible(true);

        while(!login.isDone()){
            System.out.println();
        }

        Socket socket = new Socket("localhost", 4433);
        Client client = new Client(socket, login.getUser());

        System.out.println("Connection checks...");

        client.listenForMessage();
        client.sendMessage();

        System.out.println("Connection is completed!");
    }

    class Read extends Thread {
        public void run() {
            String message;
            while(!Thread.currentThread().isInterrupted()){
                try {
                    message = bufferedReader.readLine();
                    if(message != null){
                        if (message.charAt(0) == '[') {
                            message = message.substring(1, message.length()-1);
                            ArrayList<String> listUser = new ArrayList<String>(Arrays.asList(message.split(", ")));
                            view.setList(listUser);
                        }else{
                            addMessage(message);
                            view.setTxtMsg(message);
                            view.updateMessageScreen(getMessageString());
                        }
                    }
                }
                catch (IOException ex) {
                    System.err.println("Failed to parse incoming message");
                }
            }
        }
    }
}