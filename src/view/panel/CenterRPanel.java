package view.panel;

import controller.Controller;
import view.ButtonType;
import view.ViewUtilities;

import javax.swing.*;
import java.awt.*;


public class CenterRPanel extends JPanel {

    private int width;
    private int height;
    private JList<String> userList;
    private JButton btnContactlist;
    private JButton btnAddToContact;
    private String[] str = {"heel", "Sabb", "Mortin", "Harry"};

    private Controller controller;

    public CenterRPanel(int width, int height, Controller controller) {
        ViewUtilities viewUtilities = new ViewUtilities();
        Color bgColor = viewUtilities.getMainFrameBackgroundColor();
        this.setBackground(bgColor);

        this.controller = controller;
        this.setLayout(null);
        this.width = width;
        this.height = height;
        this.setSize(width, height);
        setLocation(580, 100);
        setUp();
    }

    public void setUp() {
        activeUsers();
        btnContact();
        btnAddToContact();
    }

    private void activeUsers() {
        JLabel lblActiveUsers = new JLabel("Active users");
        lblActiveUsers.setBounds(20, 0, 100,20);
        lblActiveUsers.setForeground(Color.BLACK);
        lblActiveUsers.setFont(new Font("Arial", Font.BOLD, 12));
        add(lblActiveUsers);

        userList = new JList<String>(str);
        userList.setOpaque(true);
        userList.setBounds(0,20,width, height - 60);
        this.add(userList);
    }

    private void btnContact() {
        btnContactlist = new JButton("Contacts");
        btnContactlist.setBounds(-5, height - 30, 90,30);
        btnContactlist.setFocusable(false);

        btnContactlist.addActionListener(l -> controller.buttonPressed(ButtonType.ContactList));

        this.add(btnContactlist);
    }

    private void btnAddToContact() {
        btnAddToContact = new JButton("Add contacts");
        btnAddToContact.setBounds(80, height - 30, 110,30);
        btnAddToContact.setFocusable(false);

        btnAddToContact.addActionListener(l -> controller.buttonPressed(ButtonType.ContactAdd));

        this.add(btnAddToContact);
    }
}
