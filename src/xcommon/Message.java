package xcommon;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Message implements Serializable {

    private String text;
    private String sender;
    private String time;
    private Icon icon;
    private ArrayList<User> users = null;


    public Message(String text, String sender, ArrayList<User> user, ImageIcon imageIcon, String time) {
        this.text = text;
        this.sender = sender;
        this.users = user;
        this.icon = imageIcon;
        this.time = time;
    }

    public String getSendare() {
        return sender;
    }
    public String getTime() {
        return time;
    }

    public Icon getIcon() {
        return icon;
    }
    public String getText() {
        return text;
    }

}
