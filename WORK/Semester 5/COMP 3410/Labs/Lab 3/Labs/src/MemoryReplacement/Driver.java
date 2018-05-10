package MemoryReplacement;

import java.util.Random;

public class Driver {

    static Random random = new Random();

    public static void main(String[] args) {

        final int memorySize = 256;//KB
        final int sizePerPage = 2;//KB

        //WORST FIT MEMORY BLOCKS SIMULATION
        //--------------------------------------------------------------------------------------------------------------------------------------
        WorstFitMemoryController worstFitMemoryController = new WorstFitMemoryController(memorySize, sizePerPage);

        int fails = 0;
        int numberOfHops = 0;
        for (int i = 0; i < 10000; i++) {
            int p_id;
            int requestedSize;
            p_id = generateProcessID();

            if (toAllocateOrDeallocate()) {
                requestedSize = generateSizeRequest();
                numberOfHops+=worstFitMemoryController.findWorstFitMemoryLocation(p_id, requestedSize);
            } else {
                if (worstFitMemoryController.deallocateFirstFitProcessMemory(p_id) < 0) {
                    fails++;
                }
                continue;
            }
            //worstFitMemoryController.printWorstFitMemoryBlocks();
        }
        //--------------------------------------------------------------------------------------------------------------------------------------
        System.out.println("\n\nWORST FIT FAILS COUNT=> "+fails);
        System.out.println("WORST FIT FRAGMENT COUNT => "+worstFitMemoryController.fragmentCount());
        System.out.println("WORST FIT NUMBER OF HOPS => "+numberOfHops);

        fails = 0;
        numberOfHops=0;
        //FIRST FIT MEMORY
        //--------------------------------------------------------------------------------------------------------------------------------------
        FirstFitMemoryController firstFitMemoryController =
                new FirstFitMemoryController(memorySize, sizePerPage);

        for (int i = 0; i < 10000; i++) {
            int p_id;
            int requestedSize;
            p_id = generateProcessID();

            if (toAllocateOrDeallocate()) {
                requestedSize = generateSizeRequest();
                numberOfHops+=(firstFitMemoryController.findFirstFitMemoryLocation(p_id, requestedSize));
            } else {
                if (firstFitMemoryController.deallocateFirstFitProcessMemory(p_id) < 0) {
                    fails++;
                }
            }
            //firstFitMemoryController.printFirstFitMemoryBlocks();
        }

        System.out.println("\n\nFIRST FIT FAILS COUNT=> "+fails);
        System.out.println("FIRST FIT FRAGMENT COUNT => "+firstFitMemoryController.fragmentCount());
        System.out.println("FIRST FIT NUMBER OF HOPS => "+numberOfHops);
        //--------------------------------------------------------------------------------------------------------------------------------------


        //BEST FIT MEMORY
        //--------------------------------------------------------------------------------------------------------------------------------------

        fails = 0;
        numberOfHops=0;
        //NOT BERY EFFECTIVE AND EFFICIENT. MISSE ALLOCATING SOMETIMES WHEN THE MEMORY ALLOCATION WOULD LEAVE A BIG ALLOCATION REMAINDER
        BestFitMemoryController bestFitMemoryController = new BestFitMemoryController(memorySize, sizePerPage);

        for (int i = 0; i < 10000; i++) {
            int p_id;
            int requestedSize;
            p_id = generateProcessID();
            if (toAllocateOrDeallocate()) {
                requestedSize = generateSizeRequest();
                numberOfHops+=(bestFitMemoryController.findBestFitMemoryLocation(p_id, requestedSize));
            } else {
                if (bestFitMemoryController.deallocateBestFitProcessMemory(p_id) < 0) {
                    fails++;
                }
                continue;
            }
            //bestFitMemoryController.printBestFitMemoryBlocks();
        }

        System.out.println("\n\nBEST FIT FAILS COUNT=> "+fails);
        System.out.println("BEST FIT FRAGMENT COUNT => "+bestFitMemoryController.fragmentCount());
        System.out.println("BEST FIT NUMBER OF HOPS => "+numberOfHops);
    }

    private static int generateSizeRequest() {
        return random.nextInt(8) + 3;
    }

    private static boolean toAllocateOrDeallocate() {
        //RETURN TRUE IF TO ALLOCATE
        return random.nextInt() % 2 == 0;

    }

    private static int generateProcessID() {
        return random.nextInt(21);
    }

}
