package com.example.finpro.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.finpro.R;
import com.example.finpro.adapter.MedicineAdapter;
import com.example.finpro.model.Medicine;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MedicineAdapter.OnItemClickCallBack {
    private String url = "https://mocki.io/v1/ae13b04b-13df-4023-88a5-7346d5d3c7eb";
    private RecyclerView mList;
    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<Medicine> medicineList;
    private RecyclerView.Adapter adapter;
    String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userEmail = getIntent().getStringExtra("user_email");

        mList = findViewById(R.id.rv_medicine);

        medicineList = new ArrayList<>();
        adapter = new MedicineAdapter((ArrayList<Medicine>) medicineList, this);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(mList.getContext(), linearLayoutManager.getOrientation());

        mList.setHasFixedSize(true);
        mList.setLayoutManager(linearLayoutManager);
        mList.addItemDecoration(dividerItemDecoration);
        mList.setAdapter(adapter);

        getData();
    }

    private void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("medicines");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject medicineObj = jsonArray.getJSONObject(i);

                        Medicine medicine = new Medicine();
                        medicine.setName(medicineObj.getString("name"));
                        medicine.setManufacturer(medicineObj.getString("manufacturer"));
                        medicine.setPrice(medicineObj.getString("price"));
                        medicine.setImage(medicineObj.getString("image"));
                        medicine.setDescription(medicineObj.getString("description"));
                        medicineList.add(medicine);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    progressDialog.dismiss();
                }
                adapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void ShowMedicine(Medicine medicine) {
        Intent directintent = new Intent(MainActivity.this, DetailActivity.class);
        Toast.makeText(this, medicine.getName(), Toast.LENGTH_SHORT).show();
        directintent.putExtra("user_email", userEmail);
        directintent.putExtra("medicine_image", medicine.getImage());
        directintent.putExtra("medicine_name", medicine.getName());
        directintent.putExtra("medicine_manufacturer", medicine.getManufacturer());
        directintent.putExtra("medicine_price", medicine.getPrice());
        directintent.putExtra("medicine_description", medicine.getDescription());
        startActivity(directintent);
    }

    @Override
    public void onItemClicked(Medicine data) {
        ShowMedicine(data);
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
        } else {
            return true;
        }
    }
}