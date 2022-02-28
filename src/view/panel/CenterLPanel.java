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
        Color bgColor = viewUtilities.getMainFrameBackgroundColor();
        this.setBackground(bgColor);
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
        JLabel lblScreen = new JLabel("Message Screen");
        lblScreen.setBounds(20, 0, 180,20);
        lblScreen.setForeground(Color.BLACK);
        lblScreen.setFont(new Font("Arial", Font.BOLD, 12));
        add(lblScreen);

        txtScreen = new JTextArea();
        txtScreen.setLocation(0,20);
        txtScreen.setSize(width, height - 20);
        txtScreen.setFont(mainFont);
        txtScreen.setVisible(true);
        txtScreen.setEditable(false);
        this.add(txtScreen);
    }

    public String getTxtScreen() {
        return txtScreen.getText();
    }
    public void setTxtScreen(String txtScreen) {
        this.txtScreen.setText(txtScreen);
    }
}
