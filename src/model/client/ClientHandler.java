package model.client;

import javax.imageio.IIOException;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable
{
    //Håller koll på alla klienter. Låter oss  också kunna strema meddelande så att alla klienter kan se det
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();

    //Socket som kommer från Server klassen, är nödvändigt för att hålla kommunikation med client
    private Socket socket;

    //Denna instans används för att läsa data från klienten
    private BufferedReader bufferedReader;

    //Denna instans används för att skicka data till klienten
    private BufferedWriter bufferedWriter;

    //Detta är en instans som vi kommer att använda för användarnamn när man chattar
    private String clientUsername;


    public ClientHandler(Socket socket)
    {
        try{
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.clientUsername =  bufferedReader.readLine();
            clientHandlers.add(this);

            broadcastMessage("Server: " + clientUsername + " har deltagit i chatten!");
        }  catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }

    }

    @Override
    public void run()
    {
        String messageFromClient;

        while(socket.isConnected()) {
            try {
                messageFromClient = bufferedReader.readLine();
                broadcastMessage(messageFromClient);
            } catch (IOException e){
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }

    public void broadcastMessage(String messageTosend) {
        for(ClientHandler clientHandler : clientHandlers) {
            try {
                if(!clientHandler.clientUsername.equals(clientUsername)) {
                    clientHandler.bufferedWriter.write(messageTosend);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                }
            } catch (IOException e)
            {
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    public void removeClienthandler() {
        clientHandlers.remove(this);
        broadcastMessage("Server: " + clientUsername + " har lämnat chatten");
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter)
    {
        removeClienthandler();
        try{

            if(bufferedReader != null)
            {
                bufferedReader.close();
            }

            if(bufferedWriter != null)
            {
                bufferedWriter.close();
            }

            if(socket != null)
            {
                socket.close();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
