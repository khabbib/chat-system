package controller;

import model.User;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// This class is not used!
public class Main {

    // Not in use
    public static void main(String[] args) throws IOException {
        int port = 3344;
        ServerSocket serverSocket = new ServerSocket(port);
        Server server = new Server(serverSocket);
        User user = new User("Admin", new ImageIcon("images"));
        Socket socket = new Socket("localhost", port);
        Client client = new Client(socket, user);
    }
}
