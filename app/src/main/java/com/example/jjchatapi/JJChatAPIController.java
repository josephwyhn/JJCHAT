package com.example.jjchatapi;

import android.util.Log;

import com.example.jjchatapi.model.CSharpException;
import com.example.jjchatapi.model.ChatMessageList;
import com.example.jjchatapi.model.GenericResult;
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

        GenericResult<Boolean, JSONObject> result = _requestController.get(USER_API_DOMAIN, params);

        JSONObject jsonResponse = result.getEntity2();

        //Wenn Fehler von WebService
        if (result.getEntity1()){
            CSharpException exc = new CSharpException(jsonResponse);
            throw new Exception(exc.getExceptionMessage());
        }

        return new User(jsonResponse);
    }

    public User register(String username, String password) throws Exception {
        JSONObject jsonUser = new JSONObject();
        jsonUser.put("username", username);
        jsonUser.put("password", password);

        GenericResult<Boolean, JSONObject> result = _requestController.post(USER_API_DOMAIN, jsonUser);

        JSONObject jsonResponse = result.getEntity2();

        //Wenn Fehler von WebService
        if (result.getEntity1()){
            CSharpException exc = new CSharpException(jsonResponse);
            throw new Exception(exc.getExceptionMessage());
        }

        return new User(jsonResponse);
    }

    public User getFriend(String username) throws Exception {
        params.clear();
        params.put("username", username);

        GenericResult<Boolean, JSONObject> result = _requestController.get(FRIEND_API_DOMAIN, params);

        JSONObject jsonResponse = result.getEntity2();

        //Wenn Fehler von WebService
        if (result.getEntity1()){
            CSharpException exc = new CSharpException(jsonResponse);
            throw new Exception(exc.getExceptionMessage());
        }

        return new User(jsonResponse);
    }

    public void sendMessage(long receiver, String message) {
        //TODO: implementieren
    }

    public ChatMessageList getMessages(User user) {
        //TODO: implementieren
        return new ChatMessageList();
    }
}