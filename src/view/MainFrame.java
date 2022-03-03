package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{

    private final int width = 800;
    private final int height = 600;

    private MainPanel mainPanel;
    private Controller controller;

    private ViewUtilities viewUtilities;
    private Color color;

    public MainFrame(Controller controller) {
        super("Chat System");
        viewUtilities = new ViewUtilities();
        this.controller = controller;
        this.setResizable(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.mainPanel = new MainPanel(width, height, controller);
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

    public void updateMessageScreen(String[] stringList) {
        mainPanel.getcLPanel().updateMessageScreen(stringList);
    }
}
