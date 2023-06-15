package com.example.uas.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.uas.DBHelper;
import com.example.uas.R;
import com.example.uas.adapter.TransactionAdapter;
import com.example.uas.model.Transaction;

import java.util.ArrayList;

public class TransactionActivity extends AppCompatActivity {
    private RecyclerView rvTransaction;
    private ArrayList<Transaction> list = new ArrayList<>();
    DBHelper dbHelper;
    String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        dbHelper = new DBHelper(this);

        userEmail = getIntent().getStringExtra("user_email");

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        rvTransaction = findViewById(R.id.rv_transaction);
        rvTransaction.setHasFixedSize(true);

        // Mengambil data transaksi dari DBHelper berdasarkan email pengguna tertentu
        list.addAll(dbHelper.getTransactionsByEmail(userEmail));

        showRecyclerList();
    }

    private void showRecyclerList() {
        rvTransaction.setLayoutManager(new LinearLayoutManager(this));
        TransactionAdapter transactionAdapter = new TransactionAdapter(list);
        rvTransaction.setAdapter(transactionAdapter);

        transactionAdapter.setOnItemClickCallBack(new TransactionAdapter.OnItemClickCallBack() {
            @Override
            public void onItemClicked(Transaction data) {
                //
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
