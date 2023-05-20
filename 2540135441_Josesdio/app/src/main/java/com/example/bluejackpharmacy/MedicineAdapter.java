package com.example.bluejackpharmacy;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bluejackpharmacy.database.Medicine;
import com.example.bluejackpharmacy.databinding.ItemMedicineBinding;

import java.util.ArrayList;
import java.util.List;

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.ViewHolder> {

    List<Medicine> listMedicine = new ArrayList<>();

    void setData(List<Medicine> listMedicine) {
        this.listMedicine.clear();
        this.listMedicine.addAll(listMedicine);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MedicineAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_medicine, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicineAdapter.ViewHolder holder, int position) {
        Medicine medicine = listMedicine.get(position);
        holder.bindData(medicine);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = holder.itemView.getContext();
                Intent intent = new Intent(context, DetailMedicineActivity.class);
                intent.putExtra("medicine", medicine);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMedicine.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemMedicineBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemMedicineBinding.bind(itemView);
        }

        public void bindData(Medicine data) {
            binding.ivMedicine.setImageResource(data.getImage());
            binding.tvName.setText(data.getName());
            binding.tvManufacturer.setText(data.getManufacturer());
            binding.tvPrice.setText(data.getPrice());
        }
    }
}
