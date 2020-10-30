package com.example.jjchatapi.model;

public class User {
    private long _id;
    private String _username;
    private String _password;

    public void setId(long id){
        _id = id;
    }

    public long getId(){
        return _id;
    }

    public void setUsername(String username){
        _username = username;
    }

    public String getUsername(){
        return _username;
    }

    public void setPassword(String password){
        _password = password;
    }

    public String getPassword(){
        return _password;
    }
}