package Sockets_Assignment_3;

import java.util.Random;
//class to process input provided by client socket and
// generate random numbers to check the input against
public class ServerProtocol {

    private Random random;

    public ServerProtocol() {
     random = new Random();
    }
    public int generateNumber(){
        return random.nextInt(50);
    }

    public int processMessage(String line) {

        try {
            int x = Integer.parseInt(line);
            if(generateNumber()==x){
                return 0;
            }
            if(x<0){
                return 2;
            }
            return 1;
        }catch (Exception c){
            return -1;
        }
    }
}
