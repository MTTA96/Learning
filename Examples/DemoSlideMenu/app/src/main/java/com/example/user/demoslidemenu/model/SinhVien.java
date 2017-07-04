package com.example.user.demoslidemenu.model;

/**
 * Created by User on 4/22/2017.
 */

public class SinhVien {
    private String ID;
    private String HoTen;
    private float DiemToan;
    private float DiemLy;
    private float DiemHoa;

    public SinhVien(String ID, String hoTen, float diemToan, float diemLy, float diemHoa) {
        this.ID = ID;
        HoTen = hoTen;
        DiemToan = diemToan;
        DiemLy = diemLy;
        DiemHoa = diemHoa;
    }

    public String getID() {
        return ID;
    }

    public String getHoTen() {
        return HoTen;
    }

    public float getDiemLy() {
        return DiemLy;
    }

    public float getDiemHoa() {
        return DiemHoa;
    }

    public float TinhDTB(){
        return (DiemToan + DiemLy + DiemHoa)/3;
    }
}
