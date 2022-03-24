package xcommon;

import javax.swing.*;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.Serializable;

public class User implements Serializable {
    private String userName;
    private ImageIcon userImage;

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
}
