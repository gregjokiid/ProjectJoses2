package com.example.finpro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.finpro.model.Transaction;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String database_name = "bluejack";
    public static final String table_user = "users";
    public static final String row_user_id = "_id";
    public static final String row_user_name = "Name";
    public static final String row_user_email = "Email";
    public static final String row_user_password = "Password";
    public static final String row_user_phone = "Phone";
    public static final String row_user_isVerified = "IsVerified";

    public static final String table_medicines = "medicines";
    public static final String row_medicine_id = "_id";
    public static final String row_medicine_name = "Name";
    public static final String row_medicine_manufacturer = "Manufacturer";
    public static final String row_medicine_price = "Price";
    public static final String row_medicine_image = "Image";
    public static final String row_medicine_description = "Description";

    public static final String table_transactions = "transactions";
    public static final String row_transaction_id = "_id";
    public static final String row_transaction_medicineID = "MedicineID";
    public static final String row_transaction_userID = "UserID";
    public static final String row_transaction_date = "TransactionDate";
    public static final String row_transaction_quantity = "Quantity";

    private SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, database_name, null, 1);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryUsers = "CREATE TABLE " + table_user + "(" + row_user_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + row_user_name + " TEXT," + row_user_email + " TEXT," + row_user_password + " TEXT,"
                + row_user_phone + " TEXT," + row_user_isVerified + " TEXT)";
        db.execSQL(queryUsers);

        String queryMedicines = "CREATE TABLE " + table_medicines + "(" + row_medicine_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + row_medicine_name + " TEXT," + row_medicine_manufacturer + " TEXT," + row_medicine_price + " TEXT,"
                + row_medicine_image + " TEXT," + row_medicine_description + " TEXT)";
        db.execSQL(queryMedicines);

        String queryTransactions = "CREATE TABLE " + table_transactions + "(" + row_transaction_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + row_transaction_medicineID + " TEXT," + row_transaction_userID + " INTEGER,"
                + row_transaction_date + " TEXT," + row_transaction_quantity + " INTEGER,"
//                + "FOREIGN KEY(" + row_transaction_medicineID + ") REFERENCES " + table_medicines + "(" + row_medicine_id + "),"
                + "FOREIGN KEY(" + row_transaction_userID + ") REFERENCES " + table_user + "(" + row_user_id + "))";
        db.execSQL(queryTransactions);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_user);
        db.execSQL("DROP TABLE IF EXISTS " + table_medicines);
        db.execSQL("DROP TABLE IF EXISTS " + table_transactions);
        onCreate(db);
    }

    public void insertUser(ContentValues values){
        db.insert(table_user, null, values);
    }

    public void insertMedicine(ContentValues values) {
        db.insert(table_medicines, null, values);
    }

    public void insertTransaction(ContentValues values) {
        db.insert(table_transactions, null, values);
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

        if (count > 0) {
            if (isVerified.equals("0")) {
                return "true";
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
    }

    public int getUserIdByEmail(String email) {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {row_user_id};
        String selection = row_user_email + "=?";
        String[] selectionArgs = {email};
        Cursor cursor = db.query(table_user, columns, selection, selectionArgs, null, null, null);

        int userId = -1; // Default value jika tidak ditemukan

        if (cursor.moveToFirst()) {
            int userIdIndex = cursor.getColumnIndex(row_user_id);
            userId = cursor.getInt(userIdIndex);
        }

        cursor.close();

        return userId;
    }

    public ArrayList<Transaction> getTransactionsByEmail(String email) {
        ArrayList<Transaction> transactions = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {row_transaction_id, row_transaction_medicineID, row_transaction_date, row_transaction_quantity};
        String selection = row_transaction_userID + " = (SELECT " + row_user_id + " FROM " + table_user + " WHERE " + row_user_email + "=?)";
        String[] selectionArgs = {email};
        Cursor cursor = db.query(table_transactions, columns, selection, selectionArgs, null, null, null);

        while (cursor.moveToNext()) {
            Transaction transaction = new Transaction();
            int idIndex = cursor.getColumnIndex(row_transaction_id);
            int medicineIdIndex = cursor.getColumnIndex(row_transaction_medicineID);
            int dateIndex = cursor.getColumnIndex(row_transaction_date);
            int quantityIndex = cursor.getColumnIndex(row_transaction_quantity);

            transaction.setId(cursor.getInt(idIndex));
            transaction.setMedicineId(cursor.getString(medicineIdIndex));
            transaction.setTransactionDate(cursor.getString(dateIndex));
            transaction.setQuantity(cursor.getInt(quantityIndex));

            transactions.add(transaction);
        }

        cursor.close();

        return transactions;
    }

    public void deleteTransactionById(int transactionId) {
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = row_transaction_id + "=?";
        String[] whereArgs = {String.valueOf(transactionId)};
        db.delete(table_transactions, whereClause, whereArgs);
    }
}