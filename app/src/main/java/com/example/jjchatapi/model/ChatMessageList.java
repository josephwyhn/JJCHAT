package com.example.jjchatapi.model;

import java.util.ArrayList;
import java.util.List;

public class ChatMessageList {
    private List<ChatMessage> _sentMessages;
    private List<ChatMessage> _receivedMessages;

    public ChatMessageList() {
        setSentMessages(new ArrayList<ChatMessage>());
        setReceivedMessages(new ArrayList<ChatMessage>());
    }

    public ChatMessageList(List<ChatMessage> sentMessages, List<ChatMessage> receivedMessages) {
        setSentMessages(sentMessages);
        setReceivedMessages(receivedMessages);
    }

    public List<ChatMessage> getSentMessages() {
        return _sentMessages;
    }

    public void setSentMessages(List<ChatMessage> sentMessages) {
        _sentMessages = sentMessages;
    }

    public List<ChatMessage> getReceivedMessages() {
        return _receivedMessages;
    }

    public void setReceivedMessages(List<ChatMessage> receivedMessages) {
        _receivedMessages = receivedMessages;
    }
}