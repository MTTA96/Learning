package com.example.user.finalbasicproject.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.finalbasicproject.MainActivity;
import com.example.user.finalbasicproject.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class HSignUpFragment extends Fragment {
    private static HSignUpFragment instance;
    EditText etUser;
    EditText etPassword;
    EditText etDiaChi;
    EditText etSDT;
    EditText etCMND;
    EditText etEmail;
    Button btnDangKy;
    CheckBox cbDieuKhoan;

    public static HSignUpFragment getInstance(){
        if(instance == null){
            instance = new HSignUpFragment();
        }
        return instance;
    }

    public HSignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hsign_up, container, false);
        etUser = (EditText) view.findViewById(R.id.et_User_HSignup);
        etPassword = (EditText) view.findViewById(R.id.et_Password_HSignup);
        etDiaChi = (EditText) view.findViewById(R.id.et_DiaChi_HSignup);
        etSDT = (EditText) view.findViewById(R.id.et_SoDienThoai_HSignup);
        etCMND = (EditText) view.findViewById(R.id.et_CMND_HSignup);
        etEmail = (EditText) view.findViewById(R.id.et_Email_HSignup);
        btnDangKy = (Button) view.findViewById(R.id.btn_DangKy_HSignup);
        cbDieuKhoan = (CheckBox) view.findViewById(R.id.cb_DieuKhoan_HSignup);

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etUser.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty() || etDiaChi.getText().toString().isEmpty()
                        || etSDT.getText().toString().isEmpty() || etCMND.getText().toString().isEmpty() || etEmail.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Chưa nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!cbDieuKhoan.isChecked()){
                    Toast.makeText(getActivity(), "Chưa đồng ý điều khoản!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }


}
