package com.example.bluejackpharmacy;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bluejackpharmacy.database.Medicine;
import com.example.bluejackpharmacy.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MedicineAdapter medicineAdapter = new MedicineAdapter();
        binding.rvMedicine.setAdapter(medicineAdapter);
        binding.rvMedicine.setLayoutManager(new LinearLayoutManager(requireContext()));

        List<Medicine> medicineList = new ArrayList<>();
        medicineList.add(new Medicine("Ibuprofen", "Rp12000", "PT Kimia Farma", "Ibuprofen adalah obat untuk untuk meredakan nyeri dan menurunkan deman. Obat ini juga memiliki efek antiradang. Ibuprofen bisa digunakan untuk meredakan nyeri haid, sakit kepala, sakit gigi, nyeri otot, atau nyeri sendi akibat radang sendi. ", R.drawable.ibuprofen));
        medicineList.add(new Medicine("Betadine", "Rp6500", "PT Kalbe", "Betadine adalah produk antiseptik yang bermanfaat untuk mencegah pertumbuhan dan membunuh kuman penyebab infeksi.", R.drawable.betadine));
        medicineList.add(new Medicine("Neurobion", "Rp18500", "PT Kimia Farma", "Neurobion adalah suplemen yang bermanfaat untuk mencegah dan mengobati gangguan saraf. Neurobion merupakan suplemen multivitamin yang mengandung vitamin B1, B6, dan B12 dalam dosis tinggi. ", R.drawable.neurobion));
        medicineList.add(new Medicine("Sangobion", "Rp21500", "PT Kimia Farma", "Sangobion adalah suplemen untuk mengatasi kurang darah (anemia).", R.drawable.sangobion));
        medicineList.add(new Medicine("Bisolvon", "Rp6500", "PT Kalbe", "Ini adalah bisolvon", R.drawable.bisolvon));
        medicineList.add(new Medicine("Oralit", "Rp6500", "PT Kalbe", "Ini adalah oralit", R.drawable.oralit));
        medicineList.add(new Medicine("Amoxcillin", "Rp6500", "PT Kalbe", "Ini adalah amoxcillin", R.drawable.amoxcillin));
        medicineList.add(new Medicine("Paracetamol", "Rp6500", "PT Kalbe", "Ini adalah paracetamol", R.drawable.paracetamol));

        medicineAdapter.setData(medicineList);

    }
}