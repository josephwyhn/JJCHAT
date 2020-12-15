package com.example.jjchatapi.model;

import org.json.JSONObject;

public class ResponseMessage {
    private String _message;

    public ResponseMessage() {

    }

    public ResponseMessage(JSONObject jsonResponseMessageObject) throws Exception {
        setMessage(jsonResponseMessageObject.getString("message"));
    }

    public void setMessage(String message) {
        _message = message;
    }

    public String getMessage() {
        return _message;
    }
}
