package MemoryReplacement;

import java.util.HashMap;

public class BestFitMemoryController extends MemoryController {

    private MemoryUnit[] bestFitMemoryBlocks;
    private int sizePerUnit;
    int blocksAllocated = 0;

    public BestFitMemoryController(int memorySize, int sizePerUnit) {

        this.bestFitMemoryBlocks = new MemoryUnit[memorySize / sizePerUnit];
        this.sizePerUnit = sizePerUnit;

        for (int i = 0; i < memorySize / sizePerUnit; i++) {
            bestFitMemoryBlocks[i] = new MemoryUnit(sizePerUnit);
        }

    }

    public int findBestFitMemoryLocation(int p_id, int numberOfUnits) {
        int freeBlockStartIndex = 0;
        int prevfreeBlockLength = -1;

        int numberOfJumps = bestFitMemoryBlocks.length;

        for(int i = 0; i< bestFitMemoryBlocks.length; i++){
            if (!bestFitMemoryBlocks[i].isAllocated()){
                int j = i;
                int tempBlockLength = 0;
                //LOOP THROUGH TO GET THE SIZE OF THE EMPTY BLOCK
                while (j<bestFitMemoryBlocks.length&&!bestFitMemoryBlocks[j].isAllocated()){
                    tempBlockLength++;
                    numberOfJumps++;
                    j++;
                }

                if(prevfreeBlockLength==-1||tempBlockLength<=prevfreeBlockLength&&tempBlockLength>numberOfUnits){
                    prevfreeBlockLength = tempBlockLength;
                    freeBlockStartIndex = i;

                }else if(tempBlockLength%numberOfUnits==0||tempBlockLength%numberOfUnits<prevfreeBlockLength%numberOfUnits){
                    for(int index = freeBlockStartIndex+1;index<=freeBlockStartIndex+numberOfUnits;
                        index++){
                        bestFitMemoryBlocks[index].setPARENT_ID(p_id);
                    }
                    numberOfJumps+=numberOfUnits;
                    return numberOfJumps;
                }
            }
        }
        //IF THE LARGEST EMPTY BLOCK IS NOT BIG ENOUGH TO ACCOMODATE REQUIRED UNITS
        if(prevfreeBlockLength<numberOfUnits){
            return -1;
        }

        int index = 0;

        //ALLOCATE THE MEMORY BLOCKS
        for(index = freeBlockStartIndex;index<freeBlockStartIndex+numberOfUnits;
            index++){

            bestFitMemoryBlocks[index].setPARENT_ID(p_id);
        }
        numberOfJumps+=index;
        return numberOfJumps;
    }


    public int deallocateBestFitProcessMemory(int pid) {
        return super.deallocateProcessMemory(pid, bestFitMemoryBlocks);
    }

    public void printBestFitMemoryBlocks() {
        super.printMemoryBlocks(bestFitMemoryBlocks);
    }

    public int fragmentCount() {
        return super.fragmentCount(bestFitMemoryBlocks);
    }
}
