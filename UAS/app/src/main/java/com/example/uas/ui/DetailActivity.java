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
    TextView tvMedicineName, tvMedicineDescription, tvMedicineAuthor;

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
        tvMedicineImage = findViewById(R.id.tv_medicineImage);
        tvMedicineAuthor = findViewById(R.id.tv_medicineAuthor);
        tvMedicineDescription = findViewById(R.id.tv_medicineDescription);

        int medicineImage = getIntent().getIntExtra("medicine_image", 0);
        String medicineName = getIntent().getStringExtra("medicine_name");
        String medicineDescription = getIntent().getStringExtra("medicine_description");
        String medicineAuthor = getIntent().getStringExtra("medicine_author");

        Glide.with(this).asBitmap().load(medicineImage).into(tvMedicineImage);

        tvMedicineName.setText(medicineName);
        tvMedicineDescription.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        tvMedicineDescription.setText(medicineDescription);
        tvMedicineAuthor.setText(medicineAuthor);
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