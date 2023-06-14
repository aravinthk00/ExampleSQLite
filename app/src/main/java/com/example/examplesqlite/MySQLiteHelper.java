package com.example.examplesqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.HashMap;

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final int SQLite_DB_Version = 1;
    public static final String SQLite_DB_Name = " User_SQLite_DataBase";
    public static final String TABLE_Users = " User_Details";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_DETAILS = "details";


    public MySQLiteHelper(@Nullable Context context) {
        super(context, SQLite_DB_Name, null, SQLite_DB_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        String CREATE_TABLE = "CREATE TABLE " + TABLE_Users + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
                + KEY_DETAILS + " TEXT"
                + ")";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + TABLE_Users);

        onCreate(sqLiteDatabase);
    }

    void insertUser(String userName, String userDetail){

        SQLiteDatabase sqlite_db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, userName);
        contentValues.put(KEY_DETAILS, userDetail);

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
        contentValues.put(KEY_DETAILS, userDetails);

        int update_count = sqlite_db.update(TABLE_Users,contentValues, KEY_ID+" = ?",new String[]{String.valueOf(userId)});
        return userId;
    }
}
