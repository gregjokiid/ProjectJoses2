package com.example.uas.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Transaction implements Parcelable {
    private int id, userId, quantity;
    private String medicineId, transactionDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(String medicineId) {
        this.medicineId = medicineId;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.userId);
        dest.writeInt(this.quantity);
        dest.writeString(this.medicineId);
        dest.writeString(this.transactionDate);
    }

    public Transaction() {
    }

    public Transaction(int id, int userId, int quantity, String medicineId, String transactionDate) {
        this.id = id;
        this.userId = userId;
        this.quantity = quantity;
        this.medicineId = medicineId;
        this.transactionDate = transactionDate;
    }

    private Transaction(Parcel in) {
        this.id = in.readInt();
        this.userId = in.readInt();
        this.quantity = in.readInt();
        this.medicineId = in.readString();
        this.transactionDate = in.readString();
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
