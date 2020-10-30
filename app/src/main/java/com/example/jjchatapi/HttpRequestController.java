package com.example.jjchatapi;

import java.io.IOException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpRequestController {

    private HttpsURLConnection _connection;

    public void openConnection(String url) throws IOException {
        _connection = (HttpsURLConnection) new URL(url).openConnection();
    }

    public Object post(Object content){
        return null;
    }

    public Object get(String[] args){
        return null;
    }
}