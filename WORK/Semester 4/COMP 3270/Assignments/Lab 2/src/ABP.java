import java.util.Random;

public class ABP {

    public static void main(String[] args) {


        int packetSize = 1500;
        int headerSize = 54;

        ABPSimulator sender = new ABPSimulator(5,100000,5000000,250,2.5,packetSize,headerSize);
        long START_TIME = (System.currentTimeMillis());

        while (ABPSimulator.SUCCESSFUL_PACKET_COUNT<1000){
            sender.simulate();
        }
//        for (int i = 0;i<1000;i++){
//            sender.simulate();
//        }
        long END_TIME = (System.currentTimeMillis());
        double THROUGHPUT = (ABPSimulator.SUCCESSFUL_PACKET_COUNT * (packetSize-headerSize))/(END_TIME-START_TIME);
        System.out.println("TRHOUGH PUT "+THROUGHPUT);

    }
}
