package view.panel;

import controller.Controller;
import controller.Login;
import view.Contact;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class NorthPanel extends JPanel {

    private int width;
    private int height;
    private Controller controller;

    private Contact contact;

    private JButton btnContactlist;
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
        btnContact();
        btnLogout();
    }

    private void btnContact() {
        btnContactlist = new JButton("Contact list");
        btnContactlist.setBounds(50, height/3, 100,30);
        btnContactlist.setFocusable(false);

        btnContactlist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contact = new Contact();
            }
        });

        this.add(btnContactlist);
    }

    private void btnLogout() {
        btnLogout = new JButton("Logout ;(");
        btnLogout.setBounds(width - 150, height/3, 100,30);
        btnLogout.setForeground(Color.red);
        btnLogout.setFocusable(false);

        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
                //new Login();
            }
        });

        this.add(btnLogout);
    }

    // border

    // getter and setter
    public JButton getBtnContactlist() {
        return btnContactlist;
    }
    public JButton getBtnLogout() {
        return btnLogout;
    }
}
