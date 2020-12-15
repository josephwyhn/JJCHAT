package com.example.jjchatapi;

import com.example.jjchatapi.model.Response;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class HttpRequestController {

    private HttpURLConnection _httpConnection;
    private String _baseUrl;

    public HttpRequestController(String baseUrl) {
        _baseUrl = baseUrl;
    }

    private URL getUrl(String apiDomain, Map<String, String> params) throws Exception {
        return new URL(getUrlString(apiDomain, params));
    }

    private String getUrlString(String apiDomain, Map<String, String> params) throws Exception {
        StringBuilder paramSb = new StringBuilder();

        if (params != null) {
            String encodedKey;
            String encodedValue;
            char prefix = '?';
            for (Map.Entry<String, String> param : params.entrySet()) {

                encodedKey = URLEncoder.encode(param.getKey(), "UTF-8");
                encodedValue = URLEncoder.encode(param.getValue(), "UTF-8");

                paramSb.append(prefix + encodedKey + "=" + encodedValue);
                prefix = '&';
            }
        }

        return _baseUrl + apiDomain + paramSb.toString();
    }

    private void openConnection(String apiDomain, Map<String, String> params) throws Exception {
        _httpConnection = null;
        _httpConnection = (HttpURLConnection) getUrl(apiDomain, params).openConnection();
        _httpConnection.setConnectTimeout(15 * 1000);
    }

    public Response post(String apiDomain, JSONObject content) throws Exception {
        openConnection(apiDomain, null);

        if (_httpConnection != null) {
            String jsonContentString = content.toString();
            byte[] postDataBytes = jsonContentString.getBytes("UTF-8");

            _httpConnection.setRequestMethod("POST");
            _httpConnection.setRequestProperty("Content-Type", "application/json");
            _httpConnection.setRequestProperty("Accept", "application/json");
            _httpConnection.setDoOutput(true);

            _httpConnection.getOutputStream().write(postDataBytes);

            handleResponseCode(_httpConnection.getResponseCode());

            BufferedReader inputReader = new BufferedReader(new InputStreamReader(_httpConnection.getInputStream(), "UTF-8"));
            StringBuffer responseBuffer = new StringBuffer();
            String inputLine;
            while ((inputLine = inputReader.readLine()) != null) {
                responseBuffer.append(inputLine);
            }
            inputReader.close();

            return new Response(new JSONObject(responseBuffer.toString()));
        } else {
            throw new Exception("Verbindung konnte nicht erstellt werden!");
        }
    }

    public Response get(String apiDomain, Map<String, String> params) throws Exception {
        openConnection(apiDomain, params);

        if (_httpConnection != null) {
            _httpConnection.setRequestMethod("GET");
            _httpConnection.setRequestProperty("Accept", "application/json");

            handleResponseCode(_httpConnection.getResponseCode());

            BufferedReader inputReader = new BufferedReader(new InputStreamReader(_httpConnection.getInputStream()));
            StringBuffer responseBuffer = new StringBuffer();
            String inputLine;
            while ((inputLine = inputReader.readLine()) != null) {
                responseBuffer.append(inputLine);
            }
            inputReader.close();

            return new Response(new JSONObject(responseBuffer.toString()));
        } else {
            throw new Exception("Verbindung konnte nicht erstellt werden!");
        }
    }

    private void handleResponseCode(int code) throws Exception{
        if (code == 500) {
            throw new Exception("ResponseCode: 500 - Internal server error -> See server log files!");
        } else if (code == 404) {
            throw new Exception("ResponseCode: 404 - URL '" + _httpConnection.getURL().toString() + "' not found!");
        }
    }
}