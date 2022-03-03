package controller;
import model.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

//  import com.sun.tools.javac.Main;

public class Login extends JFrame {

    private JFrame frame;
    private JPanel mainPanel, subPanel1, subPanel2;
    private JPanel contentPane;
    private JTextField userName;
    private JPasswordField password;
    private JButton button;

    private File src;
    private String srcName;
    private ImageIcon imageIcon;

    public Login() {

        frame = new JFrame("Chat System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(100, 100, 350, 500);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLocation(100, 100);
        contentPane.setBackground(Color.white);
        Border border = BorderFactory.createLineBorder(new Color(222, 222, 222), 19);
        contentPane.setBorder(border);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // login label
        JLabel lbllogin = new JLabel("LOGIN");
        lbllogin.setFont(new Font("Serif", Font.BOLD, 20));
        lbllogin.setBounds(130, 50, 68, 20);
        contentPane.add(lbllogin);

        // username jlabel
        JLabel lbluser = new JLabel("Username");
        lbluser.setFont(new Font("Serif", Font.BOLD, 12));
        lbluser.setBounds(50, 105, 60, 14);
        contentPane.add(lbluser);

        // username inputfield
        userName = new JTextField();
        userName.setBounds(50, 135, 205, 25);
        userName.setColumns(10);
        contentPane.add(userName);

        // image choosing text
        JLabel lblprof = new JLabel("Choose profile image");
        lblprof.setFont(new Font("Serif", Font.BOLD, 13));
        lblprof.setBounds(50, 190, 130, 23);
        contentPane.add(lblprof);

        // label used as frame for image
        JLabel pic = new JLabel("<Image>", SwingConstants.CENTER);
        pic.setBounds(115, 260, 100, 100);
        Border b = BorderFactory.createLineBorder(new Color(222, 222, 222), 1);
        pic.setBorder(b);

        JButton btnfile = new JButton("File");
        btnfile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser file = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Pictures", "jpg", "png");
                file.setFileFilter(filter);
                file.showOpenDialog(null);
                src = file.getSelectedFile();
                // path = src.getAbsoluteFile();
                // System.out.println(path);
                // JLabel pic = new JLabel();
                try{
                    //ImageIcon ii = new ImageIcon(ImageIO.read(new File(String.valueOf(src.getAbsoluteFile()))));
                    imageIcon = new ImageIcon(ImageIO.read(new File(src.getAbsolutePath())));
                    Image image = imageIcon.getImage();
                    Image newImage = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
                    imageIcon = new ImageIcon(newImage);
                    //btnStaff.setIcon(ii);
                    pic.setIcon(imageIcon);
                }catch (Exception ex){
                    ex.printStackTrace();
                }

            }
        });

        btnfile.setBounds(190, 190, 89, 23);
        contentPane.add(pic);
        contentPane.add(btnfile);

        // Snabb sätt att få GUI fönstret
        //new Controller("admin", "admin.png");

        //ENTER KNAPP
        JButton btnEnter = new JButton("Enter");
        btnEnter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if(ae.getSource() == btnEnter) {

                    try {
                        srcName = src.getName();

                        if(!userName.getText().equals("") && !srcName.equals("")) {
                            String namevalue = userName.getText(); // tar in namnet som skriv i username field
                            setUserName(namevalue);
                            System.out.println(namevalue);
                            User user = new User(namevalue, "profile.png");
                            new Controller(user);
                            //Main m = new Main();
                            //m.getSetUserName(namevalue);
                            //String [] arguments = new String []{""}; // ta in en parameter typ namnet eller något
                            //m.main(arguments);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Please choose a username!");
                        }

                    } catch (Exception exeption) {
                        //exeption.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Please choose an image!");
                    }
                }
            }
        });

        btnEnter.setBounds(125, 390, 89, 23);
        contentPane.add(btnEnter);
    }

    // getter & setter
    public String getUserName() {
        return userName.getText();
    }

    public void setUserName(String username) {
        userName.setText(username);
    }

    // main
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}




