package com.example.uas.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.uas.DBHelper;
import com.example.uas.R;
import com.example.uas.adapter.TransactionAdapter;
import com.example.uas.model.Transaction;
import com.example.uas.model.TransactionData;

import java.util.ArrayList;

public class TransactionActivity extends AppCompatActivity {
    private RecyclerView rvTransaction;
    private ArrayList<Transaction> list = new ArrayList<>();
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        dbHelper = new DBHelper(this);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        setTitle("ONIC Fans Apps");

        rvTransaction = findViewById(R.id.rv_transaction);
        rvTransaction.setHasFixedSize(true);

        list.addAll(TransactionData.getListData());
        showRecyclerList();
    }

    private void showRecyclerList() {
        rvTransaction.setLayoutManager(new LinearLayoutManager(this));
        TransactionAdapter transactionAdapter = new TransactionAdapter(list);
        rvTransaction.setAdapter(transactionAdapter);

        transactionAdapter.setOnItemClickCallBack(new TransactionAdapter.OnItemClickCallBack() {
            @Override
            public void onItemClicked(Transaction data) {
//                ShowNews(data);
            }
        });
    }

//    private void ShowNews(Transaction news) {
//        Intent directintent = new Intent(TransactionActivity.this, DetailActivity.class);
//        Toast.makeText(this, news.getNewsTitle(), Toast.LENGTH_SHORT).show();
//        directintent.putExtra("news_image", news.getNewsImage());
//        directintent.putExtra("news_title", news.getNewsTitle());
//        directintent.putExtra("news_author", news.getNewsAuthor());
//        directintent.putExtra("news_detail", news.getNewsDetail());
//        startActivity(directintent);
//    }
}