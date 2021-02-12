package com.example.jjchatsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jjchatapi.model.User;
import com.example.jjchatsqlite.model.Friend;

import java.util.ArrayList;

public class SqliteUserdataController extends SQLiteOpenHelper {
    public SqliteUserdataController(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE SavedLoginDetails (Id INTEGER PRIMARY KEY AUTOINCREMENT, Username VARCHAR(255), Password VARCHAR(255))");
        db.execSQL("CREATE TABLE SavedFriends (Id INTEGER PRIMARY KEY AUTOINCREMENT, FriendId BIGINT, Username VARCHAR(255))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS SavedLoginDetails");
        db.execSQL("DROP TABLE IF EXISTS SavedFriends");
    }

    public boolean insertSavedLoginDetails(String username, String password) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Username", username);
        cv.put("Password", password);

        return db.insert("SavedLoginDetails", null, cv) != -1;
    }

    public boolean deleteSavedLoginDetails() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM SavedLoginDetails", null);

        if (cursor.getCount() > 0) {
            db.execSQL("DELETE FROM SavedLoginDetails");
        }

        cursor.close();

        return true;
    }

    public User getSavedUserDetails() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM SavedLoginDetails", null);
        User user = null;
        if (cursor.getCount() == 1) {
            cursor.moveToNext();
            user = new User();
            user.setId(cursor.getInt(0));
            user.setUsername(cursor.getString(1));
            user.setPassword(cursor.getString(2));
        } else if (cursor.getCount() > 1){
            deleteSavedLoginDetails();
        }

        cursor.close();

        return user;
    }

    public boolean insertSavedFriends(long id, String username) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("FriendId", id);
        cv.put("Username", username);

        return db.insert("SavedFriends", null, cv) != -1;
    }

    public boolean deleteSavedFriend(Long id) {
        boolean result = true;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM SavedFriends WHERE FriendId = ?", new String[]{id.toString()});

        if (cursor.getCount() > 0) {
            result = db.delete("SavedFriends", "FriendId = ?", new String[]{id.toString()}) != -1;
        }

        cursor.close();

        return result;
    }

    public ArrayList<Friend> getSavedFriends() {
        ArrayList<Friend> result = new ArrayList<>();

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM SavedFriends", null);

        while (cursor.moveToNext()) {
            result.add(new Friend(cursor.getLong(1), cursor.getString(2)));
        }

        cursor.close();

        return result;
    }
}