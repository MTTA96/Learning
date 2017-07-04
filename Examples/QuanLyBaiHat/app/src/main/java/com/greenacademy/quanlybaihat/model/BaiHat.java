package com.greenacademy.quanlybaihat.model;

import java.io.Serializable;

/**
 * Created by GIT on 3/11/2017.
 */

//Chuyền dữ liệu từ layout hoặc internet phải sử dụn implements Serializable
public class BaiHat implements Serializable{
    int hinhCaSi;
    String tenBaiHat;
    String tenCaSi;
    String thoiGian;

    public int getHinhCaSi() {
        return hinhCaSi;
    }

    public void setHinhCaSi(int hinhCaSi) {
        this.hinhCaSi = hinhCaSi;
    }

    public String getTenBaiHat() {
        return tenBaiHat;
    }

    public void setTenBaiHat(String tenBaiHat) {
        this.tenBaiHat = tenBaiHat;
    }

    public String getTenCaSi() {
        return tenCaSi;
    }

    public void setTenCaSi(String tenCaSi) {
        this.tenCaSi = tenCaSi;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }
}
