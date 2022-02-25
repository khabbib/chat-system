package view.panel;

import controller.Controller;
import view.ViewUtilities;

import javax.swing.*;
import java.awt.*;

public class CenterLPanel extends JPanel {

    private int width;
    private int height;
    private Controller controller;

    private ViewUtilities viewUtilities;
    private Font mainFont;
    private JTextArea txtScreen;

    public CenterLPanel(int width, int height, Controller controller) {
        viewUtilities = new ViewUtilities();
        this.controller = controller;
        this.setLayout(null);
        this.width = width;
        this.height = height;
        this.setSize(width, height);
        setLocation(20, 100);
        setUp();
    }

    private void setUp() {
        mainFont = viewUtilities.getMainFont();
        messageScreen();
    }

    private void messageScreen() {
        txtScreen = new JTextArea();
        txtScreen.setLocation(0,0);
        txtScreen.setSize(width, height);
        txtScreen.setFont(mainFont);
        txtScreen.setVisible(true);
        txtScreen.setEditable(false);
        this.add(txtScreen);
    }
}
