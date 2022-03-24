package client.clientView;

import client.clientModel.Login;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginGUI extends JFrame {

    private Login login;
    private JFrame frame;
    private JPanel panel;

    private JTextField userName;
    private JLabel lblLogin;
    private JLabel lblUser;
    private JLabel lblChoosePfpPic;
    private JLabel imagePic;
    private JButton btnChoosePfp;
    private JButton btnEnter;

    public LoginGUI(Login login) {
        super("Chat Client");

        this.login = login;

        frame = new JFrame();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(100, 100, 350, 500);

        // Panel
        panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setLocation(100, 100);
        panel.setBackground(Color.white);
        Border border = BorderFactory.createLineBorder(new Color(222, 222, 222), 19);
        panel.setBorder(border);
        setContentPane(panel);
        panel.setLayout(null);

        // Login Label
        lblLogin = new JLabel("LOGIN");
        lblLogin.setFont(new Font("Serif", Font.BOLD, 20));
        lblLogin.setBounds(130, 50, 68, 20);
        panel.add(lblLogin);

        // Username Label
        lblUser = new JLabel("Username");
        lblUser.setFont(new Font("Serif", Font.BOLD, 12));
        lblUser.setBounds(50, 105, 60, 14);
        panel.add(lblUser);

        // Username TextField
        userName = new JTextField();
        userName.setBounds(50, 135, 205, 25);
        userName.setColumns(10);
        panel.add(userName);

        // Image choosing image
        lblChoosePfpPic = new JLabel("Choose profile image");
        lblChoosePfpPic.setFont(new Font("Serif", Font.BOLD, 13));
        lblChoosePfpPic.setBounds(50, 190, 130, 23);
        panel.add(lblChoosePfpPic);

        // Image label
        imagePic = new JLabel("<Image>", SwingConstants.CENTER);
        imagePic.setBounds(115, 260, 100, 100);
        Border b = BorderFactory.createLineBorder(new Color(222, 222, 222), 1);
        imagePic.setBorder(b);
        panel.add(imagePic);

        // Choose pfp pic button
        btnChoosePfp = new JButton("File");
        btnChoosePfp.addActionListener(l -> login.buttonPressedLogin(ButtonType.FileChoosePfpImg));
        btnChoosePfp.setBounds(190, 190, 89, 23);
        panel.add(btnChoosePfp);

        // Enter button
        btnEnter = new JButton("Enter");
        btnEnter.addActionListener(l -> login.buttonPressedLogin(ButtonType.Login));
        btnEnter.setBounds(125, 390, 89, 23);
        panel.add(btnEnter);

        //frame.setVisible(true);
    }

//    public static void main(String args[]) {
//
//    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public String getUserName() {return userName.getText();}
    public void setUserName(String userName) {this.userName.setText(userName);}

    public JLabel getLblLogin() {
        return lblLogin;
    }

    public void setLblLogin(JLabel lblLogin) {
        this.lblLogin = lblLogin;
    }

    public JLabel getLblUser() {
        return lblUser;
    }

    public void setLblUser(JLabel lblUser) {
        this.lblUser = lblUser;
    }

    public JLabel getLblChoosePfpPic() {
        return lblChoosePfpPic;
    }

    public void setLblChoosePfpPic(JLabel lblChoosePfpPic) {
        this.lblChoosePfpPic = lblChoosePfpPic;
    }

    public Icon getImagePic() {
        return imagePic.getIcon();
    }
    public void setImagePic(ImageIcon imagePic) {
        System.out.println(imagePic + "BOUNDARY");
        this.imagePic.setIcon(imagePic);
    }

    public JButton getBtnChoosePfp() {
        return btnChoosePfp;
    }

    public void setBtnChoosePfp(JButton btnChoosePfp) {
        this.btnChoosePfp = btnChoosePfp;
    }

    public JButton getBtnEnter() {
        return btnEnter;
    }

    public void setBtnEnter(JButton btnEnter) {
        this.btnEnter = btnEnter;
    }
}
