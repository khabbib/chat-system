package model.client;

import model.Login;
import model.Message;
import model.User;
import view.ButtonType;
import view.Contact;
import view.MainFrame;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class Client {

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private PrintWriter output;
    private User user;
    private Thread read;

    private MainFrame view;
    private Contact contact;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;

    public Client(Socket socket, User user) {
        view = new MainFrame(this);
        contact = new Contact();
        contact.contacts();

        try {
            this.socket = socket;
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            System.out.println("connected user");
            this.user = user;
            //user.setStreamOut(new PrintStream(socket.getOutputStream()));
            new Read().start();
            //read.start();
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
            e.printStackTrace();
        }

        try {
            objectOutputStream.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(Message msg) {
        try {
            if (view.getTxtMsg() != null) {
                System.out.println("sfssfsfsfd");
                objectOutputStream.writeObject(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buttonPressed(ButtonType button) {

        switch (button) {

            case ContactList:
                //System.out.println("Button 'Contact' is clicked!");
                //contact.showFrame();
                contact.showFrame();
                break;

            case ContactAdd:
                //System.out.println("Button 'Add contact' is clicked!");

                int index = view.getUserListIndex();

                if(validateIndex(index)) {

                    User user = view.getUserAt(index);

                    if(user != null) {

                        contact.addUser(user);
                        System.out.println("Added!");
                    }
                }

                break;

            case File:
                //System.out.println("Button 'File' is clicked!");
                //System.out.println("[-!-] Attention: This button is modified in SouthPanel!");
                break;

            case Send:
                //System.out.println("Button 'Send' is clicked!");

                // Text Area
                //view.setTxtScreen(view.getTxtMsg());
                //view.setTxtMsg("");

                // JList
                Message msg = new Message(view.getTxtMsg(),user.getUserName(),"alla" , view.getImgIcon());
                sendMessage(msg);
                view.setTxtMsg("");
                System.out.println("helllooooooooo");
                break;

            case Logout:
                //System.out.println("Button 'Logout' is clicked!");
                System.out.println("Bye bye!");
                System.exit(0);
                break;
        }
    }

    //Check that we have gotten an index
    private boolean validateIndex(int index) {
        boolean ok = true;
        if (index < 0) {
            JOptionPane.showMessageDialog(null, "Select user from the active list!");
            ok = false;
        }
        return ok;
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
    public void addMessage(Object message) {
        view.updateMessageScreen(message);

    }
    // main
    public static void main(String[] args) throws IOException {
        Login login = new Login();
        login.setVisible(true);
        System.out.println("Connection checks...");
        while (!login.isDone()){
            System.out.println("waiting...");
        } // tar tid
        Socket socket = new Socket("localhost", 4433);
        Client client = new Client(socket, login.getUser());
        System.out.println("Connection is completed!");
    }

    ArrayList<User> listUser = new ArrayList<User>();
    private int nbr;

    class Read extends Thread { // server listener
        Object obj;
        public void run() {
            while(true){
                try {
                    obj = objectInputStream.readObject();
                    if (obj instanceof User){
                        listUser.add((User) obj);
                        System.out.println("user");
                        view.setList(((User) obj).getUserName());
                    }
                    else if(obj instanceof Message){
                        System.out.println("Message received");
                        addMessage(obj);

                        //view.updateMessageScreen(getMessageString());
                    }
                    else if (obj instanceof String) {
                        System.out.println("message is a string");
                        addMessage(obj);
                        if (obj.equals("userLeft")) {
                            listUser.clear();
                        }
                }
                }
                catch (IOException | ClassNotFoundException ex) {
                    //ex.printStackTrace();
                }
            }
        }
    }
}