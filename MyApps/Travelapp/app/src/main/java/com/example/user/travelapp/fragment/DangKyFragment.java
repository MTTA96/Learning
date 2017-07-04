package com.example.user.travelapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.user.travelapp.R;
import com.example.user.travelapp.asynctask.DangKyAsyncTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class DangKyFragment extends Fragment {
    EditText etUserName;
    EditText etPassword;
    EditText etTenHienThi;
    Button btnDangKy;

    public DangKyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_dang_ky, container, false);
        etUserName = (EditText) root.findViewById(R.id.etUserName_DangKyFragment);
        etPassword = (EditText) root.findViewById(R.id.etPassword_DangKyFragment);
        etTenHienThi = (EditText) root.findViewById(R.id.etTenHienThi_DangKyFragment);
        btnDangKy = (Button) root.findViewById(R.id.btnDangKy_DangKyFragment);

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangKyAsyncTask dangKyAsyncTask = new DangKyAsyncTask(getContext());
                dangKyAsyncTask.execute(etUserName.getText().toString(), etPassword.getText().toString(), etTenHienThi.getText().toString());
            }
        });
        return root;
    }
}
