package com.greenacademy.bai06_pheptoantrenphanso;

/**
 * Created by GIT on 2/21/2017.
 */

public class PhanSo {
    private int TuSo;
    private  int MauSo;

    public PhanSo(int tuSo, int mauSo) {
        TuSo = tuSo;
        MauSo = mauSo;
    }

    public PhanSo() {
    }

    public int getTuSo() {
        return TuSo;
    }

    public void setTuSo(int tuSo) {
        TuSo = tuSo;
    }

    public int getMauSo() {
        return MauSo;
    }

    public void setMauSo(int mauSo) {
        MauSo = mauSo;
    }

    //1. Tu so 1 * mau so @ + tu so 2 * mau so 1
    //2. Mau 1 * mau 2
    public PhanSo cong(PhanSo phanso2){
        PhanSo result = null;
        int mauso = MauSo * phanso2.getMauSo();
        int tuso = TuSo * phanso2.getMauSo() + phanso2.getTuSo() * MauSo;
        result = new PhanSo(tuso, mauso);
        result.rutgon();
        return result;
    }

    public PhanSo tru(PhanSo phanso2){
        PhanSo result = null;
        int mauso = MauSo * phanso2.getMauSo();
        int tuso = TuSo * phanso2.getMauSo() - phanso2.getTuSo() * MauSo;
        result = new PhanSo(tuso, mauso);
        result.rutgon();
        return result;
    }

    public PhanSo nhan(PhanSo phanso2){
        PhanSo result = null;
        int mauso = MauSo * phanso2.getMauSo();
        int tuso = TuSo * phanso2.getTuSo();
        result = new PhanSo(tuso, mauso);
        result.rutgon();
        return result;
    }

    public PhanSo chia(PhanSo phanso2){
        PhanSo result = null;
        int mauso = phanso2.getMauSo() * MauSo;
        int tuso = TuSo * phanso2.getTuSo();
        result = new PhanSo(tuso,mauso);
        result.rutgon();
        return result;
    }

    public void rutgon(){
        int tumoi = Math.abs(TuSo);
        int maumoi = Math.abs(MauSo);
        int UCLN = timUCLN(tumoi, maumoi);
        TuSo = TuSo / UCLN;
        MauSo = MauSo / UCLN;
    }

    private int timUCLN(int tumoi, int maumoi) {
        int result = 1;
        while(maumoi != 0)
        {
            result = tumoi % maumoi;
            tumoi = maumoi;
            maumoi = result;
        }
        result = tumoi;
        return result;
    }

}
