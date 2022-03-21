package model;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Message implements Serializable {

    private String text, sendare, time;
    private ArrayList<User> users = null;
    private Icon icon;

    public Message(String text, String sendare, ArrayList<User> user, ImageIcon imageIcon, String time) {
        this.text = text;
        this.sendare = sendare;
        this.users = user;
        this.icon = imageIcon;
        this.time = time;
    }

    public Message(String text){
        this.text = text;
    }

    public String getSendare() {
        return sendare;
    }

    public String getTime() {
        return time;
    }

    public ArrayList<User> getMotagare() {
        return users;
    }

    public Icon getIcon() {
        return icon;
    }


    public String getText() {
        return text;
    }

}
