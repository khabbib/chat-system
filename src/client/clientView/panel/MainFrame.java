package client.clientView.panel;

import client.clientModel.Login;
import xcommon.User;
import client.clientController.ClientControl;
import client.clientView.ViewUtilities;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{

    private final int width = 800;
    private final int height = 600;

    private MainPanel mainPanel;
    private ClientControl clientControl;
    private User user;
    private Login login;

    private ViewUtilities viewUtilities;
    private Color color;

    public MainFrame(ClientControl clientControl, User user) {
        super("Chat Client");
        viewUtilities = new ViewUtilities();
        this.clientControl = clientControl;

        this.setResizable(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.user = user;
        this.login = login;
        this.mainPanel = new MainPanel(width, height, clientControl, user, login);
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

    public void setList(User userList) {
        mainPanel.setList(userList);
    }

    public User getUserAt(int index) {
        return mainPanel.getcRPanel().getUserAt(index);
    }

    public int getUserListIndex() {
        return mainPanel.getcRPanel().getUserListIndex();
    }

    public void setImgMsg(ImageIcon imageIcon) {
        mainPanel.getsPanel().setImgMsg(imageIcon);
    }

    public void setImageToNull(Image image) {
        mainPanel.getsPanel().setImageToNull(image);
    }

    public ImageIcon getImgIcon() {
        return mainPanel.getsPanel().getImgMsg();
    }

    public void setImageMsgArea(String imageMsgArea) {
        mainPanel.getsPanel().setImageMsgArea(imageMsgArea);
    }

    public MainPanel getMainP() {
        return mainPanel;
    }
}
