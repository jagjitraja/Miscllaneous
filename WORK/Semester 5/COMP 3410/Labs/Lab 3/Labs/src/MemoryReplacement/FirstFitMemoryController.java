package MemoryReplacement;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FirstFitMemoryController extends MemoryController{

    private MemoryUnit [] firstFitMemoryBlocks;

    public FirstFitMemoryController(int memorySize, int sizePerUnit) {

        this.firstFitMemoryBlocks = new MemoryUnit[memorySize/sizePerUnit];

        for (int i = 0; i < memorySize/sizePerUnit; i++) {
            firstFitMemoryBlocks[i] = new MemoryUnit(sizePerUnit);
        }
    }


    public int findFirstFitMemoryLocation(int p_id,int numberOfUnits){
        //printFirstFitMemoryBlocks();
        for(int i = 0;i< firstFitMemoryBlocks.length;i++){

            if (!firstFitMemoryBlocks[i].isAllocated()){

                int endIndexForAllocation = i + numberOfUnits;

                if(endIndexForAllocation>firstFitMemoryBlocks.length){
                    return -1;
                }

                int j;
                //Loop through to ensure that there are enough units to accomodate the size
                for(j =i;j<=endIndexForAllocation&&j<firstFitMemoryBlocks.length;j++){
                    if(firstFitMemoryBlocks[j].isAllocated()){
                        break;
                    }
                }
                //ENSURE THAT THERE ARE ENOUGH BLOCKS FOR ALLOCATION AND
                // THEN BEGIN ALLOCATING BACK FROM THE WHERE THE FIRST FREE BLOCK
                // WAS FOUND
                if(j>=endIndexForAllocation){
                    for(j =i;j<endIndexForAllocation&&j<firstFitMemoryBlocks.length;j++){
                        firstFitMemoryBlocks[j].setPARENT_ID(p_id);
                    }
                    return i;
                }
            }
        }
        return -1;
    }

    public int deallocateFirstFitProcessMemory(int pid){
        return super.deallocateProcessMemory(pid,firstFitMemoryBlocks);
    }
    public int fragmentCount(){
        return super.fragmentCount(firstFitMemoryBlocks);
    }
    public void printFirstFitMemoryBlocks(){
        super.printMemoryBlocks(firstFitMemoryBlocks);
     }
}
