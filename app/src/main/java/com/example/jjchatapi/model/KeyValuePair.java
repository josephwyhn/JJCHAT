package com.example.jjchatapi.model;

public class KeyValuePair<EKey, EValue> {
    private EKey _key;
    private EValue _value;

    public EKey getKey() {
        return _key;
    }

    public void setKey(EKey key) {
        _key = key;
    }

    public EValue getValue() {
        return _value;
    }

    public void setValue(EValue value) {
        _value = value;
    }

    public KeyValuePair() {

    }

    public KeyValuePair(EKey key, EValue value) {
        _key = key;
        _value = value;
    }
}