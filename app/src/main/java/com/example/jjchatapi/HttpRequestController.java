package com.example.jjchatapi;

import com.example.jjchatapi.model.GenericResult;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class HttpRequestController {

    private HttpsURLConnection _httpConnection;
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
        _httpConnection = (HttpsURLConnection) getUrl(apiDomain, params).openConnection();
        _httpConnection.setConnectTimeout(15 * 1000);
    }

    public GenericResult<Boolean, JSONObject> post(String apiDomain, JSONObject content) throws Exception {
        openConnection(apiDomain, null);

        GenericResult<Boolean, JSONObject> result = new GenericResult<>();

        if (_httpConnection != null) {
            String jsonContentString = content.toString();
            byte[] postDataBytes = jsonContentString.getBytes("UTF-8");

            _httpConnection.setRequestMethod("POST");
            _httpConnection.setRequestProperty("Content-Type", "application/json");
            _httpConnection.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            _httpConnection.setDoOutput(true);

            _httpConnection.getOutputStream().write(postDataBytes);

            int responseCode = _httpConnection.getResponseCode();

            BufferedReader inputReader = new BufferedReader(new InputStreamReader(_httpConnection.getInputStream(), "UTF-8"));
            StringBuilder responseBuilder = new StringBuilder();
            String inputLine;
            while ((inputLine = inputReader.readLine()) != null) {
                responseBuilder.append(inputLine);
            }
            inputReader.close();

            result.setEntity1(responseCode == 500);
            result.setEntity2(new JSONObject(responseBuilder.toString()));
        } else {
            throw new Exception("Verbindung konnte nicht erstellt werden!");
        }

        return result;
    }

    public GenericResult<Boolean, JSONObject> get(String apiDomain, Map<String, String> params) throws Exception {
        openConnection(apiDomain, params);

        GenericResult<Boolean, JSONObject> result = new GenericResult<>();

        if (_httpConnection != null) {
            _httpConnection.setRequestMethod("GET");
            _httpConnection.setRequestProperty("accept", "application/json");

            int responseCode = _httpConnection.getResponseCode();

            BufferedReader inputReader = new BufferedReader(new InputStreamReader(_httpConnection.getInputStream(), "UTF-8"));
            StringBuilder responseBuilder = new StringBuilder();
            String inputLine;
            while ((inputLine = inputReader.readLine()) != null) {
                responseBuilder.append(inputLine);
            }
            inputReader.close();

            result.setEntity1(responseCode == 500);
            result.setEntity2(new JSONObject(responseBuilder.toString()));
        } else {
            throw new Exception("Verbindung konnte nicht erstellt werden!");
        }

        return result;
    }
}