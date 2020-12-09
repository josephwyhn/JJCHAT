package com.example.jjchatapi;

import com.example.jjchatapi.model.KeyValuePair;

import java.io.IOException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class HttpRequestController {

    private HttpsURLConnection _httpConnection;
    private String _baseUrl;

    public HttpRequestController(String baseUrl) throws IOException {
        _baseUrl = baseUrl;
    }

    private URL getUrl(String apiDomain, List<KeyValuePair<String, String>> params) throws IOException {
        return new URL(getUrlString(apiDomain, params));
    }

    private String getUrlString(String apiDomain, List<KeyValuePair<String, String>> params) {
        String paramString = "";

        if (params != null) {
            paramString = "?";
            Boolean first = true;
            for (KeyValuePair<String, String> param : params) {
                if (!first) {
                    paramString += "&" + param.getKey() + "=" + param.getValue();
                } else {
                    first = false;
                    paramString += param.getKey() + "=" + param.getValue();
                }
            }
        }

        return _baseUrl + apiDomain + paramString;
    }

    private void openConnection(String apiDomain, List<KeyValuePair<String, String>> params) throws IOException {
        _httpConnection = (HttpsURLConnection) getUrl(apiDomain, params).openConnection();
        _httpConnection.setConnectTimeout(15 * 1000);
    }

    public Object post(String apiDomain, Object content) throws Exception {
        openConnection(apiDomain, null);

        if (_httpConnection != null) {
            _httpConnection.setRequestMethod("POST");
        }
        else{
            throw new Exception("Verbindung"); //TODO: Exception werfen, dass Verbindung nicht erstellt werden konnte
        }

        return null;
    }

    public Object get(String apiDomain, List<KeyValuePair<String, String>> params) throws Exception {
        openConnection(apiDomain, params);

        if (_httpConnection != null) {
            _httpConnection.setRequestMethod("GET");
        }
        //TODO: Exception werfen, dass Verbindung nicht erstellt werden konnte

        return null;
    }
}