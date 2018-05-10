public class Packet{

    long entryTime;
    long exitTime;

    public Packet(){
        entryTime = System.currentTimeMillis();
    }
    public void exitPacket(){
        exitTime = System.currentTimeMillis();
    }
    public long getDelay(){
        return exitTime = entryTime;
    }
}