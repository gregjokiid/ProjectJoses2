package com.example.finpro.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finpro.DBHelper;
import com.example.finpro.R;
import com.example.finpro.adapter.TransactionAdapter;
import com.example.finpro.model.Transaction;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class TransactionActivity extends AppCompatActivity {
    private RecyclerView rvTransaction;
    private ArrayList<Transaction> list = new ArrayList<>();
    DBHelper dbHelper;
    String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        dbHelper = new DBHelper(this);

        userEmail = getIntent().getStringExtra("user_email");

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        rvTransaction = findViewById(R.id.rv_transaction);
        rvTransaction.setHasFixedSize(true);

        // Mengambil data transaksi dari DBHelper berdasarkan email pengguna tertentu
        list.addAll(dbHelper.getTransactionsByEmail(userEmail));

        showRecyclerList();
    }

    private void showRecyclerList() {
        rvTransaction.setLayoutManager(new LinearLayoutManager(this));
        TransactionAdapter transactionAdapter = new TransactionAdapter(list);
        rvTransaction.setAdapter(transactionAdapter);

        transactionAdapter.setOnItemClickCallBack(new TransactionAdapter.OnItemClickCallBack() {
            @Override
            public void onUpdate(Transaction data) {
                openDialog(data);
            }

            @Override
            public void onDelete(Transaction data) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TransactionActivity.this)
                        .setTitle("Apakah yakin ingin menghapus")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dbHelper.deleteTransactionById(data.getId());
                                Toast.makeText(TransactionActivity.this, "Berhasil menghapus pembelian obat", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                builder.create();
                builder.show();
            }
        });
    }

    public void openDialog(Transaction data){
        AlertDialog.Builder builder = new AlertDialog.Builder(TransactionActivity.this);
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
                int quantity = quantityString.equals("") ? 0 : Integer.parseInt(quantityString);
                if(quantity < 1) {
                    layoutQuantity.setError("Isi terlebih dahulu");
                } else {
                    dbHelper.updateTransactionQuantityById(data.getId(), quantity);

                    Toast.makeText(TransactionActivity.this, "Berhasil mengupdate pembelian obat", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
        builder.show();
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
        } else if (item.getItemId() == R.id.menu3) {
            Intent directintent = new Intent(this, TransactionActivity.class);
            directintent.putExtra("user_email", userEmail);
            startActivity(directintent);
            return true;
        } else if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        } else {
            return true;
        }
    }
}
