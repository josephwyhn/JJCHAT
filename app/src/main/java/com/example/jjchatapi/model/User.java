package com.example.jjchatapi.model;

import org.json.JSONObject;

public class User {
    private long _id;
    private String _username;
    private String _password;

    public User() {

    }

    public User(JSONObject jsonUser) throws Exception {
        setId(jsonUser.getLong("id"));
        setUsername(jsonUser.getString("username"));
        setPassword(jsonUser.getString("password"));
    }

    public void setId(long id) {
        _id = id;
    }

    public long getId() {
        return _id;
    }

    public void setUsername(String username) {
        _username = username;
    }

    public String getUsername() {
        return _username;
    }

    public void setPassword(String password) {
        _password = password;
    }

    public String getPassword() {
        return _password;
    }
}