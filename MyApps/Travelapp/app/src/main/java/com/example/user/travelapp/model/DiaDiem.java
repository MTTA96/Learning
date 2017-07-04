package com.example.user.travelapp.model;

/**
 * Created by zzzzz on 5/18/2017.
 */

public class DiaDiem {
    private int ID;
    private String tenKhuVuc;
    private String moTa;
    private String linkAnh;
    private int soLuotXem;
    private int yeuThich;
    private float danhGia;

//    public DiaDiem(String name, String description, int imgDiaDiem, int view, int like, float rating) {
//        this.name = name;
//        this.description = description;
//        this.imgDiaDiem = imgDiaDiem;
//        this.view = view;
//        this.like = like;
//        this.rating = rating;
//    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenKhuVuc() {
        return tenKhuVuc;
    }

    public void setTenKhuVuc(String tenKhuVuc) {
        this.tenKhuVuc = tenKhuVuc;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getLinkAnh() {
        return linkAnh;
    }

    public void setLinkAnh(String linkAnh) {
        this.linkAnh = linkAnh;
    }

    public int getSoLuotXem() {
        return soLuotXem;
    }

    public void setSoLuotXem(int soLuotXem) {
        this.soLuotXem = soLuotXem;
    }

    public int getYeuThich() {
        return yeuThich;
    }

    public void setYeuThich(int yeuThich) {
        this.yeuThich = yeuThich;
    }

    public float getDanhGia() {
        return danhGia;
    }

    public void setDanhGia(float danhGia) {
        this.danhGia = danhGia;
    }
}

