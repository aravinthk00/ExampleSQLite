package com.example.examplesqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "usersdb";
    private static final String TABLE_Users = "userdetails";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_LOC = "location";
    private static final String KEY_DESG = "designation";

    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_Users + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
                + KEY_LOC + " TEXT"
                //+ KEY_DESG + " TEXT"
                + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Users);
// Create tables again
        onCreate(db);
    }

    // **** CRUD (Create, Read, Update, Delete) Operations ***** // // Adding new User Details
    void insertUser(String userName, String userDetail){

        SQLiteDatabase sqlite_db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, userName);
        contentValues.put(KEY_LOC, userDetail);

        long newRowId = sqlite_db.insert(TABLE_Users,null,contentValues);
        sqlite_db.close();
    }

    public ArrayList<HashMap<String,String>> getUsers(){

        SQLiteDatabase sqlite_db = this.getWritableDatabase();
        ArrayList<HashMap<String,String>> usersList = new ArrayList<>();
        String select_query = "SELECT * FROM " + TABLE_Users;
        Cursor cursor = sqlite_db.rawQuery(select_query,null);

        while (cursor.moveToNext()){
            HashMap<String,String> usersHashMap = new HashMap<>();
            usersHashMap.put("id", cursor.getString(0));
            usersHashMap.put("name", cursor.getString(1));
            usersHashMap.put("details", cursor.getString(2));
            usersList.add(usersHashMap);
        }
        return usersList;
    }

    void deleteUser(int userId){
        SQLiteDatabase sqlite_db = this.getWritableDatabase();
        sqlite_db.delete(TABLE_Users,KEY_ID + "= ?", new String[]{String.valueOf(userId)});
        sqlite_db.close();
    }

    int updateUser(int userId, String userName, String userDetails){

        SQLiteDatabase sqlite_db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, userName);
        contentValues.put(KEY_LOC, userDetails);

        int update_count = sqlite_db.update(TABLE_Users,contentValues, KEY_ID+" = ?",new String[]{String.valueOf(userId)});
        return userId;
    }
}

