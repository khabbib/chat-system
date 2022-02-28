package controller;

import model.User;
import view.ButtonType;
import view.Contact;
import view.MainFrame;

public class Controller {

    private User user;
    //private ModelManager model;
    private MainFrame view;

    public Controller() {

    }

    public Controller(String userName, String userImage) {
        view = new MainFrame(this);

        user = new User(userName, userImage);
        String userN = user.getUserName();
        System.out.println("Welcome, " + userN + "!");
    }

    public void buttonPressed(ButtonType button) {
        switch (button) {
            case ContactList:
                System.out.println("Button 'Contact' is clicked!");
                Contact contact = new Contact();
                break;

            case Logout:
                System.out.println("Button 'Logout' is clicked!");
                System.out.println("Bye bye!");
                System.exit(0);
                //new Login();
                break;

            case ContactAdd:
                System.out.println("Button 'Add contact' is clicked!");
                break;

            case File:
                System.out.println("Button 'File' is clicked!");
                System.out.println("[-!-] Attention: This button is modified in SouthPanel!");
                break;

            case Send:
                System.out.println("Button 'Send' is clicked!");

                view.setTxtScreen(view.getTxtMsg());
                view.setTxtMsg("");

                break;
        }
    }
}
