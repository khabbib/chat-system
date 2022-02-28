package view;

import javax.swing.*;
import java.awt.*;

public class Contact {
    JFrame frame = new JFrame("Contact List");
    JList<String> contacts = new JList<>();

    public Contact(){
        frame.setBounds(200, 50, 300, 400);
        frame.setLayout(null);
        frame.setVisible(true);

        contacts.setBounds(0,0,300, 400);
        contacts.setOpaque(true);
        contacts.setBackground(new Color(220,220,220));
        contacts.setVisible(true);

        frame.add(contacts);
    }

    public void setContacts(String[] contacts) {
        this.contacts.setListData(contacts);
    }

    public Object getContacts() {
        return contacts;
    }
}
