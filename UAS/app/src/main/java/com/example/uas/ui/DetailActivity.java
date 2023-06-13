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
    TextView tvMedicineTitle, tvMedicineDetail, tvMedicineAuthor;

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

        tvMedicineTitle = findViewById(R.id.tv_medicineTitle);
        tvMedicineImage = findViewById(R.id.tv_medicineImage);
        tvMedicineAuthor = findViewById(R.id.tv_medicineAuthor);
        tvMedicineDetail = findViewById(R.id.tv_medicineDetail);

        int medicineImage = getIntent().getIntExtra("medicine_image", 0);
        String medicineTitle = getIntent().getStringExtra("medicine_title");
        String medicineDetail = getIntent().getStringExtra("medicine_detail");
        String medicineAuthor = getIntent().getStringExtra("medicine_author");

        Glide.with(this).asBitmap().load(medicineImage).into(tvMedicineImage);

        tvMedicineTitle.setText(medicineTitle);
        tvMedicineDetail.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        tvMedicineDetail.setText(medicineDetail);
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