package com.greenacademy.bai10_cauhoitracnghiem.model;

import java.util.ArrayList;

/**
 * Created by GIT on 2/25/2017.
 */

public class CauHoi {
    String NoiDungCauHoi;
    boolean DapAn;
    boolean DapAnNguoiDung;

    public String getNoiDungCauHoi() {
        return NoiDungCauHoi;
    }

    public void setNoiDungCauHoi(String NoiDungCauHoi) {
        this.NoiDungCauHoi = NoiDungCauHoi;
    }

    public boolean isDapAn() {
        return DapAn;
    }

    public void setDapAn(boolean DapAn) {
        this.DapAn = DapAn;
    }

    public boolean isDapAnNguoiDung() {
        return DapAnNguoiDung;
    }

    public void setDapAnNguoiDung(boolean DapAnNguoiDung) {
        this.DapAnNguoiDung = DapAnNguoiDung;
    }

    public boolean compareUser(){
        if(DapAnNguoiDung == DapAn){
            return true;
        }
        return false;
    }
}
