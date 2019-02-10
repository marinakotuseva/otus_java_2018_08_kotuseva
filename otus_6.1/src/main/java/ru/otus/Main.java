package ru.otus;

import java.lang.ref.SoftReference;
import java.util.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        int cacheSize = 5;
        long time = 1000;
        int addToCacheSize = 13;

        CacheEngine<Integer, String> cache = new CacheEngine<>(cacheSize, time);
        System.out.println("Created cache with " + cacheSize + " elements");

        for (int i =0; i< addToCacheSize; i++){
            cache.add( new MapElement<>(i, new String("Hello, world!")));
        }
        System.out.println("Added to cache " + addToCacheSize + " elements");


        Thread.sleep(1000);

        for (int i =0; i< addToCacheSize; i++){
            System.out.println(cache.get(i));
        }
    }

}





