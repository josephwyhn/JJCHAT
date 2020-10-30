package com.example.jjchatapi.model;

public class ChatMessage {
    public long id;
    public String sent;
    public String message;
    public boolean delivered;
    public User sender;
    public User receiver;

    public long getId() {
        return id;
    }

    public String getSent() {
        return sent;
    }

    public String getMessage() {
        return message;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setSent(String sent) {
        this.sent = sent;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }
}
