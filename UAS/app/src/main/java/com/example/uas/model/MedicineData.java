package com.example.uas.model;

import com.example.uas.R;

import java.util.ArrayList;

public class MedicineData {

    private static final String[] name = {
            "Ibuprofen",
            "Betadine",
            "Neurobion",
            "Sangobion",
            "Bisolvon"
    };

    private static final String[] manufacturer = {
            "PT Kimia Farma",
            "PT Kimia Farma",
            "PT Kimia Farma",
            "PT Kimia Farma",
            "PT Kimia Farma"
    };

    private static final String[] price = {
            "Rp12000",
            "Rp12000",
            "Rp12000",
            "Rp12000",
            "Rp12000"
    };

    private static final int[] image = {
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background
    };

    private static final String[] description = {
            "Ini adalah oralit",
            "Ini adalah oralit",
            "Ini adalah oralit",
            "Ini adalah oralit",
            "Ini adalah oralit"
    };

    public static ArrayList<Medicine> getListData() {
        ArrayList<Medicine> list = new ArrayList<>();
        for(int position = 0; position < name.length; position++) {
            Medicine medicine = new Medicine();
            medicine.setName(name[position]);
            medicine.setManufacturer(manufacturer[position]);
            medicine.setPrice(price[position]);
            medicine.setImage(image[position]);
            medicine.setDescription(description[position]);
            list.add(medicine);
        }
        return list;
    }

}
