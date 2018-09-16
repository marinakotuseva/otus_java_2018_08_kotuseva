package ru.otus;

//import java.lang.management.ManagementFactory;

public class Main {
    public static void main(String... args) throws InterruptedException {


            long startMemory = currentProccessMemory();
            System.out.println("Memory at the start: " + startMemory);
            //Thread.sleep(1000); //wait for 1 sec


            String newString = new String("");
            long afterStringMem = currentProccessMemory();
            //Thread.sleep(1000); //wait for 1 sec
            System.out.println("Memory after creating string " + afterStringMem);
            System.out.println("Creating empty string took: " + (afterStringMem - startMemory));

            Integer defaultArraySize = 10000;
            Object[] defaultArray = new Object[defaultArraySize];
            for (int i = 0; i < defaultArray.length; i++) {
                defaultArray[i] = new Object();
            }
            long afterArrayMem = currentProccessMemory();
            System.out.println("Memory after filling string " + afterArrayMem);
            //Thread.sleep(1000); //wait for 1 sec

            System.out.println("Creating new array took: " + (afterArrayMem - afterStringMem));
            System.out.println("For one element: " + ((afterArrayMem - afterStringMem)/defaultArray.length));


            Object[] biggerArray = new Object[defaultArraySize*20];
            for (int i = 0; i < biggerArray.length; i++) {
                biggerArray[i] = new Object();
            }
            long afterBiggerArrayMemory = currentProccessMemory();
            //Thread.sleep(1000); //wait for 1 sec

            System.out.println("Creating bigger array took: " + (afterBiggerArrayMemory - afterArrayMem));
            System.out.println("For one element of bigger array: " + ((afterBiggerArrayMemory - afterArrayMem)/biggerArray.length));

    }

    private static long currentProccessMemory() throws InterruptedException {
        System.gc();
        Thread.sleep(1000);
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }

}