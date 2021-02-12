package com.example.jjchatsqlite.model;

import com.example.jjchatapi.model.User;

public class Friend {
    private long _friendId;
    private String _username;

    public Friend() {
        setUsername("");
    }

    public Friend(long id, String username) {
        setFriendId(id);
        setUsername(username);
    }

    public Friend(User user) {
        setFriendId(user.getId());
        setUsername(user.getUsername());
    }

    public User getAsUser() {
        User result = new User();
        result.setId(getFriendId());
        result.setUsername(getUsername());
        return result;
    }

    public void setFriendId (long id) {
        _friendId = id;
    }

    public long getFriendId () {
        return _friendId;
    }

    public void setUsername (String username) {
        _username = username;
    }

    public String getUsername () {
        return _username;
    }
}