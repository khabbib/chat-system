package view.panel;

import model.client.Client;
import view.ButtonType;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

public class SouthPanel extends JPanel {

    private int width;
    private int height;
    private Client client;

    private final int imageSize = 75;

    private JTextField txtMsg;
    private JButton chooseFile;
    private JButton sendButton;

    private JButton btnLogout;

    private JLabel imageMsgArea;

    public SouthPanel(int width, int height, Client client) {
        this.client = client;
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
                    client.buttonPressed(ButtonType.Send);
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
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Pictures", "jpg", "png");
                file.setFileFilter(filter);     //Fil filter för endas bilder.
                file.showOpenDialog(null);
                File src = file.getSelectedFile();
                try {
                    ImageIcon ii = new ImageIcon(ImageIO.read(new File(src.getAbsolutePath())));
                    Image image = ii.getImage();
                    Image newImage = image.getScaledInstance(imageSize, imageSize, java.awt.Image.SCALE_SMOOTH);
                    ii = new ImageIcon(newImage);
                    imageMsgArea.setIcon(ii);
                    txtMsg.setText(src.getName());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        chooseFile.addActionListener(l -> client.buttonPressed(ButtonType.File));

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
        sendButton.addActionListener(l -> client.buttonPressed(ButtonType.Send));
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

        btnLogout.addActionListener(l -> client.buttonPressed(ButtonType.Logout));

        this.add(btnLogout);
    }

    private void lblImageMsgArea() {
        imageMsgArea = new JLabel("");
        imageMsgArea.setBounds(600,20,imageSize,imageSize);
        add(imageMsgArea);
    }

    public String getTxtMsg() {
        return txtMsg.getText();
    }

    public void setTxtMsg(String txtMsg) {
        this.txtMsg.setText(txtMsg);
    }

}
