package com.greenacademy.redboxentertainment.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.greenacademy.redboxentertainment.R;
import com.greenacademy.redboxentertainment.model.CD;
import com.greenacademy.redboxentertainment.sqlite.MySQLite;

/**
 * A simple {@link Fragment} subclass.
 */
public class Them_CD_Nhac extends Fragment implements View.OnClickListener {

    // khai bao
    EditText etTenBaiHat;
    EditText etCaSi;
    EditText etGiaCDNhac;
    EditText etCodeCDNhac;
    EditText etSoLuongCDNhac;

    Button btnInsertNhac;
    Button btnResetNhac;

    public Them_CD_Nhac() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_them__cd__nhac, container, false);
        // anh xa
        etTenBaiHat = (EditText) view.findViewById(R.id.etTenBaiHat);
        etCaSi = (EditText) view.findViewById(R.id.etCaSi);
        etGiaCDNhac = (EditText) view.findViewById(R.id.etGiaCDNhac);
        etCodeCDNhac = (EditText) view.findViewById(R.id.etCodeCDNhac);
        etSoLuongCDNhac = (EditText) view.findViewById(R.id.etSoLuongCDNhac);

        btnInsertNhac = (Button) view.findViewById(R.id.btnInsertNhac);
        btnResetNhac = (Button) view.findViewById(R.id.btnResetNhac);

        btnInsertNhac.setOnClickListener(this);
        btnResetNhac.setOnClickListener(this);

        return view;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnInsertNhac:
                CD cd = new CD();
                MySQLite mySQLite = new MySQLite(getActivity());
                cd.setTenCD(etTenBaiHat.getText().toString());
                cd.setGia(etGiaCDNhac.getText().toString());
                cd.setSoluong(etSoLuongCDNhac.getText().toString());
                cd.setCode(etCodeCDNhac.getText().toString());
                cd.setTacgia(etCaSi.getText().toString());
                cd.setLoaiCD(1);
                mySQLite.addCD(cd);
                break;
            case R.id.btnResetNhac:
                etCaSi.setText("");
                etTenBaiHat.setText("");
                etCodeCDNhac.setText("");
                etGiaCDNhac.setText("");
                etSoLuongCDNhac.setText("");
                break;
        }
    }
}
