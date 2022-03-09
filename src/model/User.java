package model;

import javax.swing.*;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.Serializable;

public class User implements Serializable {
    private String userName, msg;
    private ImageIcon userImage;
    private transient PrintStream streamOut;
    private transient InputStream streamIn;

    public User(String userName, ImageIcon userImage) {
        this.userName = userName;
        this.userImage = userImage;
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
}
