package Sockets_Assignment_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

/*Client class that uses client socket to connect to the server at given port*/
public class SocketClient {


    private static Socket clientSocket;
    private static PrintWriter printWriter;
    private static BufferedReader stringBuffer;
    private static ClientProtocol clientProtocol;

    public static void main(String[] args) {

        try {

             /*Establish a connection with the server
            * Create bufferedReader and PrintWriter to write and read from the server via the socket
            * Use clientProtocal to read and process input from server*/
            System.out.println("----------This is the client----------");
            clientSocket = new Socket("localhost",3010);
            printWriter = new PrintWriter(clientSocket.getOutputStream(),true);
            stringBuffer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            clientProtocol = new ClientProtocol();


            try {
                while (true) {
                    clientPrintServerMessage(stringBuffer.readLine());
                    sendMessage(clientProtocol.getInput());
                }
            }catch (SocketException s){
                System.out.println("CLOSED");
                closeAll();
            }

            //closeAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*Method to print server messages onto the console*/
    private static void clientPrintServerMessage( String mesage){
        System.out.println("Server says:::::::  "+ mesage);;
    }
    /*Method to send data to client using the printWriter*/
    private static void sendMessage(String message){
        printWriter.write(message+"\n");
        printWriter.flush();
    }
    /*Close all sockets and buffers*/
    private static void closeAll() throws IOException{
        printWriter.close();
        stringBuffer.close();
        clientSocket.close();
    }



}
