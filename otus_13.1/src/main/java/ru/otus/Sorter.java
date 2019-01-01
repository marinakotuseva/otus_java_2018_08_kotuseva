package ru.otus;

import java.util.Arrays;
import java.util.List;

public class Sorter {

    static class SortThread implements Runnable {
        String threadName;
        int[] array;

        public SortThread(String threadName, int[] array) {
            this.threadName = threadName;
            this.array = array;
        }

        @Override
        public void run() {
            Arrays.sort(array);
        }
    }

    static class BubbleSortThread implements Runnable {
        String threadName;
        int[] array;

        public BubbleSortThread(String threadName, int[] array) {
            this.threadName = threadName;
            this.array = array;
        }

        @Override
        public void run() {
            int inputLength = array.length;
            int temp;
            boolean is_sorted;

            for (int i = 0; i < inputLength; i++) {

                is_sorted = true;

                for (int j = 1; j < (inputLength - i); j++) {

                    if (array[j - 1] > array[j]) {
                        temp = array[j - 1];
                        array[j - 1] = array[j];
                        array[j] = temp;
                        is_sorted = false;
                    }

                }

                // is sorted? then break it, avoid useless loop.
                if (is_sorted) break;
            }
        }
    }

    static int[] sort(int[] array, int threadsMax){

        // Divide array for threads
        List subArrays = ArraysHelper.divideArray(array, threadsMax);
        int[] array1 = (int[]) subArrays.get(0);
        int[] array2 = (int[]) subArrays.get(1);
        int[] array3 = (int[]) subArrays.get(2);
        int[] array4 = (int[]) subArrays.get(3);

        // Run sorting
        Thread t1 =new Thread(new SortThread("t1",  array1));
        Thread t2 = new Thread(new SortThread("t2", array2));
        Thread t3 = new Thread(new BubbleSortThread("t3", array3));
        Thread t4 = new Thread(new BubbleSortThread("t4", array4));
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("The end of sorting");

        // Merge into one
        int[] array12 = ArraysHelper.merge(array1, array2);
        int[] array34 = ArraysHelper.merge(array3, array4);
        int[] finalArray = ArraysHelper.merge(array12, array34);
        return finalArray;
    }


}
