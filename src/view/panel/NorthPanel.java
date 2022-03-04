package view.panel;

import model.client.Client;
import view.ButtonType;

import javax.swing.*;
import java.awt.*;

public class NorthPanel extends JPanel {

    private int width;
    private int height;
    private Client client;

    private JLabel lblTitle;
    private JLabel lblAuthors;
    private JLabel lblVersion;



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
        lblHeader();
    }

    private void lblHeader() {
        lblTitle = new JLabel("ChatSystem");
        lblAuthors = new JLabel("@Authors: S.O.K.K.E.N.");
        lblVersion = new JLabel("@Version: 10.0.0");

        lblTitle.setBounds(20, 20, 180,30);
        lblAuthors.setBounds(180, 20, 180,30);
        lblVersion.setBounds(600, 20, 180,30);

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
}
