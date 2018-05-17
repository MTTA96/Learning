package hte.edu.demoviewpagerswipe.control;

import java.util.ArrayList;

import hte.edu.demoviewpagerswipe.model.SinhVien;

/**
 * Created by User on 4/25/2017.
 */

public class ManageSinhVien {
    ArrayList<SinhVien> listSinhVien;
    SinhVien sinhVien;
    float DTB;

    public ManageSinhVien(ArrayList<SinhVien> listSinhVien) {
        this.listSinhVien = listSinhVien;
    }

    public void NhapThongTinSV(String mssv, String tenSV, float dlt, float dth){
        sinhVien = new SinhVien(mssv, tenSV, dlt, dth);
    }
    public float TinhDTB(SinhVien sinhVien){
        return DTB = (sinhVien.getDiemLT()+ sinhVien.getDiemTH())/2;
    }

    public String XuatThongTinSV(SinhVien sinhVien){
        if(TinhDTB(sinhVien) >= 5){
            return "Đậu";
        }
        return "Rớt";
    }
}
