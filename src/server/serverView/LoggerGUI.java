package server.serverView;

import server.serverModel.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class LoggerGUI extends JFrame{

    private JFrame frame;
    private Logger logger = new Logger();

    public LoggerGUI() throws IOException {
        super("Server Logger");
        frame = new JFrame();
        frame = new JFrame();
        frame.setBounds(100, 100, 500, 400);
        frame.getContentPane().setLayout(null);

        // lblst jlabel
        JLabel lblst = new JLabel("Start time");
        lblst.setFont(new Font("Serif", Font.BOLD, 12));
        lblst.setBounds(320, 70, 60, 14);
        frame.getContentPane().add(lblst);

        // star inputfield
        JTextField jtf1 = new JTextField("uuuu-MM-dd HH:mm");
        jtf1.setBounds(320, 100, 120, 25);
        jtf1.setColumns(10);
        frame.getContentPane().add(jtf1);

        // end time jlabel
        JLabel lblen = new JLabel("End Time");
        lblen.setFont(new Font("Serif", Font.BOLD, 12));
        lblen.setBounds(320, 140, 60, 14);
        frame.getContentPane().add(lblen);

        // endtime inputfield
        JTextField jtf2 = new JTextField("uuuu-MM-dd HH:mm");
        jtf2.setBounds(320, 170, 120, 25);
        jtf2.setColumns(10);
        frame.getContentPane().add(jtf2);

        //create list storing log file kontent
        Vector<String> input = new Vector<String>();
        BufferedReader br = new BufferedReader(new FileReader("./log/log.txt"));
        String in;
        while ((in = br.readLine()) != null) {
            input.addElement(in);
        }

        JList list = new JList(input);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JScrollPane scroller = new JScrollPane(list);
                scroller.setBounds(50, 20, 250, 300);
                frame.getContentPane().add(scroller);
            }
        });
        br.close();

        //create button to find the time
        JButton btnfind = new JButton("Find");
        btnfind.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==btnfind){
                    String start = jtf1.getText();
                    String end = jtf2.getText();
                    logger.checkLogger(start, end);
                    try {
                        BufferedReader  br2 = new BufferedReader(new FileReader("./log/outputs.txt"));
                        Vector<String> out = new Vector<String>();
                        String traffic;
                        while ((traffic = br2.readLine()) != null) {
                            out.addElement(traffic);
                        }
                        UIManager.put("OptionPane.minimumSize",new Dimension(400,400));
                        JOptionPane.showMessageDialog(null, new JScrollPane(new JList(out)));
                        br2.close();
                        FileWriter fw = new FileWriter("./log/outputs.txt", false);

                        fw.write(" ");

                        fw.close();
                    } catch (IOException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }
                }
            }
        });

        btnfind.setBounds(320, 210, 100, 25);
        frame.getContentPane().add(btnfind);
        frame.setVisible(true);
    }
}
