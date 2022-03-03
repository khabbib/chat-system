package view.panel;

import controller.Controller;
import view.ButtonType;
import view.Contact;
import view.RoundedBorder;
import view.ViewUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NorthPanel extends JPanel {

    private int width;
    private int height;
    private Controller controller;

    private JLabel lblTitle;
    private JLabel lblAuthors;
    private JLabel lblVersion;

    private JButton btnLogout;

    public NorthPanel(int width, int height, Controller controller) {
        this.controller = controller;
        this.setLayout(null);
        this.width = width;
        this.height = height;
        this.setSize(width, height);
        setLocation(0, 0);
        setUp();
    }

    private void setUp() {
        lblHeader();
        btnLogout();
    }

    private void lblHeader() {
        lblTitle = new JLabel("@Chat System");
        lblAuthors = new JLabel("@Authors: S.O.K.K.E.N.");
        lblVersion = new JLabel("@Version: 7.0");

        lblTitle.setBounds(width / 2, 5, 180,30);
        lblAuthors.setBounds(width / 2, 20, 180,30);
        lblVersion.setBounds(width / 2, 35, 180,30);

        lblTitle.setForeground(Color.gray);
        lblTitle.setFont(new Font("Arial", Font.PLAIN, 12));

        lblAuthors.setForeground(Color.gray);
        lblAuthors.setFont(new Font("Arial", Font.PLAIN, 12));

        lblVersion.setForeground(Color.gray);
        lblVersion.setFont(new Font("Arial", Font.PLAIN, 12));

        add(lblTitle);
        add(lblAuthors);
        add(lblVersion);
    }

    private void btnLogout() {
        btnLogout = new JButton("Logout");
        btnLogout.setBounds(width - 150, height/3, 100,30);
        btnLogout.setForeground(Color.red);
        btnLogout.setFocusable(false);

        btnLogout.addActionListener(l -> controller.buttonPressed(ButtonType.Logout));

        this.add(btnLogout);
    }

    // border

    // getter and setter
    public JButton getBtnLogout() {
        return btnLogout;
    }
}
