package com.greenacademy.redboxentertainment.model;

/**
 * Created by ADMIN on 4/29/2017.
 */

public class CD {
    String tenCD;
    String tacgia;
    String code;
    String gia;
    String soluong;
    int loaiCD;

    public int getLoaiCD() {
        return loaiCD;
    }

    public void setLoaiCD(int loaiCD) {
        this.loaiCD = loaiCD;
    }

    public String getTenCD() {
        return tenCD;
    }

    public void setTenCD(String tenCD) {
        this.tenCD = tenCD;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }
}
