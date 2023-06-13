package com.example.uas.model;

import com.example.uas.R;

import java.util.ArrayList;

public class MedicineData {

    private static final String[] name = {
            //
    };

    private static final String[] description = {
            //
    };

    private static final String[] author = {
            //
    };

    private static final int[] image = {
            //
    };

    public static ArrayList<Medicine> getListData() {
        ArrayList<Medicine> list = new ArrayList<>();
        for(int position = 0; position < name.length; position++) {
            Medicine medicine = new Medicine();
            medicine.setName(name[position]);
            medicine.setDescription(description[position]);
            medicine.setAuthor(author[position]);
            medicine.setImage(image[position]);
            list.add(medicine);
        }
        return list;
    }

}
