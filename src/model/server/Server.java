package model.server;

import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import model.client.ClientHandler;

public class Server {

    private ServerSocket serverSocket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

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
                Socket socket = serverSocket.accept();
                System.out.println("En ny klient har kopplat upp sig");

                ClientHandler clientHandler = new ClientHandler(socket);

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
        ServerSocket serverSocket = new ServerSocket(3344);
        Server server = new Server(serverSocket);
        server.startServer();
    }
}

