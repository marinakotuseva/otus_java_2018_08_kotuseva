package ru.otus;


import org.eclipse.jetty.server.Server;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) throws Exception {

        int addToCacheSize = 13;

        ApplicationContext context = new ClassPathXmlApplicationContext("SpringBeans.xml");
        CacheEngine<Integer, String> cache = context.getBean("cacheEngine", CacheEngine.class);
        System.out.println("Created cache with " + cache.size() + " elements");

        for (int i =0; i< addToCacheSize; i++){
            cache.add( new MapElement<>(i, new String("Hello, world!")));
        }
        System.out.println("Added to cache " + addToCacheSize + " elements");


        ServerHelper serverHelper = new ServerHelper();
        Server server = serverHelper.setServer();
        server.start();
        server.join();
    }
}
