package com.example.bluejackpharmacy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.bluejackpharmacy.database.AppDatabase;
import com.example.bluejackpharmacy.database.Medicine;
import com.example.bluejackpharmacy.database.Transaction;
import com.example.bluejackpharmacy.database.TransactionDao;
import com.example.bluejackpharmacy.databinding.ActivityDetailMedicineBinding;
import com.example.bluejackpharmacy.databinding.DialogBeliObatBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DetailMedicineActivity extends AppCompatActivity {

    ActivityDetailMedicineBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailMedicineBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TransactionDao transactionDao = AppDatabase.getInstance(this).transactionDao();

        Medicine data = (Medicine) getIntent().getSerializableExtra("medicine");
        binding.ivMedicine.setImageResource(data.getImage());
        binding.tvName.setText(data.getName());
        binding.tvManufacturer.setText(data.getManufacturer());
        binding.tvPrice.setText(data.getPrice());
        binding.tvDescription.setText(data.getDescription());

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.btnBeli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("click", "clicked");
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailMedicineActivity.this);
                DialogBeliObatBinding dialogBinding = DialogBeliObatBinding.inflate(getLayoutInflater());
                builder.setView(dialogBinding.getRoot());

                AlertDialog dialog = builder.create();

                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        dialogBinding.edtQuantity.addTextChangedListener(new ClearErrorWatcher(dialogBinding.layoutQuantity));
                        dialogBinding.btnBeli.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String quantityString = dialogBinding.edtQuantity.getText().toString();
                                int quantity = quantityString.equals("") ? 0 : Integer.parseInt(quantityString);
                                if(quantity < 1) {
                                    dialogBinding.layoutQuantity.setError("Isi terlebih dahulu");
                                } else {
                                    Transaction transaction = new Transaction();
                                    SharedPreferences sharedPreferences = getSharedPreferences("sharedpref", MODE_PRIVATE);
                                    int uid = sharedPreferences.getInt("uid", 0);

                                    SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", new Locale("in", "ID"));
                                    transaction.date = sdf.format(new Date());
                                    transaction.medicineName = data.getName();
                                    transaction.price = data.getPrice();
                                    transaction.quantity = quantity;
                                    transaction.uid = uid;

                                    transactionDao.addTransaction(transaction);
                                    Toast.makeText(DetailMedicineActivity.this, "Berhasil membeli obat", Toast.LENGTH_SHORT).show();

                                    dialog.dismiss();
                                }

                            }
                        });
                    }
                });
                dialog.show();
            }
        });
    }
}