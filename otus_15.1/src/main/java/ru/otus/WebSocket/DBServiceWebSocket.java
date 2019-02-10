package ru.otus.WebSocket;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;
import ru.otus.MessageSystem.MessageSystem;
import ru.otus.MessageSystem.MessageSystemContext;

import java.util.ArrayList;

@WebSocket
public class DBServiceWebSocket {
    private MessageSystemContext messageSystemContext;
    private MessageSystem messageSystem;
    private Session session;

    public DBServiceWebSocket(MessageSystemContext messageSystemContext){
        this.messageSystemContext = messageSystemContext;
        this.messageSystem = messageSystemContext.getMessageSystem();

    }
;}
