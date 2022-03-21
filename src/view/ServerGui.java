package view;

import controller.Server;
import model.Unsend;
import model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;

/**
 * ServerGui class to display the server window
 */

public class ServerGui extends JFrame {

    private JFrame frame;
    private JButton btnLogger;
    private JButton shutDown;

    private ServerSocket serverSocket;
    private Server.ClientHandler clientHandler;
    private static User user;
    private Unsend unsend;


    /**
     * Constructor  display the window with two buttons.
     */
    public ServerGui() {
        super("Chat Server");

        this.frame = new JFrame();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocation(0, 0);
        this.setSize(300, 200);
        this.setLayout(null);
        this.setVisible(true);

        shutDown = new JButton("Shutdown");
        shutDown.setBounds(80, 100, 120,30);
        shutDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == shutDown) {
                    System.exit(0);
                }
            }
        });
        this.add(shutDown);

        btnLogger = new JButton("Log");
        btnLogger.setBounds(80, 20, 120,30);
        btnLogger.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == btnLogger) {
                    try {
                        LogGUI log = new LogGUI();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        this.add(btnLogger);
    }
}
