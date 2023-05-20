package com.example.bluejackpharmacy.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Transaction {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "date")
    public String date;

    @ColumnInfo(name = "medicine_name")
    public String medicineName;

    @ColumnInfo(name = "price")
    public String price;

    @ColumnInfo(name = "quantity")
    public int quantity;

    @ColumnInfo(name = "uid")
    public int uid;
}
