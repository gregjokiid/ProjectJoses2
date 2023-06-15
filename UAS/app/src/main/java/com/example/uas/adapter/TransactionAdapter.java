package com.example.uas.adapter;

import android.annotation.SuppressLint;
import android.os.Build;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.uas.R;
import com.example.uas.model.Transaction;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ListViewHolder> {
    private TransactionAdapter.OnItemClickCallBack onItemClickCallBack;

    public void setOnItemClickCallBack(TransactionAdapter.OnItemClickCallBack onItemClickCallBack){
        this.onItemClickCallBack = onItemClickCallBack;
    }

    private final ArrayList<Transaction> newsList;

    public TransactionAdapter(ArrayList<Transaction> list){
        this.newsList = list;
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
        Transaction news = newsList.get(position);
        holder.tvMedicineId.setText(news.getMedicineId());
        holder.tvTransactionDate.setText(news.getTransactionDate());
        holder.tvQuantity.setText(String.valueOf(news.getQuantity()));

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onItemClickCallBack.onItemClicked(newsList.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public static class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tvMedicineId, tvTransactionDate, tvQuantity;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMedicineId = itemView.findViewById(R.id.tv_item_medicine_id);
            tvTransactionDate = itemView.findViewById(R.id.tv_item_transaction_date);
            tvQuantity = itemView.findViewById(R.id.tv_item_quantity);
        }
    }

    public interface OnItemClickCallBack {
        void onItemClicked(Transaction data);
    }
}
