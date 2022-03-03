package model.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import model.User;
import model.client.ClientHandler;

public class Server {

    private ServerSocket serverSocket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    private ArrayList<User> clients;

    public Server(ServerSocket serverSocket)
    {
        this.serverSocket = serverSocket;
    }

    public void startServer()
    {
        try
        {
            while(!serverSocket.isClosed())
            {
                //Här väntas server till någon har kopplats upp sig på servern
                //När någon kopplar sig till servern så skapas ett ny socket som används för att communicera med clienten

                Socket client = serverSocket.accept();
                System.out.println("En ny klient har kopplat upp sig");

                BufferedReader bis = new BufferedReader(new InputStreamReader(client.getInputStream()));

                String nickname = bis.readLine();
                String image = bis.readLine();

                User newUser = new User(nickname, image);
                System.out.println(newUser.getUserName());
                System.out.println(newUser.getUserImage());
                this.clients = new ArrayList<User>();
                this.clients.add(newUser);

                ClientHandler clientHandler = new ClientHandler(client);

                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {

        }

    }



    //Denna metod kommer stänga ner server socket om ett problem skulle uppstå
    public void closeServerSocket() {
        try{
            if(serverSocket != null)
            {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(4433);
        Server server = new Server(serverSocket);
        server.startServer();
    }
}

