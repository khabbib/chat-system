package client.clientView;

import client.clientView.panel.MainFrame;
import xcommon.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class ContactGUI extends JFrame {
    private MainFrame mainFrame;
    private User user;
    private JList<String> contacts;

    public ContactGUI(MainFrame mainFrame, User user, String[] contactUsers){
        this.user = user;
        this.mainFrame = mainFrame;
        this.contacts = new JList<String>(contactUsers);

        this.setTitle("ContactList: " + user.getUserName());
        this.setBounds(200, 50, 300, 500);
        this.setLayout(null);
        this.setResizable(false);
        this.setVisible(false);
        contacts();
    }

    public void contacts() {
        contacts.setBounds(0,0,300, 500);
        contacts.setBorder(new EmptyBorder(10,10, 10, 10));
        contacts.setOpaque(true);
        contacts.setBackground(new Color(220,220,220));
        contacts.setVisible(true);
        this.add(contacts);
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

    public void showUsers(DefaultListModel listModel) {
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

    }

    private void setContacts(DefaultListModel listModel) {
        this.contacts.setModel(listModel);
    }

    public void setContactsLog(JList<String> contacts) {
        this.contacts.setModel(contacts.getModel());
    }

    public JList<String> getContacts() {
        return contacts;
    }
}

