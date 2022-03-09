package model;

import javax.swing.*;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String userName;
    private ImageIcon userImage;
    private ArrayList<String> contacts;
    private transient PrintStream streamOut;
    private transient InputStream streamIn;

    public User(String userName, ImageIcon userImage) {
        this.userName = userName;
        this.userImage = userImage;
        this.contacts = new ArrayList<>();
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ImageIcon getUserImage() {
        return userImage;
    }
    public void setUserImage(ImageIcon userImage) {
        this.userImage = userImage;
    }

    public PrintStream getStreamOut() {
        return streamOut;
    }

    public void setStreamOut(PrintStream streamOut) {
        this.streamOut = streamOut;
    }

    public InputStream getStreamIn() {
        return streamIn;
    }

    public void setStreamIn(InputStream streamIn) {
        this.streamIn = streamIn;
    }

    public void addContact(String contact) {
        contacts.add(contact);
    }

    public ArrayList<String> getContacts() {
        return contacts;
    }
}
