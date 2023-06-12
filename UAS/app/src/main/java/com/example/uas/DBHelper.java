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

    // Tambahkan atribut untuk tabel medicines
    public static String table_medicines = "medicines";
    public static String row_medicine_id = "_id";
    public static String row_medicine_name = "Name";
    public static String row_medicine_manufacturer = "Manufacturer";
    public static String row_medicine_price = "Price";
    public static String row_medicine_image = "Image";
    public static String row_medicine_description = "Description";

    private SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, database_name, null, 3);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query_user = "CREATE TABLE " + table_user + "(" + row_user_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + row_user_name + " TEXT," + row_user_email + " TEXT," + row_user_password + " TEXT," + row_user_phone + " TEXT," + row_user_isVerified + " TEXT)";
        db.execSQL(query_user);

        // Tambahkan query untuk membuat tabel medicines
        String query_medicines = "CREATE TABLE " + table_medicines + "(" + row_medicine_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + row_medicine_name + " TEXT," + row_medicine_manufacturer + " TEXT," + row_medicine_price + " TEXT," + row_medicine_image + " TEXT," + row_medicine_description + " TEXT)";
        db.execSQL(query_medicines);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_user);
        db.execSQL("DROP TABLE IF EXISTS " + table_medicines);
        onCreate(db);
    }

    //Insert Data
    public void insertData(ContentValues values){
        db.insert(table_user, null, values);
    }

    //Insert Medicine Data
    public void insertMedicineData(ContentValues values){
        db.insert(table_medicines, null, values);
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

    // Getter dan Setter untuk atribut medicines
    public String getMedicineId() {
        return row_medicine_id;
    }

    public String getMedicineName() {
        return row_medicine_name;
    }

    public String getMedicineManufacturer() {
        return row_medicine_manufacturer;
    }

    public String getMedicinePrice() {
        return row_medicine_price;
    }

    public String getMedicineImage() {
        return row_medicine_image;
    }

    public String getMedicineDescription() {
        return row_medicine_description;
    }

    public void setMedicineId(String id) {
        row_medicine_id = id;
    }

    public void setMedicineName(String name) {
        row_medicine_name = name;
    }

    public void setMedicineManufacturer(String manufacturer) {
        row_medicine_manufacturer = manufacturer;
    }

    public void setMedicinePrice(String price) {
        row_medicine_price = price;
    }

    public void setMedicineImage(String image) {
        row_medicine_image = image;
    }

    public void setMedicineDescription(String description) {
        row_medicine_description = description;
    }
}
