package com.example.jjchatapi.model;

import org.json.JSONObject;

public class CSharpException {
    private String _message;
    private String _exceptionMessage;
    private String _exceptionType;
    private String _stackTrace;

    public CSharpException() {

    }

    public CSharpException(JSONObject jsonCSharpException) throws Exception {
        setMessage(jsonCSharpException.getString("Message"));
        setExceptionMessage(jsonCSharpException.getString("ExceptionMessage"));
        setExceptionType(jsonCSharpException.getString("ExceptionType"));
        setStackTrace(jsonCSharpException.getString("StackTrace"));
    }

    public String getMessage() {
        return _message;
    }

    public void setMessage(String message) {
        _message = message;
    }

    public String getExceptionMessage() {
        return _exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        _exceptionMessage = exceptionMessage;
    }

    public String getExceptionType() {
        return _exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        _exceptionType = exceptionType;
    }

    public String getStackTrace() {
        return _stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        _stackTrace = stackTrace;
    }
}