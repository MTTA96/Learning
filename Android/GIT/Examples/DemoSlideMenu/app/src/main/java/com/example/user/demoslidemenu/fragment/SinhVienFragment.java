package com.example.user.demoslidemenu.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.demoslidemenu.R;
import com.example.user.demoslidemenu.control.HandleSinhVien;
import com.example.user.demoslidemenu.model.SinhVien;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SinhVienFragment extends Fragment implements View.OnClickListener {
    EditText etMSSV;
    EditText etNamSinh;
    EditText etToan;
    EditText etLy;
    EditText etHoa;
    Button btnThem;
    Button btnDanhSach;
    Button btnNhapLai;

    HandleSinhVien handleSinhVien;
    ArrayList<SinhVien> listSinhVien;
    SinhVien sinhVien;

    public SinhVienFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sinh_vien, container, false);
        etMSSV = (EditText) view.findViewById(R.id.etMSSV_SinhVienFragment);
        etNamSinh = (EditText) view.findViewById(R.id.etNamSinh_SinhVienFragment);
        etToan = (EditText) view.findViewById(R.id.etToan_SinhVienFragment);
        etLy = (EditText) view.findViewById(R.id.etLy_SinhVienFragment);
        etHoa = (EditText) view.findViewById(R.id.etHoa_SinhVienFragment);
        btnThem = (Button) view.findViewById(R.id.btnThem_SinhVienFragment);
        btnDanhSach = (Button) view.findViewById(R.id.btnDanhSach_SinhVienFragment);
        btnNhapLai = (Button) view.findViewById(R.id.btnNhapLai_SinhVienFragment);

        btnThem.setOnClickListener(this);
        btnDanhSach.setOnClickListener(this);
        btnNhapLai.setOnClickListener(this);

        listSinhVien = new ArrayList<>();
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnThem_SinhVienFragment:
                if(etMSSV.getText().toString().isEmpty() || etNamSinh.getText().toString().isEmpty() || etToan.getText().toString().isEmpty() || etHoa.getText().toString().isEmpty() || etLy.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Chưa nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
                    return;
                }
                String mssv = etMSSV.getText().toString();
                String namSinh = etNamSinh.getText().toString();
                float diemToan = Float.parseFloat(etToan.getText().toString());
                float diemLy = Float.parseFloat(etLy.getText().toString());
                float diemHoa = Float.parseFloat(etHoa.getText().toString());
                sinhVien = new SinhVien(mssv, namSinh, diemToan, diemLy, diemHoa);

                break;
            case R.id.btnDanhSach_SinhVienFragment:
                break;
            case R.id.btnNhapLai_SinhVienFragment:
                etMSSV.setText("");
                etNamSinh.setText("");
                etToan.setText("");
                etLy.setText("");
                etHoa.setText("");
                break;
        }
    }
}
