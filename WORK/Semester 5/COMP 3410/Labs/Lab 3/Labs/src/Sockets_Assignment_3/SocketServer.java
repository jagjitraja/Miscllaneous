package Sockets_Assignment_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
public class SocketServer {
    private static ServerSocket serverSocket;
    private static Socket socket;
    private static BufferedReader bufferedReader;
    private static PrintWriter printWriter;
    private static ServerProtocol serverProtocol;
    public static void main(String[] args) {
        try {
             /*Establish port to allow client to connect
            * Create bufferedReader and PrintWriter to write and read from the client via the socket
            * Use clientProtocal to read and process input from client*/
            System.out.println("----------This is the server----------");
            serverSocket = new ServerSocket(3010);
            socket = serverSocket.accept();
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream(),true);
            serverProtocol = new ServerProtocol();
            sendMessage("Hi this is the server, Lets play a game, Guess any number between 0 and 50?, -1 to quit");
            while (true) {
                String line = bufferedReader.readLine();
                serverPrintClientMessage(line);
                int result = serverProtocol.processMessage(line);
                if(result==-1){
                    sendMessage("Sorry I couldnt process that, Guess any number between 0 and 50?, -1 to quit");
                }else if(result == 1) {
                    sendMessage("You guessed incorrect,Guess any number between 0 and 50?, -1 to quit");
                }else if(result == 0){
                    sendMessage("You guessed correclty!!! Congrats, Guess any number between 0 and 50?, -1 to quit");
                }else if(result == 2){
                    sendMessage("Thanks for playing, I am off");
                    closeAll();
                    return;
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
    /*Method to print server messages onto the console*/
    private static void serverPrintClientMessage( String mesage){
        System.out.println("Client says::::::: "+ mesage);;
    }
    /*Method to send data to client using the printWriter*/
    private static void sendMessage(String message){
        printWriter.write(message+"\n");
        printWriter.flush();
    }
    /*Close all sockets and buffers*/
    private static void closeAll() throws IOException{
        printWriter.close();
        bufferedReader.close();
        serverSocket.close();
        socket.close();
    }
}
