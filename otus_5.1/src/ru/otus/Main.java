package ru.otus;

public class Main {

    public static void main(String[] args) {
        int arrSize = 5*1000*1000;
            while (true) {
                System.out.println("Staring creating array...");
                Object[] myArray = new Object[arrSize];
                for (int j = 0; j < arrSize; j++) {
                    myArray[j] = new String("Hello, World");
                }
                GarbageCollector.runGC();
            }
        //}
    }
}
