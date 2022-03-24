package client.clientView.panel;

import client.clientModel.Login;
import xcommon.User;
import client.clientController.ClientControl;

import javax.swing.*;
import java.awt.*;

public class NorthPanel extends JPanel {

    private int width;
    private int height;

    private ClientControl clientControl;
    private User user;
    private Login login;

    private JLabel lblTitle;
    private JLabel lblAuthors;
    private JLabel lblVersion;

    private JLabel clientProfileImage;
    private JLabel clientUserName;

    public NorthPanel(int width, int height, ClientControl clientControl, User user, Login login) {
        this.login = login;
        this.user = user;
        this.clientControl = clientControl;
        this.setLayout(null);
        this.width = width;
        this.height = height;
        this.setSize(width, height);
        setLocation(0, 0);
        setUp();
    }

    private void setUp() {
        headerInformation();
        headerClientUser();
    }

    private void headerInformation() {
        lblTitle = new JLabel("ChatSystem");
        lblAuthors = new JLabel("@Authors: S.O.K.K.E.N.");
        lblVersion = new JLabel("@Version: 100.0");

        lblTitle.setBounds(20, 20, 180,30);
        lblAuthors.setBounds(180, 20, 180,30);
        lblVersion.setBounds(380, 20, 180,30);

        lblTitle.setForeground(Color.gray);
        lblTitle.setFont(new Font("Arial", Font.PLAIN, 26));

        lblAuthors.setForeground(Color.gray);
        lblAuthors.setFont(new Font("Arial", Font.PLAIN, 16));

        lblVersion.setForeground(Color.gray);
        lblVersion.setFont(new Font("Arial", Font.PLAIN, 16));

        add(lblTitle);
        add(lblAuthors);
        add(lblVersion);
    }

    private void headerClientUser() {
        clientProfileImage = new JLabel();
        clientProfileImage.setBounds(550,18,40,40);
        ImageIcon icon = new ImageIcon(user.getUserImage().getImage());
            // Scale the image
            Image image = icon.getImage();
            Image newImage = image.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImage);
            clientProfileImage.setIcon(icon);
        add(clientProfileImage);

        clientUserName = new JLabel(user.getUserName());
        clientUserName.setBounds(600,20,150,30);
        clientUserName.setForeground(Color.blue);
        clientUserName.setFont(new Font("Arial", Font.PLAIN, 16));
        add(clientUserName);
    }
}
