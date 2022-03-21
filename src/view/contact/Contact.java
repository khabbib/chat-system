package view.contact;

import model.User;
import view.panel.MainFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Contact {

    //private User user;
    private MainFrame mainFrame;

    JFrame frame;
    String[] contactUsers;
    int nbrOfUsers = 1;
    int nbrOfConactList = 20; // 20 contacts to each user
    int nbrOfCOntactListCounter = 0;
    JList<String> contacts;

    public Contact(MainFrame mainFrame) {
        contactUsers = new String[nbrOfConactList];

        //this.user = user;
        contacts = new JList<String>(contactUsers);

        this.mainFrame = mainFrame;
        frame = new JFrame("ContactList: " + mainFrame.getUname());
        frame.setBounds(200, 50, 300, 500);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(false);
    }

    public void contacts() {
        contacts.setBounds(0,0,300, 500);
        contacts.setBorder(new EmptyBorder(10,10, 10, 10));
        contacts.setOpaque(true);
        contacts.setBackground(new Color(220,220,220));
        contacts.setVisible(true);
        frame.add(contacts);
        contacts.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()){
                    JList source = (JList)e.getSource();
                    mainFrame.setTxtMsg(("@" + source.getSelectedValue().toString()));
                }
            }
        });
    }

    public void showFrame() {
        frame.setVisible(true);
    }

    public void addUser(User user) {
        nbrOfCOntactListCounter++;

        if(nbrOfCOntactListCounter <= nbrOfConactList) {
            DefaultListModel listModel = new DefaultListModel();
            listModel.setSize(20);

            for(int i = 0; i <= 20; i++){
                if(contacts.getModel().getElementAt(i) == null){
                    System.out.println("null");
                    listModel.add(i,user.getUserName());
                    break;
                }
                else{
                    System.out.println("not null");
                    listModel.add(i,contacts.getModel().getElementAt(i));
                }
            }
            setContacts(listModel);
        } else {
            JOptionPane.showMessageDialog(null, "Only 20 contacts per user!");
        }


    }

    public void setContacts(DefaultListModel model) {
        this.contacts.setModel(model);
    }

    public void setContactsLog(JList<String> contacts) {
        this.contacts.setModel(contacts.getModel());
    }

    public JList<String> getContacts() {
        return contacts;
    }
}
