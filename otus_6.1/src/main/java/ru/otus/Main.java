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

//        List<SoftReference<BigObject>> references = new ArrayList<>(cacheSize);
//
  //      for (int k = 0; k < cacheSize; k++) {
    //        references.add(new SoftReference<>(new BigObject()));
      //  }
        //System.out.println("Soft references before GC: " + cacheSize);

        //Thread.sleep(1000);
        //System.gc();

        //int sum = 0;
        //for (int k = 0; k < cacheSize; k++) {
        //    if (references.get(k).get() != null) sum++;
        //}

        //System.out.println("Soft references after GC: " + sum);
    }

}





