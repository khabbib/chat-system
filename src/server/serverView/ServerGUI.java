package server.serverView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * ServerGui class to display the server window
 */
public class ServerGUI extends JFrame {

    private JFrame frame;
    private JButton btnLogger;
    private JButton btnShutDown;

    /**
     * Constructor  display the window with two buttons.
     */
    public ServerGUI() {
        super("Chat Server");

        this.frame = new JFrame();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocation(0, 0);
        this.setSize(300, 200);
        this.setLayout(null);
        this.setVisible(true);

        btnShutDown = new JButton("Shutdown");
        btnShutDown.setBounds(80, 100, 120,30);
        btnShutDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == btnShutDown) {
                    System.exit(0);
                }
            }
        });
        this.add(btnShutDown);

        btnLogger = new JButton("Log");
        btnLogger.setBounds(80, 20, 120,30);
        btnLogger.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == btnLogger) {
                    try {
                        LoggerGUI log = new LoggerGUI();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        this.add(btnLogger);
    }
}
