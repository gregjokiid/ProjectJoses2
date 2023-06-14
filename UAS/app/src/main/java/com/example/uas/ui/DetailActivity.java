package com.example.uas.ui;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.uas.DBHelper;
import com.example.uas.R;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {
    ImageView tvMedicineImage;
    TextView tvMedicineName, tvMedicineManufacturer, tvMedicinePrice, tvMedicineDescription;
    DBHelper dbHelper;
    String userEmail, medicineName;

    @SuppressLint("WrongConstant")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        dbHelper = new DBHelper(this);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("ONIC Fans Apps");
        actionBar.setDisplayHomeAsUpEnabled(true);

        tvMedicineName = findViewById(R.id.tv_medicineName);
        tvMedicineManufacturer = findViewById(R.id.tv_medicineManufacturer);
        tvMedicinePrice = findViewById(R.id.tv_medicinePrice);
        tvMedicineImage = findViewById(R.id.tv_medicineImage);
        tvMedicineDescription = findViewById(R.id.tv_medicineDescription);

        userEmail = getIntent().getStringExtra("user_email");
        String medicineImage = getIntent().getStringExtra("medicine_image");
        medicineName = getIntent().getStringExtra("medicine_name");
        String medicineManufacturer = getIntent().getStringExtra("medicine_manufacturer");
        String medicinePrice = getIntent().getStringExtra("medicine_price");
        String medicineDescription = getIntent().getStringExtra("medicine_description");

        Glide.with(this).asBitmap().load(medicineImage).into(tvMedicineImage);

        tvMedicineName.setText(medicineName);
        tvMedicineManufacturer.setText(medicineManufacturer);
        tvMedicinePrice.setText(medicinePrice);
        tvMedicineDescription.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        tvMedicineDescription.setText(medicineDescription);

        Button btnBeli = findViewById(R.id.btn_beli);
        btnBeli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
    }

    public void openDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_beli_obat, null);
        builder.setView(view);
        TextInputLayout layoutQuantity = view.findViewById(R.id.layout_quantity);
        EditText edtQuantity = view.findViewById(R.id.edt_quantity);
        Button btnBeliDialog = view.findViewById(R.id.btn_beliDialog);
        btnBeliDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String quantityString = edtQuantity.getText().toString();
                ContentValues values = new ContentValues();
                int userId = dbHelper.getUserIdByEmail(userEmail);
                SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", new Locale("in", "ID"));
                int quantity = quantityString.equals("") ? 0 : Integer.parseInt(quantityString);
                if(quantity < 1) {
                    layoutQuantity.setError("Isi terlebih dahulu");
                } else {
                    values.put(DBHelper.row_transaction_medicineID, medicineName);
                    values.put(DBHelper.row_transaction_userID, userId);
                    values.put(DBHelper.row_transaction_date, sdf.format(new Date()));
                    values.put(DBHelper.row_transaction_quantity, quantity);
                    dbHelper.insertTransaction(values);

                    Toast.makeText(DetailActivity.this, "Berhasil membeli obat", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
        builder.show();
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