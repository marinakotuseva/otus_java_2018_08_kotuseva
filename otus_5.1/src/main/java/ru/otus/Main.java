package ru.otus;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        int arrSize = 5*1000*1000;
        while (true) {
            System.out.println("Staring creating array...");
            List<Object> myList = new ArrayList<Object>();
            for (int j = 0; j < arrSize; j++) {
                myList.add(new String("Hello, World"));
            }
            for (int i = 0; i < arrSize / 2; i++) {
                myList.remove(myList.size()-1);
            }

            GarbageCollector.runGC();
        }
    }
}
