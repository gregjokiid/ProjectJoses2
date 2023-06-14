package com.example.uas.ui;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.uas.R;

public class DetailActivity extends AppCompatActivity {
    ImageView tvMedicineImage;
    TextView tvMedicineName, tvMedicineManufacturer, tvMedicinePrice, tvMedicineDescription;

    @SuppressLint("WrongConstant")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("ONIC Fans Apps");
        actionBar.setDisplayHomeAsUpEnabled(true);

        tvMedicineName = findViewById(R.id.tv_medicineName);
        tvMedicineManufacturer = findViewById(R.id.tv_medicineManufacturer);
        tvMedicinePrice = findViewById(R.id.tv_medicinePrice);
        tvMedicineImage = findViewById(R.id.tv_medicineImage);
        tvMedicineDescription = findViewById(R.id.tv_medicineDescription);

        String medicineImage = getIntent().getStringExtra("medicine_image");
        String medicineName = getIntent().getStringExtra("medicine_name");
        String medicineManufacturer = getIntent().getStringExtra("medicine_manufacturer");
        String medicinePrice = getIntent().getStringExtra("medicine_price");
        String medicineDescription = getIntent().getStringExtra("medicine_description");

        Glide.with(this).asBitmap().load(medicineImage).into(tvMedicineImage);

        tvMedicineName.setText(medicineName);
        tvMedicineManufacturer.setText(medicineManufacturer);
        tvMedicinePrice.setText(medicinePrice);
        tvMedicineDescription.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        tvMedicineDescription.setText(medicineDescription);
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