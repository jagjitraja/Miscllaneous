import java.util.ArrayList;
import java.util.Random;

public class ABPSimulator {

    int probError;
    int bound;

    public static int SUCCESSFUL_PACKET_COUNT = 0;

    public long START_TIME;
    private int errorCount=0;

    private int SN;
    private int RN;
    private int nextExpectedACK ;
    private int nextExpectedFrame;
    private long transmissionDelay;
    long TIME_OUT_TIME;
    private double timeOutFactor;
    private int headerLength;
    private double dataLength;
    private double propDelay;
    private static int c = 0;

    public enum STATE{LOSS, ERROR, NOERROR,TIMEOUT};

    public static ArrayList<Event> eventsScheduler;


    public ABPSimulator(int BERNum,
                        int BERden,
                        double channelCapacity,
                        double propagationDelay,
                        double timeoutDelayFactor,
                        int packetSize,
                        int headerSize) {

        eventsScheduler = new ArrayList<>();
        timeOutFactor = timeoutDelayFactor;

        probError = BERNum;
        bound = BERden;

        SN = RN = 0;
        nextExpectedFrame = 0;
        nextExpectedACK = 1;
        this.propDelay = propagationDelay;

        headerLength = headerSize*8;
        dataLength = packetSize * 8 - headerLength;
        transmissionDelay = (long) (packetSize*8 /channelCapacity);
    }

    public void simulate(){
        START_TIME = System.currentTimeMillis();
        errorCount = 0;
        TIME_OUT_TIME = (long) (START_TIME + (2*transmissionDelay) + (2*propDelay)+(timeOutFactor*propDelay));
        sendPacket();
    }

    private void sendPacket() {
        Event sendPacketResult = channelTransmit();
        if (sendPacketResult.result != STATE.LOSS) {
            receiverCheckPacket(sendPacketResult);
        }else{
            waitTIMEOUT();
            return;
        }
    }

    private void receiverCheckPacket(Event receivedPacketResult) {

        if(receivedPacketResult.result ==(STATE.NOERROR) && SN == nextExpectedFrame){
            nextExpectedFrame+=1;
            nextExpectedFrame %=2;
            RN = nextExpectedFrame;

            Event ACKsendEvent = channelTransmit();

            if (ACKsendEvent.result==STATE.LOSS){
                waitTIMEOUT();
            }else{
                if (ACKsendEvent.result==STATE.NOERROR || ACKsendEvent.result==STATE.ERROR && RN == nextExpectedACK){
                    SN+=1;
                    SN%=2;
                    nextExpectedACK+=1;
                    nextExpectedACK%=2;
                    SUCCESSFUL_PACKET_COUNT++;
                    eventsScheduler.add(receivedPacketResult);
                }else{
                    waitTIMEOUT();
                }
            }
        }else{
            waitTIMEOUT();
        }
    }

    private void waitTIMEOUT(){
        while (System.currentTimeMillis()<TIME_OUT_TIME){
        }
    }

    public Event channelTransmit() {

        errorCount = 0;
        for (int i = 0; i < dataLength; i+=1) {
            Random randomGen = new Random();
            int generatedProb = randomGen.nextInt(bound);
            if (generatedProb < probError) {
                errorCount++;
            }
        }
        if(errorCount>5){
            return new Event(System.currentTimeMillis()+(long)propDelay,STATE.LOSS);
        }
        if(errorCount==0){
            return new Event(System.currentTimeMillis()+(long)propDelay,STATE.NOERROR);
        }
        else {
            return new Event(System.currentTimeMillis()+(long)propDelay,STATE.ERROR);
        }
    }
    private class Event{
        long completionTime;
        ABPSimulator.STATE result;

        Event(long cT, ABPSimulator.STATE result){
            completionTime = cT;
            this.result = result;
        }
    }
}