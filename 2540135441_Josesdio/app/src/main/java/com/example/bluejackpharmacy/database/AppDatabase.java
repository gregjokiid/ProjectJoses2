package com.example.bluejackpharmacy.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Transaction.class, User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TransactionDao transactionDao();
    public abstract UserDao userDao();

    private static AppDatabase Instance;

    public static AppDatabase getInstance(Context context) {
        if(Instance == null) {
            Instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "BLUEJACK_DB")
                    .allowMainThreadQueries()
                    .build();
        }

        return Instance;
    }
}
