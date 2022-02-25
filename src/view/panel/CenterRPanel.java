package view.panel;

import controller.Controller;

import javax.swing.*;

public class CenterRPanel extends JPanel {

    private int width;
    private int height;
    private JList<String> userList;

    private String[] str = {"heel", "Sabb", "Mortin", "Harry"};

    private Controller controller;

    public CenterRPanel(int width, int height, Controller controller) {
        this.controller = controller;
        this.setLayout(null);
        this.width = width;
        this.height = height;
        this.setSize(width, height);
        setLocation(580, 100);
        setUp();
    }

    public void setUp() {
        userList = new JList<String>(str);
        userList.setOpaque(true);
        userList.setBounds(0,0,width,height);
        this.add(userList);
    }
}
