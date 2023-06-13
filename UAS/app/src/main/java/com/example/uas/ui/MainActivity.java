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
    private RecyclerView rvMedicine;
    private ArrayList<Medicine> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMedicine = findViewById(R.id.rv_medicine);
        rvMedicine.setHasFixedSize(true);

        list.addAll(MedicineData.getListData());
        showRecyclerList();
    }

    private void showRecyclerList() {
        rvMedicine.setLayoutManager(new LinearLayoutManager(this));
        MedicineAdapter medicineAdapter = new MedicineAdapter(list);
        rvMedicine.setAdapter(medicineAdapter);

        medicineAdapter.setOnItemClickCallBack(new MedicineAdapter.OnItemClickCallBack() {
            @Override
            public void onItemClicked(Medicine data) {
                ShowMedicine(data);
            }
        });
    }

    private void ShowMedicine(Medicine medicine) {
        Intent directintent = new Intent(MainActivity.this, DetailActivity.class);
        Toast.makeText(this, medicine.getName(), Toast.LENGTH_SHORT).show();
        directintent.putExtra("medicine_image", medicine.getImage());
        directintent.putExtra("medicine_name", medicine.getName());
        directintent.putExtra("medicine_manufacturer", medicine.getManufacturer());
        directintent.putExtra("medicine_price", medicine.getPrice());
        directintent.putExtra("medicine_description", medicine.getDescription());
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
