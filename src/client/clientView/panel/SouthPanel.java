package client.clientView.panel;

import client.clientController.ClientControl;
import client.clientView.ButtonType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

public class SouthPanel extends JPanel {

    private int width;
    private int height;
    private ClientControl clientControl;

    private final int imageSize = 75;

    private JTextField txtMsg;

    private JButton chooseFile;
    private JButton sendButton;
    private JButton btnLogout;

    private JLabel imageMsgArea;
    private ImageIcon ImgToSend;

    public SouthPanel(int width, int height, ClientControl clientControl) {
        this.clientControl = clientControl;
        this.setLayout(null);
        this.width = width;
        this.height = height;
        this.setSize(width, height);
        setLocation(0, height - (height / 8));
        setUp();
    }

    public void setUp() {
        txtMsg();
        btnFile();
        btnSendMsg();
        btnLogout();
        lblImageMsgArea();
    }

    private void txtMsg() {
        txtMsg = new JTextField();
        txtMsg.setBounds(50,30,300,30);
        txtMsg.addKeyListener(new KeyAdapter() {
            // send message on Enter
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    clientControl.buttonPressedClient(ButtonType.Send);
                }
            }
        });
        add(txtMsg);
    }

    private void btnFile() {
        ImageIcon imageIcon = new ImageIcon("images_gui/gallery.png");
        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImage);

        chooseFile = new JButton(imageIcon);
        chooseFile.setBounds(400,30,30,30);
        chooseFile.setFocusable(false);

        chooseFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser file = new JFileChooser();
                file.showOpenDialog(null);
                File src = file.getSelectedFile();
                try {
                    ImageIcon ii = new ImageIcon(ImageIO.read(new File(src.getAbsolutePath())));
                    Image image = ii.getImage();
                    Image newImage = image.getScaledInstance(imageSize, imageSize, java.awt.Image.SCALE_SMOOTH);
                    ii = new ImageIcon(newImage);
                    imageMsgArea.setIcon(ii);
                    ImgToSend = ii;
                    setImgMsg(ii);
                    txtMsg.setText(src.getName());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        chooseFile.addActionListener(l -> clientControl.buttonPressedClient(ButtonType.FileSendImg));
        add(chooseFile);
    }

    private void btnSendMsg() {
        ImageIcon imageIcon = new ImageIcon("images_gui/send.png");
        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImage);

        sendButton = new JButton(imageIcon);
        sendButton.setBounds(450,30,30,30);
        sendButton.setFocusable(false);
        sendButton.addActionListener(l -> clientControl.buttonPressedClient(ButtonType.Send));
        add(sendButton);
    }

    private void btnLogout() {
        ImageIcon imageIcon = new ImageIcon("images_gui/logout.png");
        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImage);

        btnLogout = new JButton(imageIcon);
        btnLogout.setBounds(500, 30, 30,30);
        btnLogout.setForeground(Color.red);
        btnLogout.setFocusable(false);

        btnLogout.addActionListener(l -> clientControl.buttonPressedClient(ButtonType.Logout));

        this.add(btnLogout);
    }

    private void lblImageMsgArea() {
        imageMsgArea = new JLabel("");
        imageMsgArea.setBounds(600,20,imageSize,imageSize);
        add(imageMsgArea);
    }

    public String getTxtMsg() {
        if(this.txtMsg.getText().length() > 0) {
            return txtMsg.getText();
        }
        else {
            return null;
        }
    }
    public void setTxtMsg(String txtMsg) {
        this.txtMsg.setText(txtMsg);
    }


    public void setImgMsg(ImageIcon imageIcon) {
        imageMsgArea.setIcon(imageIcon);
        //ImgToSend.setImage(imageIcon);
    }
    public ImageIcon getImgMsg() {
        return ImgToSend;
    }
    public void setImageToNull(Image image) {
        ImgToSend = new ImageIcon();
    }

    public JLabel getImageMsgArea() {
        return imageMsgArea;
    }
    public void setImageMsgArea(String imageMsgArea) {
        this.imageMsgArea.setText(imageMsgArea);
    }
}
