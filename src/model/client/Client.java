package model.client;

import javax.imageio.IIOException;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;
    private String image;

    public Client(Socket socket, String username)  {
        try {
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.username = username;
            this.image = "images/gubbe.jpg";

            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.write(image);
            bufferedWriter.flush();

            this.listenForMessage();
            this.sendMessage();

        } catch (IOException e) {

            closeEverything(socket, bufferedReader, bufferedWriter);
        }

    }


    //Denna metod används för att bli identefierad av system
    public void sendMessage() {
        try {
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()) {
                String messageToSend = scanner.nextLine();
                bufferedWriter.write(username + ": " + messageToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch(IOException e){
            closeEverything(socket, bufferedReader, bufferedWriter);

        }
    }

    public void listenForMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msgFromGruppChat;
                while (socket.isConnected()) {

                    try {
                        msgFromGruppChat = bufferedReader.readLine();
                        System.out.println(msgFromGruppChat);
                    } catch (IOException e) {

                        closeEverything(socket, bufferedReader, bufferedWriter);
                    }
                }
            }
        }).start();
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter)  {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }

            if (bufferedWriter != null) {
                bufferedWriter.close();
            }

            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Skriv in ditt användarnamn för att delta i gruppchatten: ");
        String username = scanner.nextLine();
        Socket socket = new Socket("localhost", 4433);
        Client client = new Client(socket, username);
        client.listenForMessage();
        client.sendMessage();
    }
}