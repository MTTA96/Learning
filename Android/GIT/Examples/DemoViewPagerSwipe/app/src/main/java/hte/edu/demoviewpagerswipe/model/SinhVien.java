package hte.edu.demoviewpagerswipe.model;

/**
 * Created by User on 4/25/2017.
 */

public class SinhVien {
    private String MSSV;
    private String HoTen;
    private float DiemLT;
    private float DiemTH;

    public SinhVien(String MSSV, String hoTen, float diemLT, float diemTH) {
        this.MSSV = MSSV;
        DiemTH = diemTH;
        DiemLT = diemLT;
        HoTen = hoTen;
    }

    public String getMSSV() {
        return MSSV;
    }

    public float getDiemTH() {
        return DiemTH;
    }

    public float getDiemLT() {
        return DiemLT;
    }

    public String getHoTen() {
        return HoTen;
    }
}
