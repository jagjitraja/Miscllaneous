package Sockets_Assignment_3;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketTest {
    public static void main(String[] args) throws IOException {

        startSender();
        startServer();
    }

    public static void startSender() {
        (new Thread(() -> {
            try {
                Socket s = new Socket("localhost", 60010);
                BufferedWriter out = new BufferedWriter(
                        new OutputStreamWriter(s.getOutputStream()));

                while (true) {
                    out.write("Hello World!");
                    out.newLine();
                    out.flush();

                    Thread.sleep(200);
                }

            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        })).start();
    }

    public static void startServer() {
        (new Thread(() -> {
            ServerSocket ss;
            try {
                ss = new ServerSocket(60010);

                Socket s = ss.accept();

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(s.getInputStream()));
                String line = null;
                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        })).start();
    }
}
