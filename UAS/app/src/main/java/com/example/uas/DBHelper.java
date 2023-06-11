package com.example.uas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String database_name = "db_login";
    public static final String table_name = "table_login";
    public static final String row_id = "_id";
    public static final String row_name = "Name";
    public static final String row_email = "Email";
    public static final String row_password = "Password";
    public static final String row_phone = "Phone";
    public static final String row_isVerified = "IsVerified";

    private SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, database_name, null, 3);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + table_name + "(" + row_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + row_name + " TEXT," + row_email + " TEXT," + row_password + " TEXT," + row_phone + " TEXT," + row_isVerified + " TEXT)";
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
        String[] columns = {row_id, row_isVerified};
        SQLiteDatabase db = getReadableDatabase();
        String selection = row_email + "=?" + " and " + row_password + "=?";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query(table_name, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();

        String isVerified = "";

        if (cursor.moveToFirst()) {
            int isVerifiedIndex = cursor.getColumnIndex(row_isVerified);
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

    public String verified(){
        //
    }
}
