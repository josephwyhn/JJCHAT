package com.example.jjchatapi.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ChatMessageList {
    private List<ChatMessage> _sentMessages;
    private List<ChatMessage> _receivedMessages;

    public ChatMessageList() {
        setSentMessages(new ArrayList<ChatMessage>());
        setReceivedMessages(new ArrayList<ChatMessage>());
    }

    public ChatMessageList(JSONObject jsonChatMessageList) throws Exception {
        ArrayList<ChatMessage> sent = new ArrayList<>();
        ArrayList<ChatMessage> received = new ArrayList<>();

        JSONArray jsonSentMessages = jsonChatMessageList.getJSONArray("sentMessages");
        JSONArray jsonReceivedMessages = jsonChatMessageList.getJSONArray("receivedMessages");

        for (int i = 0; i < jsonSentMessages.length(); i++) {
            JSONObject jsonSentMessage = jsonSentMessages.getJSONObject(i);
            sent.add(new ChatMessage(jsonSentMessage));
        }

        for (int i = 0; i < jsonReceivedMessages.length(); i++) {
            JSONObject jsonReceivedMessage = jsonReceivedMessages.getJSONObject(i);
            received.add(new ChatMessage(jsonReceivedMessage));
        }

        setSentMessages(sent);
        setReceivedMessages(received);
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