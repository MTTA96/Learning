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
public class Them_CD_Phim extends Fragment implements View.OnClickListener {

    // khai bao
    EditText etTenBoPhim;
    EditText etDaoDien;
    EditText etGiaCDPhim;
    EditText etCodeCDPhim;
    EditText etSoLuongCDPhim;

    Button btnInsertPhim;
    Button btnResetPhim;
    
    public Them_CD_Phim() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_them__cd__phim, container, false);
        // anh xa
        etTenBoPhim = (EditText) view.findViewById(R.id.etTenBoPhim);
        etDaoDien = (EditText) view.findViewById(R.id.etDaoDien);
        etGiaCDPhim = (EditText) view.findViewById(R.id.etGiaCDPhim);
        etCodeCDPhim = (EditText) view.findViewById(R.id.etCodeCDPhim);
        etSoLuongCDPhim = (EditText) view.findViewById(R.id.etSoLuongCDPhim);

        btnInsertPhim = (Button) view.findViewById(R.id.btnInsertPhim);
        btnResetPhim = (Button) view.findViewById(R.id.btnResetPhim);

        btnInsertPhim.setOnClickListener(this);
        btnResetPhim.setOnClickListener(this);
        
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
            case R.id.btnInsertPhim:
                CD cd = new CD();
                MySQLite mySQLite = new MySQLite(getActivity());
                cd.setTenCD(etTenBoPhim.getText().toString());
                cd.setGia(etGiaCDPhim.getText().toString());
                cd.setSoluong(etSoLuongCDPhim.getText().toString());
                cd.setCode(etCodeCDPhim.getText().toString());
                cd.setTacgia(etDaoDien.getText().toString());
                cd.setLoaiCD(0);
                mySQLite.addCD(cd);
                break;
            case R.id.btnResetPhim:
                etDaoDien.setText("");
                etCodeCDPhim.setText("");
                etSoLuongCDPhim.setText("");
                etGiaCDPhim.setText("");
                etTenBoPhim.setText("");
                break;
        }
    }
}
