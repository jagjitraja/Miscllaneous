package Threads_Assignment_4;

public class MultiThread {

    //VARIABLES SHARED BETWEEN MAIN AND CHILD THREADS
    private static double average;
    private static double min;
    private static double max;

    private static void setAverage(double average){
        MultiThread.average = average;
    }

    private static void setMax(double max) {
        MultiThread.max = max;
    }

    private static void setMin(double min) {
        MultiThread.min = min;
    }

    public static void main(String[] args) {

        if(args.length==0){
            System.out.println("Please enter numeric values seperated by spaces when running");
            return;
        }
        //RUNNABLES TO PERFORM THE SPECIFIC FUNTIONS OF AVERAGE< MIN AND MAX AND SET IT TO THE
        //SHARED VARIABLES
        Runnable averageRunnable = () -> {
            double averageR;
            int sum = 0;
            for (String arg : args) {
                try {
                    sum += Integer.parseInt(arg);

                } catch (NumberFormatException number) {
                    break;
                }
            }
            averageR = (double)sum/args.length;
            MultiThread.setAverage(averageR);
        };

        Runnable maxRunnable = () -> {
            int minR = Integer.parseInt(args[0]);

            for (String arg : args) {
                try {
                    if (Integer.parseInt(arg) < minR) {
                        minR = Integer.parseInt(arg);
                    }
                } catch (NumberFormatException number) {
                    break;
                }
            }
            MultiThread.setMin(minR);
        };

        Runnable minRunnable = () -> {
            int maxR = Integer.parseInt(args[0]);

            for (String arg : args) {
                try {
                    if (Integer.parseInt(arg) > maxR) {
                        maxR = Integer.parseInt(arg);
                    }
                } catch (NumberFormatException number) {
                    break;
                }
            }
            MultiThread.setMax(maxR);
        };


        Thread averageCalcThread = new Thread(averageRunnable);
        Thread minCalcThread = new Thread(minRunnable);
        Thread maxCalcThread = new Thread(maxRunnable);

        averageCalcThread.start();
        minCalcThread.start();
        maxCalcThread.start();

        //MAKES MAIN THREAD WAIT FOR ALL THE OTHER THREADS TO FINISH
        try {
            averageCalcThread.join();
            minCalcThread.join();
            maxCalcThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(average +" MAIN AVERAGE");
        System.out.println(min+" MAIN MIN");
        System.out.println(max+" MAIN MAX");
    }
}
//http://journals.ecs.soton.ac.uk/java/tutorial/java/threads/simple.html
//https://stackoverflow.com/questions/15956231/what-does-this-thread-join-code-mean
///http://www.henryxi.com/java-thread-join-example