package view.panel;

import model.client.Client;
import view.ButtonType;
import view.ViewUtilities;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class CenterRPanel extends JPanel {

    private int width;
    private int height;
    private JList<String> userList;
    private JButton btnContactList;
    private JButton btnAddToContact;
    private String[] str = {"Harry", "Barry", "Melly", "Delly"};

    private Client client;

    public CenterRPanel(int width, int height, Client client) {
        ViewUtilities viewUtilities = new ViewUtilities();
        Color bgColor = viewUtilities.getMainFrameBackgroundColor();
        this.setBackground(bgColor);

        this.client = client;
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
        ImageIcon imageIcon = new ImageIcon("images_gui/contacts.png");
        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImage);

        btnContactList = new JButton(imageIcon);
        btnContactList.setBounds(0, height - 30, 30,30);
        btnContactList.setFocusable(false);
        btnContactList.addActionListener(l -> client.buttonPressed(ButtonType.ContactList));
        this.add(btnContactList);
    }

    private void btnAddToContact() {
        ImageIcon imageIcon = new ImageIcon("images_gui/contacts_add.png");
        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImage);

        btnAddToContact = new JButton(imageIcon);
        btnAddToContact.setBounds(50, height - 30, 30,30);
        btnAddToContact.setFocusable(false);
        btnAddToContact.addActionListener(l -> client.buttonPressed(ButtonType.ContactAdd));
        this.add(btnAddToContact);
    }

    public void setUserList(ArrayList<String> users) {
        DefaultListModel listModel = new DefaultListModel();
        for (int i = 0; i < users.size(); i++) {
            listModel.addElement(users.get(i));
            System.out.println(users.get(i));
        }
        userList.setModel(listModel);
    }
}
