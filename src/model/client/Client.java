package model.client;

import model.MessageProducer;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private String ip;
    private int port;
    private Socket socket;

    public Client(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    private void connect() throws IOException {
        System.out.println("[Client]: connected!");
        socket = new Socket(ip, port);
    }

    private void disconnect() throws IOException {
        System.out.println("[Client]: disconnected!");
        socket.close();
    }

    public void send(MessageProducer mp) throws IOException {
        connect();
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(mp);
        System.out.println("[Client]: MessageProducer-implementation sent!");
        disconnect();
    }
}
