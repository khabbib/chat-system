package view.panel;

import controller.Controller;
import view.ButtonType;
import view.MainPanel;

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
    private Controller controller;

    private final int imageSize = 75;

    private JTextField txtMsg;
    private JButton chooseFile;
    private JButton sendButton;

    private JLabel imageMsgArea;

    public SouthPanel(int width, int height, Controller controller) {
        this.controller = controller;
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
        lblImageMsgArea();
    }

    private void txtMsg() {
        txtMsg = new JTextField();
        txtMsg.setBounds(50,30,300,30);

        txtMsg.addKeyListener(new KeyAdapter() {
            // send message on Enter
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    controller.buttonPressed(ButtonType.Send);
                }
            }
        });

        add(txtMsg);
    }

    private void btnFile() {
        chooseFile = new JButton();
        chooseFile.setBounds(400,30,80,30);
        chooseFile.setText("File");
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
                    txtMsg.setText(src.getName());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        chooseFile.addActionListener(l -> controller.buttonPressed(ButtonType.File));

        add(chooseFile);

    }

    private void btnSendMsg() {
        sendButton = new JButton();
        sendButton.setBounds(500,30,80,30);
        sendButton.setText("Send");
        sendButton.setFocusable(false);
        sendButton.addActionListener(l -> controller.buttonPressed(ButtonType.Send));
        add(sendButton);
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
