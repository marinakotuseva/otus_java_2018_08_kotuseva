package ru.otus;


import org.eclipse.jetty.server.Server;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.DBService.cache.CacheEngine;
import ru.otus.DBService.cache.MapElement;

public class Main {

    public static void main(String[] args) throws Exception {

        ServerHelper serverHelper = new ServerHelper();
        Server server = serverHelper.setServer();
        server.start();
        server.join();
    }
}
