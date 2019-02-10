package ru.otus;


import org.eclipse.jetty.server.Server;

public class Main {

    public static void main(String[] args) throws Exception {


        ServerHelper serverHelper = new ServerHelper();
        Server server = serverHelper.setServer();
        server.start();
        server.join();
    }
}
