import com.sun.xml.internal.ws.api.message.Packet;

import javax.xml.transform.sax.SAXSource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class SingleServerQueue {

    static final double departProbability = 0.6;

    public static void main(String[] args) {

        Random arrivalGenerator = new Random();

        double arrivalProbs[] = {0.1, 0.2, 0.3, 0.4, 0.5,0.51, 0.52,0.53, 0.54, 0.55, 0.56, 0.57, 0.58,0.59};

        int[] lengths = new int[100000];

        for (double probability : arrivalProbs) {
            System.out.println("PROBABILITY " + probability);

            lengths[0] = runSimulation(probability, arrivalGenerator,0);
            for (int i = 1; i < lengths.length; i++) {
                int change =   runSimulation(probability, arrivalGenerator,lengths[i-1]);
                lengths[i] = lengths[i-1]+change;
            }
            double sum = 0;
            for (int x : lengths) {
                sum+=x;
            }
            System.out.println(sum+"    "+(sum/lengths.length)/probability);

            for (int i = 0; i <lengths.length; i++) {
                lengths[i] = 0;
            }
        }
    }


    private static int runSimulation(double p, Random arrivalGenerator,int prev) {
        int length = 0;
        double token = arrivalGenerator.nextInt(100);
        double arrivalProb = token/100;
        token = arrivalGenerator.nextInt(100);
        double departProb = token/100;

        if(arrivalProb<=p){
            //System.out.println("ARRIVED");
            length++;
        }
        if (departProb <=departProbability&& prev>0) {
            //System.out.println("DEPARTED");
            length--;
        }
        return length;
    }

}
