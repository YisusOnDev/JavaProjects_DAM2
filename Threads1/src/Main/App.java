package Main;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import Threads.SumThread;

public class App {
    /**
    *   Ryzen 9 5900X (12-24) - 32gb ram 3600mhz - SSD.
    *   Best: 2 Threads.  
    *
    *   1 Thread    =    320500 nanoseconds
    *   2 Threads   =    287300 nanoseconds BEST
    *   4 Threads   =    401600 nanoseconds
    *   8 Threads   =    600900 nanoseconds
    *   12 Threads  =    834900 nanoseconds 
    *   16 Threads  =    1146500 nanoseconds (1 ms)
    *   20 Threads  =    1495200 nanoseconds (1 ms)
    *   24 Threads  =    1630200 nanoseconds (1 ms)
    *
    *   More threads more time...
    */
    public static void main(String[] args) throws InterruptedException {
        final int _PARTS = 50;
        int threadSumTotal = 0;

        int[] array = generateArray();
        SumThread[] threads = new SumThread[_PARTS];

        // Assign id and array part to specified thread.
        for (int i = 0; i < _PARTS; i++) {
            int[] subArray = Arrays.copyOfRange(array, i * (array.length / _PARTS),
                    (i + 1) * (array.length / _PARTS) - 1);
            threads[i] = new SumThread(i, subArray);
        }

        // Init time ns
        long start = System.nanoTime();

        // Initialize threads
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

        // Await threads
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }

        // Wait threads results
        for (int i = 0; i < threads.length; i++) {
            threadSumTotal += threads[i].getSum();
        }
        
        long end = System.nanoTime();
        long nsResult = (end-start);
        long msResult = TimeUnit.NANOSECONDS.toMillis(nsResult);
        
        System.out.println("Total: " + threadSumTotal);
        System.out.println("Time (ns): " + nsResult);
        System.out.println("Time (ms): " + msResult);
    }

    public static int[] generateArray() {
        int array[] = new int[10000];
        for (int i = 0; i < array.length; i++) {
            int rnd = (int) (Math.random() * 10);
            while (rnd == 0) {
                rnd = (int) (Math.random() * 10);
            }
            array[i] = rnd;
        }
        return array;
    }
}