package ru.otus.MessageSystem;

public class MessageSystemContext {

    private MessageSystem messageSystem;
    private Sender sender;

    public MessageSystemContext(MessageSystem messageSystem){
        this.messageSystem = messageSystem;
    }

    public MessageSystem getMessageSystem() {
        return messageSystem;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }
    public Sender getSender() {
        return sender;
    }

}
