package com.example.uas.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

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
import com.example.uas.databinding.ActivityMainBinding;
import com.example.uas.model.Medicine;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MedicineAdapter medicineAdapter = new MedicineAdapter();
        binding.rvMedicine.setAdapter(medicineAdapter);

        List<Medicine> medicineList = new ArrayList<>();
        medicineList.add(new Medicine("Ibuprofen", "Rp12000", "PT Kimia Farma", "Ibuprofen adalah obat untuk untuk meredakan nyeri dan menurunkan deman. Obat ini juga memiliki efek antiradang. Ibuprofen bisa digunakan untuk meredakan nyeri haid, sakit kepala, sakit gigi, nyeri otot, atau nyeri sendi akibat radang sendi. ", R.drawable.ic_launcher_background));
        medicineList.add(new Medicine("Betadine", "Rp6500", "PT Kalbe", "Betadine adalah produk antiseptik yang bermanfaat untuk mencegah pertumbuhan dan membunuh kuman penyebab infeksi.", R.drawable.ic_launcher_background));
        medicineList.add(new Medicine("Neurobion", "Rp18500", "PT Kimia Farma", "Neurobion adalah suplemen yang bermanfaat untuk mencegah dan mengobati gangguan saraf. Neurobion merupakan suplemen multivitamin yang mengandung vitamin B1, B6, dan B12 dalam dosis tinggi. ", R.drawable.ic_launcher_background));
        medicineList.add(new Medicine("Sangobion", "Rp21500", "PT Kimia Farma", "Sangobion adalah suplemen untuk mengatasi kurang darah (anemia).", R.drawable.ic_launcher_background));
        medicineList.add(new Medicine("Bisolvon", "Rp6500", "PT Kalbe", "Ini adalah bisolvon", R.drawable.ic_launcher_background));
        medicineList.add(new Medicine("Oralit", "Rp6500", "PT Kalbe", "Ini adalah oralit", R.drawable.ic_launcher_background));
        medicineList.add(new Medicine("Amoxcillin", "Rp6500", "PT Kalbe", "Ini adalah amoxcillin", R.drawable.ic_launcher_background));
        medicineList.add(new Medicine("Paracetamol", "Rp6500", "PT Kalbe", "Ini adalah paracetamol", R.drawable.ic_launcher_background));

        medicineAdapter.setData(medicineList);
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
