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

//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeFragment extends Fragment {
    private TextView mTextViewResult;
    private RequestQueue mQueue;
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

//        mQueue = Volley.newRequestQueue(this);

        MedicineAdapter medicineAdapter = new MedicineAdapter();
        binding.rvMedicine.setAdapter(medicineAdapter);
        binding.rvMedicine.setLayoutManager(new LinearLayoutManager(requireContext()));

        List<Medicine> medicineList = new ArrayList<>();
//        medicineList.add(new Medicine("Ibuprofen", "Rp12000", "PT Kimia Farma", "Ibuprofen adalah obat untuk untuk meredakan nyeri dan menurunkan deman. Obat ini juga memiliki efek antiradang. Ibuprofen bisa digunakan untuk meredakan nyeri haid, sakit kepala, sakit gigi, nyeri otot, atau nyeri sendi akibat radang sendi. ", R.drawable.ibuprofen));
//        medicineList.add(new Medicine("Betadine", "Rp6500", "PT Kalbe", "Betadine adalah produk antiseptik yang bermanfaat untuk mencegah pertumbuhan dan membunuh kuman penyebab infeksi.", R.drawable.betadine));
//        medicineList.add(new Medicine("Neurobion", "Rp18500", "PT Kimia Farma", "Neurobion adalah suplemen yang bermanfaat untuk mencegah dan mengobati gangguan saraf. Neurobion merupakan suplemen multivitamin yang mengandung vitamin B1, B6, dan B12 dalam dosis tinggi. ", R.drawable.neurobion));
//        medicineList.add(new Medicine("Sangobion", "Rp21500", "PT Kimia Farma", "Sangobion adalah suplemen untuk mengatasi kurang darah (anemia).", R.drawable.sangobion));
//        medicineList.add(new Medicine("Bisolvon", "Rp6500", "PT Kalbe", "Ini adalah bisolvon", R.drawable.bisolvon));
//        medicineList.add(new Medicine("Oralit", "Rp6500", "PT Kalbe", "Ini adalah oralit", R.drawable.oralit));
//        medicineList.add(new Medicine("Amoxcillin", "Rp6500", "PT Kalbe", "Ini adalah amoxcillin", R.drawable.amoxcillin));
//        medicineList.add(new Medicine("Paracetamol", "Rp6500", "PT Kalbe", "Ini adalah paracetamol", R.drawable.paracetamol));

        @Override
        public void onResponse(JSONObject response) {
            try {
                JSONArray jsonArray = response.getJSONArray("medicines");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject medicineJson = jsonArray.getJSONObject(i);
                    String name = medicineJson.getString("name");
                    String price = medicineJson.getString("price");
                    String manufacturer = medicineJson.getString("manufacturer");
                    String description = medicineJson.getString("description");

                    Medicine medicine = new Medicine(name, price, manufacturer, description, R.drawable.placeholder); // Ubah R.drawable.placeholder dengan resource ID gambar yang sesuai
                    medicineList.add(medicine);
                }

                medicineAdapter.setData(medicineList);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        medicineAdapter.setData(medicineList);

    }

    private void jsonParse() {
        String url = "https://mocki.io/v1/ae13b04b-13df-4023-88a5-7346d5d3c7eb";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("medicines");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject medicine = jsonArray.getJSONObject(i);
                                String name = medicine.getString("name");
                                String price = medicine.getString("price");
                                String manufacturer = medicine.getString("manufacturer");
                                String description = medicine.getString("description");

//                                mTextViewResult.append(firstName + ", " + String.valueOf(age) + ", " + mail + "\n\n");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }
}