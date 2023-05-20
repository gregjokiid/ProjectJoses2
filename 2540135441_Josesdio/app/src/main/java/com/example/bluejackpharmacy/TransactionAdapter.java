package com.example.bluejackpharmacy;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bluejackpharmacy.database.Medicine;
import com.example.bluejackpharmacy.database.Transaction;
import com.example.bluejackpharmacy.databinding.ItemMedicineBinding;
import com.example.bluejackpharmacy.databinding.ItemTransactionBinding;

import java.util.ArrayList;
import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    List<Transaction> listTransaction = new ArrayList<>();
    OnItemClick onItemClick;

    void setData(List<Transaction> listTransaction) {
        this.listTransaction.clear();
        this.listTransaction.addAll(listTransaction);
        notifyDataSetChanged();
    }

    void setListener(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }
    @NonNull
    @Override
    public TransactionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaction, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.ViewHolder holder, int position) {
        Transaction transaction = listTransaction.get(position);
        holder.bindData(transaction);
    }

    @Override
    public int getItemCount() {
        return listTransaction.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemTransactionBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemTransactionBinding.bind(itemView);
        }

        public void bindData(Transaction data) {
            binding.tvPrice.setText(data.price);
            binding.tvName.setText(data.medicineName);
            binding.tvQuantity.setText("Quantity: " + data.quantity);
            binding.tvDate.setText(data.date);

            binding.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick.onDelete(data);
                }
            });

            binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick.onUpdate(data);
                }
            });
        }
    }

    interface OnItemClick{
        void onUpdate(Transaction transaction);
        void onDelete(Transaction transaction);
    }
}
