package com.example.finpro.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.finpro.DBHelper;
import com.example.finpro.R;
import com.example.finpro.adapter.TransactionAdapter;
import com.example.finpro.model.Transaction;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu1) {
            startActivity(new Intent(this, LoginActivity.class));
            return true;
        } else if (item.getItemId() == R.id.menu2) {
            startActivity(new Intent(this, MenuActivity.class));
            return true;
        } else if (item.getItemId() == R.id.menu3) {
            Intent directintent = new Intent(this, TransactionActivity.class);
            directintent.putExtra("user_email", userEmail);
            startActivity(directintent);
            return true;
        } else if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        } else {
            return true;
        }
    }
}
