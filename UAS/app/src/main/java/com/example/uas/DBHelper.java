package com.example.uas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String database_name = "bluejack";
    public static final String table_name = "users";
    public static final String users_row_id = "_id";
    public static final String users_row_name = "Name";
    public static final String users_row_email = "Email";
    public static final String users_row_password = "Password";
    public static final String users_row_phone = "Phone";
    public static final String users_row_isVerified = "IsVerified";

    private SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, database_name, null, 3);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + table_name + "(" + users_row_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + users_row_name + " TEXT," + users_row_email + " TEXT," + users_row_password + " TEXT," + users_row_phone + " TEXT," + users_row_isVerified + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
    }

    //Insert Data
    public void insertData(ContentValues values){
        db.insert(table_name, null, values);
    }


    public String checkUser(String email, String password){
        String[] columns = {users_row_id, users_row_isVerified};
        SQLiteDatabase db = getReadableDatabase();
        String selection = users_row_email + "=?" + " and " + users_row_password + "=?";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query(table_name, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();

        String isVerified = "";

        if (cursor.moveToFirst()) {
            int isVerifiedIndex = cursor.getColumnIndex(users_row_isVerified);
            isVerified = cursor.getString(isVerifiedIndex);
        }

        cursor.close();
        db.close();

        if (count > 0) {
            if (isVerified.equals("0")) {
                return "otp";
            } else {
                return "true";
            }
        } else {
            return "false";
        }
    }
}
