package Sockets_Assignment_3;

import java.util.Scanner;

//Class to read data from keyboard and check terminating results from server
public class ClientProtocol {

    private static Scanner scanner;

    public ClientProtocol(){
        scanner = new Scanner(System.in);
    }

    public String getInput() {

        String input = scanner.nextLine();
        return input;
    }

}
