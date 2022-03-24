package client.clientView.panel;

import xcommon.User;
import client.clientController.ClientControl;
import client.clientView.ButtonType;
import client.clientView.ViewUtilities;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;

public class CenterRPanel extends JPanel {

    private int width;
    private int height;
    private JList<String> userList;
    private ArrayList<User> contactUsers = new ArrayList<>();
    private SouthPanel southPanel;

    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private JButton btnContactList;
    private JButton btnAddToContact;
    private int nbrOfUsers = 0;
    private String[] str = {"Harry", "Barry", "Teddy", "Melly"}; // hanterar 100 klienter

    private ClientControl clientControl;

    public CenterRPanel(int width, int height,SouthPanel southPanel, ClientControl clientControl) {
        ViewUtilities viewUtilities = new ViewUtilities();
        Color bgColor = viewUtilities.getMainFrameBackgroundColor();
        this.setBackground(bgColor);

        this.southPanel = southPanel;
        this.clientControl = clientControl;
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
        userList.setBorder(new EmptyBorder(10,10, 10, 10));
        this.add(userList);
        userList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()){
                    try {
                        JList source = (JList)event.getSource();
                        southPanel.setTxtMsg("@" + source.getSelectedValue().toString());
                    } catch (Exception e) {
                    }
                }
            }
        });
    }

    private void btnContact() {
        ImageIcon imageIcon = new ImageIcon("images_gui/contacts.png");
        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImage);

        btnContactList = new JButton(imageIcon);
        btnContactList.setBounds(0, height - 30, 30,30);
        btnContactList.setFocusable(false);
        btnContactList.addActionListener(l -> clientControl.buttonPressedClient(ButtonType.ContactList));
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
        btnAddToContact.addActionListener(l -> clientControl.buttonPressedClient(ButtonType.ContactAdd));
        this.add(btnAddToContact);
    }

    public void setUserList(User user) {
        contactUsers.add(user);
        removeDuplicates(contactUsers);
        listModel.removeAllElements();
        for (User value : contactUsers) {
            System.out.println(value.getUserName());
            listModel.addElement(value.getUserName());
            nbrOfUsers++;
        }
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

    public void addContacts(ArrayList<User> list) {
        System.out.println("addcontact: ");
        System.out.println(contactUsers.size());
        contactUsers.clear();
        contactUsers.addAll(list);
        System.out.println(contactUsers.size());
        removeDuplicates(contactUsers);
        listModel.removeAllElements();
        System.out.println(contactUsers.size());
        for (User user : contactUsers) {
            System.out.println("got some" );
            listModel.addElement(user.getUserName());
        }
    }

    public void removeDuplicates(ArrayList<User> user){
        for (int i = 0; i < user.size(); i++) {
            int x = 0;
            for (int j = 0; j < user.size(); j++) {
                if (user.get(i).getUserName().equals(user.get(j).getUserName())) {
                    if (x > 0) user.remove(j);
                    else x = 1;
                }
            }
        }
    }
}
