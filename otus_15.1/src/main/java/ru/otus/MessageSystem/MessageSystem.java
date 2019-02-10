package ru.otus.MessageSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageSystem {
    private final List<Thread> workers;
    private final Map<Address, LinkedBlockingQueue<Message>> messagesMap;

    public MessageSystem(){
        this.messagesMap = new HashMap<>();
        this.workers = new ArrayList<>();
    }
}
