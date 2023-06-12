package com.example.uas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String database_name = "bluejack";
    public static final String table_user = "users";
    public static final String row_user_id = "_id";
    public static final String row_user_name = "Name";
    public static final String row_user_email = "Email";
    public static final String row_user_password = "Password";
    public static final String row_user_phone = "Phone";
    public static final String row_user_isVerified = "IsVerified";

    private SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, database_name, null, 3);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + table_user + "(" + row_user_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + row_user_name + " TEXT," + row_user_email + " TEXT," + row_user_password + " TEXT," + row_user_phone + " TEXT," + row_user_isVerified + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_user);
    }

    public void insertData(ContentValues values){
        db.insert(table_user, null, values);
    }


    public String checkUser(String email, String password){
        String[] columns = {row_user_id, row_user_isVerified};
        SQLiteDatabase db = getReadableDatabase();
        String selection = row_user_email + "=?" + " and " + row_user_password + "=?";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query(table_user, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();

        String isVerified = "";

        if (cursor.moveToFirst()) {
            int isVerifiedIndex = cursor.getColumnIndex(row_user_isVerified);
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

    public void updateUserIsVerified(String email) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(row_user_isVerified, "1");
        String whereClause = row_user_email + "=?";
        String[] whereArgs = {email};
        db.update(table_user, values, whereClause, whereArgs);
        db.close();
    }
}