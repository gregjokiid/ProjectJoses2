package com.example.uas.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.uas.R;
import com.example.uas.ui.fragment.AboutUsFragment;
import com.example.uas.ui.fragment.HomeFragment;
import com.example.uas.ui.fragment.TransactionFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(navListner);

        //f statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
        }

    }

    private NavigationBarView.OnItemSelectedListener navListner =
            new NavigationBarView.OnItemSelectedListener(){
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    if (item.getItemId() == R.id.home){
                      selectedFragment = new HomeFragment();
                    }
                    else if (item.getItemId() == R.id.transaction){
                        selectedFragment = new TransactionFragment();
                    }
                    else if (item.getItemId() == R.id.about_us){
                        selectedFragment = new AboutUsFragment();
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };

}