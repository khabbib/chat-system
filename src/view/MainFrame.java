package view;

import model.Message;
import model.User;
import model.client.Client;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainFrame extends JFrame{

    private final int width = 800;
    private final int height = 600;

    private MainPanel mainPanel;
    private Client client;

    private ViewUtilities viewUtilities;
    private Color color;

    public MainFrame(Client client) {
        super("Chat Client");
        viewUtilities = new ViewUtilities();
        this.client = client;
        this.setResizable(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.mainPanel = new MainPanel(width, height, client);
        this.setContentPane(mainPanel);

        color = viewUtilities.getMainFrameBackgroundColor();
        this.getContentPane().setBackground(color);
        this.setVisible(true);
    }

    public String getTxtMsg() {
        return mainPanel.getsPanel().getTxtMsg();
    }

    public void setTxtMsg(String txtMsg) {
        mainPanel.getsPanel().setTxtMsg(txtMsg);
    }

    public void setTxtScreen(String txtScreen) {
        mainPanel.getcLPanel().setTxtScreen(txtScreen);
    }

    // string + img
    public void updateMessageScreen(Object stringList) {
        mainPanel.getcLPanel().updateMessageScreen(stringList);
    }

    public void setList(String userList) {
        mainPanel.setList(userList);
    }

    public User getUserAt(int index) {
        return mainPanel.getcRPanel().getUserAt(index);
    }

    public int getUserListIndex() {
        return mainPanel.getcRPanel().getUserListIndex();
    }

    public ImageIcon getImgIcon() {
        return mainPanel.getsPanel().getImgMsg();
    }
}
