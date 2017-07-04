package com.example.user.travelapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.user.travelapp.R;
import com.example.user.travelapp.asynctask.DangNhapAsyncTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class DangNhapFragment extends Fragment {
    EditText etUserName;
    EditText etPassword;
    Button btnDangNhap;

    public DangNhapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_dang_nhap, container, false);
        etUserName = (EditText) root.findViewById(R.id.etUesrName_DangNhapFragment);
        etPassword = (EditText) root.findViewById(R.id.etPassword_DangNhapFragment);
        btnDangNhap = (Button) root.findViewById(R.id.btnDangNhap_DangNhapFragment);

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangNhapAsyncTask dangNhapAsyncTask = new DangNhapAsyncTask(getContext());
                dangNhapAsyncTask.execute(etUserName.getText().toString(), etPassword.getText().toString());
            }
        });
        return root;
    }

}
