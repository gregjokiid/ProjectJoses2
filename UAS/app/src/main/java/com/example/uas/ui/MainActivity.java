package com.example.uas.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.uas.R;
import com.example.uas.adapter.MedicineAdapter;
import com.example.uas.model.Medicine;
import com.example.uas.model.MedicineData;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvNews;
    private ArrayList<Medicine> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvNews = findViewById(R.id.rv_news);
        rvNews.setHasFixedSize(true);

        list.addAll(MedicineData.getListData());
        showRecyclerList();
    }

    private void showRecyclerList() {
        rvNews.setLayoutManager(new LinearLayoutManager(this));
        MedicineAdapter newsAdapter = new MedicineAdapter(list);
        rvNews.setAdapter(newsAdapter);

        newsAdapter.setOnItemClickCallBack(new MedicineAdapter.OnItemClickCallBack() {
            @Override
            public void onItemClicked(Medicine data) {
                ShowNews(data);
            }
        });
    }

    private void ShowNews(Medicine news) {
        Intent directintent = new Intent(MainActivity.this, DetailActivity.class);
        Toast.makeText(this, news.getNewsTitle(), Toast.LENGTH_SHORT).show();
        directintent.putExtra("news_image", news.getNewsImage());
        directintent.putExtra("news_title", news.getNewsTitle());
        directintent.putExtra("news_author", news.getNewsAuthor());
        directintent.putExtra("news_detail", news.getNewsDetail());
        startActivity(directintent);
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
        } else {
            return true;
        }
    }
}
