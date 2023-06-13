package com.example.uas.model;

import com.example.uas.R;

import java.util.ArrayList;

public class MedicineData {

    private static final String[] name = {
            //
    };

    private static final String[] manufacturer = {
            //
    };

    private static final String[] price = {
            //
    };

    private static final int[] image = {
            //
    };

    private static final String[] description = {
            //
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
