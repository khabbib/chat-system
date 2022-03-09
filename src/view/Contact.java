package view;

import model.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Contact {

    //private User user;

    JFrame frame;
    String[] contactUsers = new String[20]; // 20 contacts to each user
    int nbrOfUsers = 1;
    JList<String> contacts;

    public Contact() {
        //this.user = user;
        contacts = new JList<String>(contactUsers);

        frame = new JFrame("ContactList");
        frame.setBounds(200, 50, 300, 400);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(false);
    }

    public void contacts() {
        contacts.setBounds(0,0,300, 400);
        contacts.setOpaque(true);
        contacts.setBackground(new Color(220,220,220));
        contacts.setVisible(true);
        frame.add(contacts);
    }

    public void showFrame() {
        frame.setVisible(true);
    }

    public void addUser(User user) {
        for(int i = 0; i < nbrOfUsers; i++){
            if(contactUsers[i] == null){
                contactUsers[i] = String.valueOf(user);
                System.out.println(Arrays.toString(contactUsers));
            }
        }
        nbrOfUsers++;
        setContacts(contactUsers);
        System.out.println(nbrOfUsers);
    }

    public void setContacts(String[] contacts) {
        this.contacts.setListData(contacts);
    }

    public void setContacts(JList<String> contacts) {
        this.contacts = contacts;
    }

    public Object getContacts() {
        return contacts;
    }
}
