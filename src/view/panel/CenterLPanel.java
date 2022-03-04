package view.panel;

import model.client.Client;
import view.ViewUtilities;

import javax.swing.*;
import java.awt.*;

public class CenterLPanel extends JPanel {

    private int width;
    private int height;
    private Client client;

    private ViewUtilities viewUtilities;
    private Font mainFont;
    private JTextArea txtScreen;
    private JList<Object> txtMessageScreen;
    JScrollPane s;

    public CenterLPanel(int width, int height, Client client) {
        viewUtilities = new ViewUtilities();
        Color bgColor = viewUtilities.getMainFrameBackgroundColor();
        this.setBackground(bgColor);
        this.client = client;
        this.setLayout(null);
        this.width = width;
        this.height = height;
        this.setSize(width, height);
        setLocation(20, 100);
        setUp();

        s = new JScrollPane(txtMessageScreen);
        s.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        s.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        s.setBounds(0,30, width - 10, height - 10);
        //txtMessageScreen.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        this.add(s);
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

//        txtScreen = new JTextArea();
//        txtScreen.setLocation(0,20);
//        txtScreen.setSize(width, height - 20);
//        txtScreen.setFont(mainFont);
//        txtScreen.setVisible(true);
//        txtScreen.setEditable(false);
//        this.add(txtScreen);

        txtMessageScreen = new JList<>();
        txtMessageScreen.setLocation(0, 20);
        txtMessageScreen.setSize(width, height - 20);
        txtMessageScreen.setFont(mainFont);
        //txtMessageScreen.setOpaque(true);
        txtMessageScreen.setVisible(true);
        //txtMessageScreen.setLayoutOrientation(JList.VERTICAL_WRAP);

//        JScrollPane scrollPane = new JScrollPane();
//        scrollPane.setViewportView(txtMessageScreen);
//        txtMessageScreen.setLayoutOrientation(JList.VERTICAL);

        this.add(txtMessageScreen);

    }

    public String getTxtScreen() {
        return txtScreen.getText();
    }
    public void setTxtScreen(String txtScreen) {
        this.txtScreen.setText(txtScreen);
    }
    public void updateMessageScreen(String[] messageScreenTxt) {
        txtMessageScreen.setListData(messageScreenTxt);
    }
}
