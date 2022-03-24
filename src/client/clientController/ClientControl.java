package client.clientController;

import client.clientModel.Client;
import client.clientView.panel.MainFrame;
import client.clientView.ButtonType;
import client.clientModel.Login;
import client.clientModel.Contact;
import xcommon.Message;
import xcommon.Time;
import xcommon.User;

import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class ClientControl {
    private Socket socket;
    private User user;

    private MainFrame view;
    private Contact contact;
    private Client client;


    // Main method
    public static void main(String[] args) throws IOException {
        Login login = new Login();
        while (!login.isClientIsDone()){System.out.println("Connection waiting to login...");}
        Socket socket = new Socket("localhost", 4434);
        System.out.println("Connection check...");
        System.out.println("2%");
        System.out.println("34%");
        System.out.println("78%");
        System.out.println("95%");
        System.out.println("100%");
        ClientControl client1 = new ClientControl(login.getUser(), socket);
        System.out.println("Connection is completed!");
    }


    // Constructor
    public ClientControl(User user, Socket socket) {
        this.user = user;
        view = new MainFrame(this, user);
        client = new Client(view, socket, user);
        contact = new Contact(view, user);
    }



    // Button pressed
    public void buttonPressedClient(ButtonType button) {
        switch (button) {
            case ContactList:
                System.out.println("Button 'Contact' is clicked!");
                contact.showFrame();
                break;

            case ContactAdd:
                System.out.println("Button 'Add contact' is clicked!");

                int index = view.getUserListIndex();

                if(validateIndex(index)) {
                    User user = view.getUserAt(index);
                    if(user != null) {
                        contact.addUser(user);
                        System.out.println("Added!");
                    }
                }
                break;

            case FileSendImg:
                System.out.println("Button 'File' is clicked!");
                System.out.println("[-!-] Attention: This button is modified in SouthPanel!");
                break;

            case Send:
                System.out.println("Button 'Send' is clicked!");
                if (view.getTxtMsg() != null){
                    Message msg = new Message(view.getTxtMsg(),user.getUserName(),null, view.getImgIcon(), Time.getTime());
                    client.sendMessage(msg);
                }else {
                    JOptionPane.showMessageDialog(null, "Message is empty!");
                }
                view.setTxtMsg(null);
                view.setImageMsgArea(null);
                view.setImgMsg(null);
                view.setImageToNull(null);

                break;

            case Logout:
                System.out.println("Button 'Logout' is clicked!");
                /*try {
                    objectOutputStream.writeObject(new Logger(contact.getContacts(), user.getUserName()));
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
                System.out.println("Bye bye!");
                System.exit(0);
                break;
        }
    }

    // Check that we have gotten an index
    private boolean validateIndex(int index) {
        boolean ok = true;
        if (index < 0) {
            JOptionPane.showMessageDialog(null, "Select user from the active list!");
            ok = false;
        }
        return ok;
    }


}