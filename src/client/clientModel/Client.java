package client.clientModel;

import client.clientController.ClientControl;
import client.clientView.panel.MainFrame;
import server.serverModel.Logger;
import xcommon.Message;
import xcommon.User;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    private Socket socket;
    private User user;

    private MainFrame view;
    private Contact contact;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private ClientControl clientControl;

    // Constructor
    public Client(MainFrame view, Socket socket, User user) {
        try {
            this.socket = socket;
            this.view = view;
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            this.user = user;
            new Read().start();
        } catch (IOException e) {
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
            if (view.getTxtMsg() != null) {
                objectOutputStream.writeObject(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addMessage(Object message) {
        view.updateMessageScreen(message);
    }

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
                    if (obj instanceof Logger) {
                        Logger logger = (Logger) obj;
                        JList<String> contacts = logger.getContacts();
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
                        System.exit(0);
                    } catch (IOException e) {
                        //e.printStackTrace();
                    }

                }
            }
        }
    }
}
