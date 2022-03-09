package model;

import javax.swing.*;
import java.io.Serializable;

public class Message implements Serializable {

    private String text, sendare, motagare;
    private Icon icon;

    public Message(String text, String sendare, String motagare, ImageIcon imageIcon) {
        this.text = text;
        this.icon = imageIcon;
        this.sendare = sendare;
        this.motagare = motagare;
    }


    public String getSendare() {
        return sendare;
    }

    public String getMotagare() {
        return motagare;
    }

    public Icon getIcon() {
        return icon;
    }

    public String getText() {
        return text;
    }

}
