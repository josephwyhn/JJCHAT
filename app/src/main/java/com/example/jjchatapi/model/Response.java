package com.example.jjchatapi.model;

import org.json.JSONObject;

public class Response {
    private boolean _isError;
    private JSONObject _responseObject;

    public Response() {

    }

    public Response (JSONObject jsonResponseObject) throws Exception {
        setError(jsonResponseObject.getBoolean("isException"));
        setResponseObject(jsonResponseObject.getJSONObject("responseObject"));
    }

    public void setError(boolean isError) {
        _isError = isError;
    }

    public boolean isError() {
        return _isError;
    }

    public void setResponseObject(JSONObject responseObject) {
        _responseObject = responseObject;
    }

    public JSONObject getResponseObject() {
        return _responseObject;
    }
}
