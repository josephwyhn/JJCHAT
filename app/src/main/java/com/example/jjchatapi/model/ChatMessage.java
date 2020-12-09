package com.example.jjchatapi.model;

public class ChatMessage {
    private long _id;
    private String _sent;
    private String _message;
    private boolean _delivered;
    private long _sender;
    private long _receiver;

    public long getId() {
        return _id;
    }

    public String getSent() {
        return _sent;
    }

    public String getMessage() {
        return _message;
    }

    public boolean isDelivered() {
        return _delivered;
    }

    public long getSender() {
        return _sender;
    }

    public long getReceiver() {
        return _receiver;
    }

    public void setId(long id) {
        _id = id;
    }

    public void setSent(String sent) {
        _sent = sent;
    }

    public void setMessage(String message) {
        _message = message;
    }

    public void setDelivered(boolean delivered) {
        _delivered = delivered;
    }

    public void setSender(long sender) {
        _sender = sender;
    }

    public void setReceiver(long receiver) {
        _receiver = receiver;
    }
}
