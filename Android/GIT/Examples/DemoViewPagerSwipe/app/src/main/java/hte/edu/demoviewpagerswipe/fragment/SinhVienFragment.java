package hte.edu.demoviewpagerswipe.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import hte.edu.demoviewpagerswipe.R;
import hte.edu.demoviewpagerswipe.control.ManageSinhVien;
import hte.edu.demoviewpagerswipe.model.SinhVien;

/**
 * A simple {@link Fragment} subclass.
 */
public class SinhVienFragment extends Fragment implements View.OnClickListener {
    EditText etMSSV;
    EditText etTenSV;
    EditText etDLT;
    EditText etDTH;
    Button btnThongTin;
    Button btnThem;
    Button btnXoa;

    ManageSinhVien manageSinhVien;
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
        etMSSV = (EditText) view.findViewById(R.id.etMSSV_FragmentSinhVien);
        etTenSV = (EditText) view.findViewById(R.id.etTenSinhVien_FragmentSinhVien);
        etDLT = (EditText) view.findViewById(R.id.etDiemLyThuyet_FragmentSinhVien);
        etDTH = (EditText) view.findViewById(R.id.etDiemThuHanh_FragmentSinhVien);
        btnThem = (Button) view.findViewById(R.id.btnThem_FragmentSinhVien);
        btnXoa = (Button) view.findViewById(R.id.btnXoa_FragmentSinhVien);

        btnThongTin.setOnClickListener(this);
        btnThem.setOnClickListener(this);
        btnXoa.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnThem_FragmentSinhVien:
                if(etMSSV.getText().toString().isEmpty() || etTenSV.getText().toString().isEmpty() || etDLT.getText().toString().isEmpty() || etDTH.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Chưa nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
                    return;
                }
                String mssv = etMSSV.getText().toString();
                String tenSV = etTenSV.getText().toString();
                float dlt = Float.parseFloat(etDLT.getText().toString());
                float dth = Float.parseFloat(etDTH.getText().toString());

                sinhVien = new SinhVien();

                break;
            case R.id.btnXoa_FragmentSinhVien:
                etMSSV.setText("");
                etTenSV.setText("");
                etDTH.setText("");
                etDLT.setText("");
                break;
        }
    }
}
