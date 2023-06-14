package com.example.uas.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Transaction implements Parcelable {
    private int id, medicineID, userID, quantity;
    private Date transactionDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMedicineID() {
        return medicineID;
    }

    public void setMedicineID(int medicineID) {
        this.medicineID = medicineID;
    }
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.medicineID);
        dest.writeInt(this.userID);
        dest.writeInt(this.quantity);
    }

    public Transaction() {
    }

    public Transaction(int id, int medicineID, int userID, Date transactionDate, int quantity) {
        this.id = id;
        this.medicineID = medicineID;
        this.userID = userID;
        this.transactionDate = transactionDate;
        this.quantity = quantity;
    }

    private Transaction(Parcel in) {
        this.id = in.readInt();
        this.medicineID = in.readInt();
        this.userID = in.readInt();
        this.quantity = in.readInt();
    }

    public static final Parcelable.Creator<Transaction> CREATOR = new Parcelable.Creator<Transaction>() {
        @Override
        public Transaction createFromParcel(Parcel source) {
            return new Transaction(source);
        }

        @Override
        public Transaction[] newArray(int size) {
            return new Transaction[size];
        }
    };
}
