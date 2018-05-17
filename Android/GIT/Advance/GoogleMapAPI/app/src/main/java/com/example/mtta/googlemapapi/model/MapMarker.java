package com.example.mtta.googlemapapi.model;

/**
 * Created by 508-2 on 6/15/2017.
 */

public class MapMarker {
    private int id;
    private String ten;
    private String moTa;
    private int danhGia;
    private int soLuotXem;
    private int yeuThich;
    private int checkIn;
    private String loaiMarKer;
    private String linkAnh;
    private double lat;
    private double lng;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getDanhGia() {
        return danhGia;
    }

    public void setDanhGia(int danhGia) {
        this.danhGia = danhGia;
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

    public int getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(int checkIn) {
        this.checkIn = checkIn;
    }

    public String getLoaiMarKer() {
        return loaiMarKer;
    }

    public void setLoaiMarKer(String loaiMarKer) {
        this.loaiMarKer = loaiMarKer;
    }

    public String getLinkAnh() {
        return linkAnh;
    }

    public void setLinkAnh(String linkAnh) {
        this.linkAnh = linkAnh;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
