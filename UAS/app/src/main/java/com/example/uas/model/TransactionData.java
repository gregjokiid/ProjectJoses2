package com.example.uas.model;

import java.util.ArrayList;

public class TransactionData {
    private static final String[] MedicineId = {
            "Rumor Player Filipina Kelra Gabung ONIC Esports Menguat, Ini Buktinya!",
            "Profil Vonzy, Brand Ambassador Berparas Cantik dari ONIC Esports",
            "3 Hal yang Dievaluasi ONIC Esports untuk M4 World Championship",
            "ONIC Esports Juara MPL ID S10, CW: Kita Belajar Hari Ini Buat di M4",
            "Inilah Sosok Dibalik Gimmick Kendaraan ONIC Esports di MPL ID S10",
            "CW Main Lylia Goldlane di MPL ID S10 W7, Ternyata Ini Alasannya",
            "Build Fanny Tersakit Versi ONIC Kairi di Royal Derby Leg 2 MPL ID S10",
            "Jadwal MPL ID S10 Week 5: Ada Royal Derby RRQ vs ONIC Esports!",
            "ONIC Esports Punya Pelatih Fisik di MPL ID S10, Apa Saja Latihannya?",
            "Sempat Unggul, BTR Alpha Gagal Comeback ONIC. Ini Analisa Coach Aldo!"
    };

    private static final String[] TransactionDate = {
            "15 Juni 2023",
            "15 Juni 2023",
            "15 Juni 2023",
            "15 Juni 2023",
            "15 Juni 2023",
            "15 Juni 2023",
            "15 Juni 2023",
            "15 Juni 2023",
            "15 Juni 2023",
            "15 Juni 2023"
    };

    private static final String[] Quantity = {
            "15",
            "15",
            "15",
            "15",
            "15",
            "15",
            "15",
            "15",
            "15",
            "15"
    };

    public static ArrayList<Transaction> getListData() {
        ArrayList<Transaction> list = new ArrayList<>();
        for(int position = 0; position < MedicineId.length; position++) {
            Transaction transaction = new Transaction();
            transaction.setMedicineId(MedicineId[position]);
            transaction.setTransactionDate(TransactionDate[position]);
            transaction.setQuantity(Integer.parseInt(Quantity[position]));
            list.add(transaction);
        }
        return list;
    }
}
