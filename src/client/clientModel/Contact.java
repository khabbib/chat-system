package client.clientModel;

import client.clientView.ContactGUI;
import client.clientView.panel.MainFrame;
import xcommon.User;

import javax.swing.*;

public class Contact {

    private User user;
    private ContactGUI contactGUI;

    private String[] contactUsers;
    private int nbrOfConactList = 20; // 20 contacts to each user
    private int nbrOfCOntactListCounter = 0;

    public Contact(MainFrame mainFrame, User user) {
        contactUsers = new String[nbrOfConactList];
        this.user = user;
        contactGUI = new ContactGUI(mainFrame, user, contactUsers);
    }

    public void showFrame() {
        contactGUI.setVisible(true);
    }

    public void addUser(User user) {
        nbrOfCOntactListCounter++;
        if(nbrOfCOntactListCounter <= nbrOfConactList) {
            DefaultListModel listModel = new DefaultListModel();
            listModel.setSize(20);
            contactGUI.showUsers(listModel);
        } else {
            JOptionPane.showMessageDialog(null, "Only 20 contacts per user!");
        }

    }

    public void setContactsLog(JList<String> contacts) {
        contactGUI.setContactsLog(contacts);
    }

    public JList<String> getContacts() {
        return contactGUI.getContacts();
    }
}
