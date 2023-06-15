package com.example.finpro.adapter;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finpro.R;
import com.example.finpro.model.Transaction;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ListViewHolder> {
    private TransactionAdapter.OnItemClickCallBack onItemClickCallBack;

    public void setOnItemClickCallBack(TransactionAdapter.OnItemClickCallBack onItemClickCallBack){
        this.onItemClickCallBack = onItemClickCallBack;
    }

    private final ArrayList<Transaction> transactionList;

    public TransactionAdapter(ArrayList<Transaction> list){
        this.transactionList = list;
    }

    @NonNull
    @Override
    public TransactionAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_transaction, viewGroup, false);
        return new TransactionAdapter.ListViewHolder(view);
    }

    @SuppressLint("WrongConstant")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull final TransactionAdapter.ListViewHolder holder, int position) {
        Transaction transaction = transactionList.get(position);
        holder.tvMedicineId.setText(transaction.getMedicineId());
        holder.tvTransactionDate.setText(transaction.getTransactionDate());
        holder.tvQuantity.setText(String.valueOf(transaction.getQuantity()));

        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickCallBack.onUpdate(transaction);
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickCallBack.onDelete(transaction);
            }
        });
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    public static class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tvMedicineId, tvTransactionDate, tvQuantity;
        Button btnUpdate, btnDelete;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMedicineId = itemView.findViewById(R.id.tv_item_medicine_id);
            tvTransactionDate = itemView.findViewById(R.id.tv_item_transaction_date);
            tvQuantity = itemView.findViewById(R.id.tv_item_quantity);
            btnUpdate = itemView.findViewById(R.id.btn_update);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }
    }

    public interface OnItemClickCallBack {
        void onUpdate(Transaction data);
        void onDelete(Transaction data);
    }
}
