package view.panel;

import model.User;
import model.client.Client;
import view.ButtonType;
import view.ViewUtilities;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CenterRPanel extends JPanel {

    private int width;
    private int height;
    private JList<String> userList;
    private ArrayList<User> contactUsers;

    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private JButton btnContactList;
    private JButton btnAddToContact;
    private int nbrOfUsers = 0;
    private String[] str = {"Harry", "Barry", "Teddy", "Melly"}; // hanterar 100 klienter

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

        userList = new JList<String>(listModel);
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

    public void setUserList(String users) {
        listModel.addElement(users);
        //contactUsers = users;
        userList.setModel(listModel);
    }

    public User getUserAt(int index){
        if(!(index < nbrOfUsers)){
            return null;
        }
        else {
            return contactUsers.get(index);
        }
    }

    public int getUserListIndex() {
        return userList.getSelectedIndex();
    }
}
