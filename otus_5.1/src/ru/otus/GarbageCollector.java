package ru.otus;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

public class GarbageCollector {
    public static void runGC(){

        List<GarbageCollectorMXBean> garbageCollectors = ManagementFactory.getGarbageCollectorMXBeans();
        for(GarbageCollectorMXBean gc:garbageCollectors){
            System.out.println("Name of GC: "+ gc.getName() + ", Collection #"+ gc.getCollectionCount() + ", Collection took "+ gc.getCollectionTime()/1000/60 + " minutes.");
        }
    }
}
