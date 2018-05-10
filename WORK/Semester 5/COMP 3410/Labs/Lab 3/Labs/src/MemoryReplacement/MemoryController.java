package MemoryReplacement;

public class MemoryController {
    public int fragmentCount(MemoryUnit [] memoryUnits){

        int fragmentCount = 0;
        for (int i = 0; i <memoryUnits.length ; i++) {
            if (!memoryUnits[i].isAllocated()){
                fragmentCount++;
            }
        }
        return fragmentCount;
    }

    public void printMemoryBlocks(MemoryUnit [] memoryUnits){
        System.out.println("=========================================================================================");
        for (int i = 0; i < memoryUnits.length; i++) {
            //if(memoryUnits[i].isAllocated())
                System.out.println(memoryUnits[i] +"\t\t\t\tINDEX=> "+i);
        }
        System.out.println("=========================================================================================");
    }

    public int deallocateProcessMemory(int pid,MemoryUnit [] memoryUnits){
        boolean foundProcessMemory = false;
        for (int i = 0; i < memoryUnits.length; i++) {

            if(memoryUnits[i].getPARENT_ID()==pid){
                memoryUnits[i].setPARENT_ID(-1);
                foundProcessMemory=true;
            }
        }
        return foundProcessMemory?1:-1;
    }
}
