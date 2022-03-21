package view.panel;

import model.Message;
import controller.Client;
import view.utilities.ViewUtilities;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CenterLPanel extends JPanel {

    private int width;
    private int height;
    private Client client;

    private ViewUtilities viewUtilities;
    private Font mainFont;
    private JTextArea txtScreen;
    private JList<Object> txtMessageScreen;
    private DefaultListModel<Object> modelMsg = new DefaultListModel<>();
    JScrollPane s;
    private JScrollBar sb;

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
        sb = s.getVerticalScrollBar();
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
        this.add(lblScreen);

        txtMessageScreen = new JList<>(modelMsg);
        txtMessageScreen.setLocation(0, 20);
        txtMessageScreen.setSize(width, height - 20);
        txtMessageScreen.setBorder(new EmptyBorder(10,10, 10, 10));
        txtMessageScreen.setFont(mainFont);
        txtMessageScreen.setVisible(true);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(txtMessageScreen);
        this.add(txtMessageScreen);

    }

    public String getTxtScreen() {
        return txtScreen.getText();
    }
    public void setTxtScreen(String txtScreen) {
        this.txtScreen.setText(txtScreen);
    }


    public void updateMessageScreen(Object msg) {
        if (msg instanceof Message){

            modelMsg.addElement(((Message) msg).getTime() + " [" + ((Message)msg).getSendare() + "]: " + ((Message)msg).getText());
            if (((Message)msg).getIcon() != null){
                modelMsg.addElement(((Message)msg).getIcon());
            }
        }
        else if(msg instanceof String){
            modelMsg.addElement(msg.toString());
        }
        //txtMessageScreen = new JList<>(modelMsg);
        sb.setValue(sb.getMaximum() - 10);
    }
}
