package ru.otus;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        int arrSize = 5*1000*1000;
        long started = System.currentTimeMillis();
        boolean cont = true;
        while (cont) {
            try {

                System.out.println("Staring creating array...");
                List<Object> myList = new ArrayList<Object>();
                for (int j = 0; j < arrSize; j++) {
                    //System.out.println(j);
                    myList.add(new String("Hello, World"));
                }
                for (int i = 0; i < arrSize / 2; i++) {
                    myList.remove(myList.size() - 1);
                    //System.out.println(i);
                }

                GarbageCollector.runGC();
            } catch(OutOfMemoryError e) {
                //e.getMessage();
                System.out.println("ERROR: " + e.getMessage());
                System.out.println("Application lived " + (System.currentTimeMillis() - started) + " ms.");
                cont = false;
            }
        }
    }

}

