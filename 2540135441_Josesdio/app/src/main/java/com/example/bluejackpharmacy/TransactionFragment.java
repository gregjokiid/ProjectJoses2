package com.example.bluejackpharmacy;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bluejackpharmacy.database.AppDatabase;
import com.example.bluejackpharmacy.database.Transaction;
import com.example.bluejackpharmacy.database.TransactionDao;
import com.example.bluejackpharmacy.databinding.DialogBeliObatBinding;
import com.example.bluejackpharmacy.databinding.FragmentTransactionBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TransactionFragment extends Fragment {

    FragmentTransactionBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTransactionBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TransactionAdapter adapter = new TransactionAdapter();
        binding.rvTransaction.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvTransaction.setAdapter(adapter);

        TransactionDao transactionDao = AppDatabase.getInstance(requireContext()).transactionDao();
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("sharedpref", MODE_PRIVATE);
        int uid = sharedPreferences.getInt("uid", 0);
        adapter.setData(transactionDao.getAll(uid));

        adapter.setListener(new TransactionAdapter.OnItemClick() {
            @Override
            public void onUpdate(Transaction transaction) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
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
                                    transaction.quantity = quantity;
                                    transactionDao.update(transaction);
                                    Toast.makeText(requireContext(), "Berhasil update", Toast.LENGTH_SHORT).show();

                                    dialog.dismiss();
                                    adapter.setData(transactionDao.getAll(uid));
                                }

                            }
                        });
                    }
                });

                dialog.show();
            }

            @Override
            public void onDelete(Transaction transaction) {
                new AlertDialog.Builder(requireContext())
                        .setTitle("Apakah yakin ingin menghapus")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                transactionDao.deleteTransaction(transaction);
                                Toast.makeText(requireContext(), "Berhasil menghapus", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                adapter.setData(transactionDao.getAll(uid));
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create()
                        .show();
            }
        });
    }
}