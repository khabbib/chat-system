package controller;

import model.*;
import view.*;
import view.utilities.ButtonType;
import view.contact.Contact;
import view.panel.MainFrame;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Client {

    private Socket socket;
    private User user;

    private MainFrame view;
    private Contact contact;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;

    // Constructor
    public Client(Socket socket, User user) {
        this.user = user;
        view = new MainFrame(this);
        contact = new Contact(view);
        contact.contacts();

        try {
            this.socket = socket;
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            new Read().start();
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

    // Send message
    public void sendMessage(Message msg) {
        try {
            if ((view.getTxtMsg() != null) && (msg != null)) {
                objectOutputStream.writeObject(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Button pressed
    public void buttonPressed(ButtonType button) {

        switch (button) {

            case ContactList:
                System.out.println("Button 'Contact' is clicked!");
                contact.showFrame();
                break;

            case ContactAdd:
                System.out.println("Button 'Add contact' is clicked!");

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
                System.out.println("Button 'File' is clicked!");
                System.out.println("[-!-] Attention: This button is modified in SouthPanel!");
                break;

            case Send:
                System.out.println("Button 'Send' is clicked!");

                Message msg = new Message(view.getTxtMsg(),user.getUserName(),null, view.getImgIcon(), Time.getTime());
                sendMessage(msg);
                view.setTxtMsg("");
                view.setImageMsgArea(null);
                view.setImgMsg(null);
                view.setImageToNull(null);

                break;

            case Logout:
                System.out.println("Button 'Logout' is clicked!");
                try {
                    objectOutputStream.writeObject(new Log(contact.getContacts(), user.getUserName()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Bye bye!");
                System.exit(0);
                break;
        }
    }

    // Check that we have gotten an index
    private boolean validateIndex(int index) {
        boolean ok = true;
        if (index < 0) {
            JOptionPane.showMessageDialog(null, "Select user from the active list!");
            ok = false;
        }
        return ok;
    }

    // Close everything
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

    // Main method
    public static void main(String[] args) throws IOException {
        //Login login = new Login();
        LoginGui lg = new LoginGui();
        lg.setVisible(true);
        while (!lg.isDone()){System.out.println("Connection waiting to login...");}
        Socket socket = new Socket("localhost", 4433);
        System.out.println("Connection check...");
        System.out.println("2%");
        System.out.println("34%");
        System.out.println("78%");
        System.out.println("95%");
        System.out.println("100%");
        new Client(socket, new User(lg.getUserName(), lg.getImageIcon()));
        System.out.println("Connection is completed!");
    }

    public String getUname() {
        return user.getUserName();
    }

    public ImageIcon getImageIcon() {
        return user.getUserImage();
    }

    // Server listener
    private class Read extends Thread {
        ArrayList<User> listUser = new ArrayList<User>();
        Object obj;
        public void run() {
            while(true){
                try {
                    obj = objectInputStream.readObject();
                    if (obj instanceof User){
                        listUser.clear();
                        listUser.add((User)obj);
                        view.setList((User)obj);
                    }
                    if (obj instanceof Log) {
                        Log log = (Log) obj;
                        JList<String> contacts = log.getContacts();
                        contact.setContactsLog(contacts);
                    }
                    else if(obj instanceof Message){
                        System.out.println("Message received!");
                        addMessage(obj);
                    }
                    else if (obj instanceof String) {
                        addMessage(obj);
                        if (((String) obj).contains(":") && ((String)obj).contains("left")){
                            String[] str = ((String) obj).split(":");
                            String userLeft = str[0];
                            for (User users : listUser){
                                listUser.removeIf(item -> (users.getUserName().equals(userLeft)));
                                break;
                            }
                            view.getMainP().getcRPanel().addContacts(listUser);
                        }
                    }
                }
                catch (IOException | ClassNotFoundException ex) {
                    try {
                        objectInputStream.close();
                        objectInputStream.close();
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.exit(0);
                }
            }
        }
    }
}