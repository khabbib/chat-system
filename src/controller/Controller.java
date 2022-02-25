package controller;

import model.User;
import view.MainFrame;

public class Controller {

    private User user;
    //private ModelManager model;
    private MainFrame view;

    public Controller() {

    }

    public Controller(String userName, String userImage) {
        view = new MainFrame(this);
        startModel();

        user = new User(userName, userImage);
        String userN = user.getUserName();
        System.out.println("Welcome, " + userN + "!");
    }

    public void startModel() {

    }
}
