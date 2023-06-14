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
import com.example.uas.model.Medicine;

import java.util.ArrayList;

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.ListViewHolder> {
    private OnItemClickCallBack onItemClickCallBack;

    public void setOnItemClickCallBack(OnItemClickCallBack onItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallBack;
    }

    private final ArrayList<Medicine> medicineList;

    public MedicineAdapter(ArrayList<Medicine> list, OnItemClickCallBack onItemClickCallBack){
        this.medicineList = list;
        this.onItemClickCallBack = onItemClickCallBack;
    }

    @NonNull
    @Override
    public MedicineAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_medicine, viewGroup, false);
        return new MedicineAdapter.ListViewHolder(view);
    }

    @SuppressLint("WrongConstant")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        Medicine medicine = medicineList.get(position);
        Glide.with(holder.itemView.getContext())
                .load((medicine.getImage()))
                .apply(new RequestOptions().override(55,55))
                .into(holder.imgPhoto);
        holder.tvName.setText(medicine.getName());
        holder.tvManufacturer.setText(medicine.getManufacturer());
        holder.tvPrice.setText(medicine.getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallBack.onItemClicked(medicineList.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return medicineList.size();
    }

    public static class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvManufacturer, tvPrice;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvManufacturer = itemView.findViewById(R.id.tv_item_manufacturer);
            tvPrice = itemView.findViewById(R.id.tv_item_price);
        }
    }

    public interface OnItemClickCallBack {
        void onItemClicked(Medicine data);
    }
}