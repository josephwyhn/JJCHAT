package com.example.jjchatapi;

import com.example.jjchatapi.model.ChatMessage;
import com.example.jjchatapi.model.ChatMessageList;
import com.example.jjchatapi.model.User;

import java.util.ArrayList;
import java.util.List;

public class JJChatAPIController {

    public JJChatAPIController(){

    }

    public User login(String username, String password){
        return null;
    }

    public User register(String username, String password){
        return null;
    }

    public User getFriend(String username) { return null; }

    public void sendMessage(ChatMessage message){

    }

    public ChatMessageList getMessages(User user){
        return new ChatMessageList();
    }
}