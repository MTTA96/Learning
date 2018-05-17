package com.example.user.demoslidemenu.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.demoslidemenu.R;
import com.example.user.demoslidemenu.model.HinhChuNhat;

/**
 * A simple {@link Fragment} subclass.
 */
public class HinhChuNhatFragment extends Fragment implements View.OnClickListener {
    EditText etChieuDai;
    EditText etChieuRong;
    Button btnTinh;
    TextView tvKetQuaChuVi;
    TextView tvKetQuaDienTich;

    HinhChuNhat HCN;

    public HinhChuNhatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hinh_chu_nhat, container, false);
        etChieuDai = (EditText) view.findViewById(R.id.etChieuDai_HCNFragment);
        etChieuRong = (EditText) view.findViewById(R.id.etChieuRong_HCNFragment);
        btnTinh = (Button) view.findViewById(R.id.btnTinh_HCNFragment);
        tvKetQuaChuVi = (TextView) view.findViewById(R.id.tvKetQuaChuVi_HCNFragment);
        tvKetQuaDienTich = (TextView) view.findViewById(R.id.tvKetQuaDienTich_HCNFragment);

        btnTinh.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        HCN = new HinhChuNhat(Integer.parseInt(etChieuDai.getText().toString()),Integer.parseInt(etChieuRong.getText().toString()));
        int result;
        switch (v.getId()){
            case R.id.btnTinh_HCNFragment:
                if(etChieuDai.getText().toString().isEmpty() || etChieuRong.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Chưa nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
                    return;
                }
                result = HCN.TinhChuVi();
                tvKetQuaChuVi.setText(String.valueOf(result));
                result = HCN.TinhDienTich();
                tvKetQuaDienTich.setText(String.valueOf(result));
                break;
        }
    }
}
