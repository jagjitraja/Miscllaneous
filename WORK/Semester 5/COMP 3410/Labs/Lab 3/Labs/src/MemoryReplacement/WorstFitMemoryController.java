package MemoryReplacement;

public class WorstFitMemoryController extends MemoryController{

    private MemoryUnit [] worstFitMemoryBlocks;
    private int sizePerUnit;

    public WorstFitMemoryController(int memorySize, int sizePerUnit) {
        this.worstFitMemoryBlocks = new MemoryUnit[memorySize/sizePerUnit];
        this.sizePerUnit = sizePerUnit;

        for (int i = 0; i < memorySize/sizePerUnit; i++) {
            worstFitMemoryBlocks[i] = new MemoryUnit();
        }
    }


    public int findWorstFitMemoryLocation(int p_id,int numberOfUnits){
        //Variables to measure the startINdex of the empty blocks and its length
        int worstFitStartIndex = 0;
        int worstFitBlockLength =-1;
        int numberOfJumps = worstFitMemoryBlocks.length;

        for(int i = 0; i< worstFitMemoryBlocks.length; i++){
            if (!worstFitMemoryBlocks[i].isAllocated()){
                int j = i;
                int tempBlockLength = 0;
                //LOOP THROUGH TO GET THE SIZE OF THE EMPTY BLOCK
                while (j<worstFitMemoryBlocks.length&&!worstFitMemoryBlocks[j].isAllocated()){
                    tempBlockLength++;
                    numberOfJumps++;
                    j++;
                }
                if(worstFitBlockLength==-1||tempBlockLength>worstFitBlockLength){
                    worstFitBlockLength = tempBlockLength;
                    worstFitStartIndex = i;
                }
            }
        }
        //IF THE LARGEST EMPTY BLOCK IS NOT BIG ENOUGH TO ACCOMODATE REQUIRED UNITS
        if(worstFitBlockLength<numberOfUnits){
            return -1;
        }

        int index = 0;

        //ALLOCATE THE MEMORY BLOCKS
        for(index = worstFitStartIndex;index<worstFitStartIndex+numberOfUnits;
            index++){

            worstFitMemoryBlocks[index].setPARENT_ID(p_id);
        }
        numberOfJumps+=index;
        return numberOfJumps;
    }
    public int fragmentCount(){
        return super.fragmentCount(worstFitMemoryBlocks);
    }

    public int deallocateFirstFitProcessMemory(int pid){
       return super.deallocateProcessMemory(pid,worstFitMemoryBlocks);
    }

    public void printWorstFitMemoryBlocks(){
        super.printMemoryBlocks(worstFitMemoryBlocks);
    }

}
