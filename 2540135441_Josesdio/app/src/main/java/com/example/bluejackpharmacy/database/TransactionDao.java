package com.example.bluejackpharmacy.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TransactionDao {
    @Query("SELECT * FROM `transaction` WHERE uid = :uid")
    List<Transaction> getAll(Integer uid);

    @Insert
    void addTransaction(Transaction transaction);

    @Delete
    void deleteTransaction(Transaction transaction);

    @Update
    void update(Transaction transaction);

}
