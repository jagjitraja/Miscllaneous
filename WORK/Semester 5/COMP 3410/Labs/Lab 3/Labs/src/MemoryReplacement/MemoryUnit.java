package MemoryReplacement;

public class MemoryUnit {

   // private int remainder;
    //private int allocatedSize;
    private int PARENT_ID;
    private int MAX_SIZE = 2;
    public MemoryUnit(){
           // remainder = 0;
            PARENT_ID = -1;
    }

    public MemoryUnit(int sizePerUnit) {
        this.MAX_SIZE = sizePerUnit;
       // remainder = MAX_SIZE;
       // allocatedSize = 0;
        PARENT_ID = -1;
    }


    public boolean isAllocated(){
        return this.PARENT_ID !=-1;
    }

    public int getPARENT_ID() {
        return PARENT_ID;
    }

    public void setPARENT_ID(int PARENT_ID) {
        this.PARENT_ID = PARENT_ID;
    }

    @Override
    public String toString() {
        return "Parent = "+PARENT_ID;
    }
}
