package com.example.user.demoslidemenu.model;

/**
 * Created by User on 4/22/2017.
 */

public class HinhChuNhat {
    private int dai;
    private int rong;

    public HinhChuNhat(int dai, int rong) {
        this.dai = dai;
        this.rong = rong;
    }

    public int getDai() {
        return dai;
    }

    public int getRong() {
        return rong;
    }

    public int TinhChuVi(){
        return (dai+rong)*2;
    }

    public int TinhDienTich(){
        return dai*rong;
    }
}
