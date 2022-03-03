package controller;

import model.User;
import view.ButtonType;
import view.Contact;
import view.MainFrame;

public class Controller {

    private User user;
    //private ModelManager model;
    private MainFrame view;

    public Controller(User user) {
        view = new MainFrame(this);

        this.user = user;
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

                // Text Area
                //view.setTxtScreen(view.getTxtMsg());
                //view.setTxtMsg("");

                // JList
                addMessage(view.getTxtMsg());
                view.setTxtMsg("");
                view.updateMessageScreen(getMessageString());

                break;
        }
    }

    private String[] messages = new String[100];
    private int nbrOfMessages = 0;

    public void addMessage(String message) {

        for(int i = 0; i <= nbrOfMessages; i++){
            if(messages[i] == null){
                messages[i] = message;
            }
        }
        nbrOfMessages++;
    }

    public String[] getMessageString() {
        String[] infoStrings = new String[nbrOfMessages];
        for(int i = 0; i < messages.length; i++){
            if(messages[i] != null){
                infoStrings[i] = messages[i].toString();
            }
        }
        return infoStrings;
    }
}
