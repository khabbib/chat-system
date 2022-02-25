package model;

import javax.swing.*;

public class Message {

    private String text;
    private Icon icon;

    public Message(String text, ImageIcon imageIcon) {
        this.text = text;
        this.icon = imageIcon;
    }

    public Icon getIcon() {
        return icon;
    }

    public String getText() {
        return text;
    }

}
