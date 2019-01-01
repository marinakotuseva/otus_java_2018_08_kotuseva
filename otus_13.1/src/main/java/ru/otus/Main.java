package ru.otus;


public class Main {



    public static void main(String[] args) {

        int arrayLength = 10;
        int threadsMax = 4;

        int[] finalArray = Sorter.sort(arrayLength, threadsMax);
        System.out.println("Sorted array:");
        for (int i = 0; i < finalArray.length; i++) {
            System.out.print(finalArray[i] + ",");
        }
        System.out.println("\n");


    }
}
