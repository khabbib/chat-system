package client.clientModel;

import client.clientController.ClientControl;
import client.clientView.LoginGUI;
import client.clientView.ButtonType;
import xcommon.User;

import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Login {

    private LoginGUI loginGui;

    private ClientControl clientControl;
    private File fileSrc;
    private String srcName;
    private ImageIcon imageIcon;
    private static User user;
    private boolean clientIsDone = false;

    // Constructor
    public Login() {
        loginGui = new LoginGUI(this);
        loginGui.setVisible(true);
    }

    public void buttonPressedLogin(ButtonType button) {

        switch (button) {

            case FileChoosePfpImg:
                System.out.println("Button 'FileChoosePfpImg' is clicked!");
                JFileChooser file = new JFileChooser();
                file.showOpenDialog(null);
                fileSrc = file.getSelectedFile();
                try{
                    imageIcon = new ImageIcon(ImageIO.read(new File(fileSrc.getAbsolutePath())));
                    Image image = imageIcon.getImage();
                    Image newImage = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
                    imageIcon = new ImageIcon(newImage);

                    loginGui.setImagePic(imageIcon); // HERE SENDS TO GUI
                    System.out.println(imageIcon + "ENTITY");
                    user.setUserImage(imageIcon);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                break;

            case Login:
                System.out.println("Button 'Login' is clicked!");
                try {
                    srcName = fileSrc.getName();
                    if(!loginGui.getUserName().equals("") && !srcName.equals("")) {
                        if(loginGui.getUserName().length() < 3 || loginGui.getUserName().length() > 12) {
                            JOptionPane.showMessageDialog(null, "Username shall be between 3-12 characters!");
                        } else {
                            String namevalue = loginGui.getUserName();
                            user = new User(namevalue, imageIcon);
                            setUser(user);
                            setClientIsDone(true);

                            loginGui.dispose();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Please choose a username!");
                    }
                } catch (Exception exeption) {
                    JOptionPane.showMessageDialog(null, "Please choose an image!");
                }
                break;
        }
    }

    // ClientControl
    public ClientControl getClient() {
        return clientControl;
    }
    public void setClient(ClientControl clientControl) {
        this.clientControl = clientControl;
    }

    // Username
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    // Loop
    public boolean isClientIsDone() {
        return clientIsDone;
    }
    public void setClientIsDone(boolean clientIsDone) {
        this.clientIsDone = clientIsDone;
    }
}




