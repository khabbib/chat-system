package view.panel;

import controller.Client;
import view.utilities.RoundedBorder;

import javax.swing.*;
import java.awt.*;

public class NorthPanel extends JPanel {

    private int width;
    private int height;

    private Client client;

    private JLabel lblTitle;
    private JLabel lblAuthors;
    private JLabel lblVersion;

    private JLabel clientProfileImage;
    private JLabel clientUserName;

    public NorthPanel(int width, int height, Client client) {
        this.client = client;
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
        lblVersion = new JLabel("@Version: 19.9.9 (k)");

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
        ImageIcon icon = client.getImageIcon();
            // Scale the image
            Image image = icon.getImage();
            Image newImage = image.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImage);
            clientProfileImage.setIcon(icon);
            RoundedBorder rb = new RoundedBorder(20); //Round image (not working)
            rb.getBorderInsets(clientProfileImage);         //Round image (not working)
        add(clientProfileImage);

        clientUserName = new JLabel(client.getUname());
        clientUserName.setBounds(600,20,150,30);
        clientUserName.setForeground(Color.blue);
        clientUserName.setFont(new Font("Arial", Font.PLAIN, 16));
        add(clientUserName);
    }
}
