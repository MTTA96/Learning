package com.example.user.demoslidemenu.control;

import com.example.user.demoslidemenu.model.SinhVien;

import java.util.ArrayList;

/**
 * Created by User on 4/22/2017.
 */

public class HandleSinhVien {
    private ArrayList<SinhVien> listSinhVien;

    public HandleSinhVien(ArrayList<SinhVien> listSinhVien) {
        this.listSinhVien = listSinhVien;
    }

    public ArrayList<SinhVien> getListSinhVien() {
        return listSinhVien;
    }
}
