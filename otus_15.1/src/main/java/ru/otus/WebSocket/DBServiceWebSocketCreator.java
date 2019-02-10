package ru.otus.WebSocket;

import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;
import ru.otus.DBInitializer;
import ru.otus.DBService.DBService;
import ru.otus.MessageSystem.MessageSystem;
import ru.otus.MessageSystem.MessageSystemContext;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DBServiceWebSocketCreator implements WebSocketCreator {

    private MessageSystem messageSystem;
    private MessageSystemContext messageSystemContext;
    private DBService dbService;
    private Set<DBServiceWebSocket> userLiist;

    public DBServiceWebSocketCreator() {
        this.messageSystem = new MessageSystem();
        this.messageSystemContext = new MessageSystemContext(this.messageSystem);
        this.dbService = DBInitializer.InitializeDB();
        this.messageSystemContext.setSender(this.dbService);
        this.userLiist = Collections.newSetFromMap(new ConcurrentHashMap<>());
    }


    @Override
    public Object createWebSocket(ServletUpgradeRequest servletUpgradeRequest, ServletUpgradeResponse servletUpgradeResponse) {
        return null;
    }
}
