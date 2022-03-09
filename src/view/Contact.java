package view;

import javax.swing.*;
import java.awt.*;

public class Contact {
    JFrame frame = new JFrame("Contact List");
    JList<String> contactsJList = new JList<>();

    public Contact(String[] contacts){
        frame.setBounds(200, 50, 300, 400);
        frame.setLayout(null);
        frame.setVisible(true);

        contactsJList.setBounds(0,0,300, 400);
        contactsJList.setOpaque(true);
        contactsJList.setBackground(new Color(220,220,220));
        contactsJList.setVisible(true);

        contactsJList.setListData(contacts);

        frame.add(contactsJList);
    }

    public void setContactsJList(String[] contactsJList) {
        this.contactsJList.setListData(contactsJList);
    }

    public Object getContactsJList() {
        return contactsJList;
    }
}
