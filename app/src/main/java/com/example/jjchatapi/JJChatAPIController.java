package com.example.jjchatapi;

import com.example.jjchatapi.model.ChatMessage;
import com.example.jjchatapi.model.ChatMessageList;
import com.example.jjchatapi.model.GenericResult;
import com.example.jjchatapi.model.Response;
import com.example.jjchatapi.model.ResponseMessage;
import com.example.jjchatapi.model.User;

import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class JJChatAPIController {

    private HttpRequestController _requestController;

    private static final String USER_API_DOMAIN = "User/";
    private static final String FRIEND_API_DOMAIN = "Friend/";
    private static final String CHAT_API_DOMAIN = "Chat/";

    private Map<String, String> params;

    public JJChatAPIController(String baseUrl) {
        _requestController = new HttpRequestController(formatBaseUrl(baseUrl));
        params = new LinkedHashMap<String, String>();
    }

    private String formatBaseUrl(String baseUrl){
        String result = baseUrl;

        if(!result.endsWith("/"))
            result += '/';

        return result;
    }

    public User login(String username, String password) throws Exception {
        params.clear();
        params.put("username", username);
        params.put("password", password);

        Response response = _requestController.get(USER_API_DOMAIN, params);
        handleError(response);

        return new User(response.getResponseObject());
    }

    public User register(String username, String password) throws Exception {
        JSONObject jsonUser = new JSONObject();
        jsonUser.put("username", username);
        jsonUser.put("password", password);

        Response response = _requestController.post(USER_API_DOMAIN, jsonUser);
        handleError(response);

        return new User(response.getResponseObject());
    }

    public User getFriend(String username) throws Exception {
        params.clear();
        params.put("username", username);

        Response response = _requestController.get(FRIEND_API_DOMAIN, params);
        handleError(response);

        return new User(response.getResponseObject());
    }

    public ChatMessage sendMessage(long sender, long receiver, String message) throws Exception {
        JSONObject jsonChatMessage = new JSONObject();
        jsonChatMessage.put("message", message);
        jsonChatMessage.put("sender", sender);
        jsonChatMessage.put("receiver", receiver);

        Response response = _requestController.post(CHAT_API_DOMAIN, jsonChatMessage);
        handleError(response);

        return new ChatMessage(response.getResponseObject());
    }

    public ChatMessageList getMessages(String username, String password) throws Exception {
        params.clear();
        params.put("username", username);
        params.put("password", password);

        Response response = _requestController.get(CHAT_API_DOMAIN, params);
        handleError(response);

        return new ChatMessageList(response.getResponseObject());
    }

    private void handleError(Response response) throws Exception {
        if (response.isError()){
            ResponseMessage msg = new ResponseMessage(response.getResponseObject());
            throw new Exception(msg.getMessage());
        }
    }
}