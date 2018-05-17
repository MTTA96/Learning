package com.example.user.finalbasicproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class SignIn extends AppCompatActivity implements View.OnClickListener {
    EditText etUser;
    EditText etPassword;
    CheckBox cbLuuDangNhap;
    Button btnDangNhap;
    Button btnDangKy;
    Button btnKhach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //Ánh xạ
        etUser = (EditText) findViewById(R.id.et_User_SignIn);
        etPassword = (EditText) findViewById(R.id.et_Password_SignIn);
        btnDangKy = (Button) findViewById(R.id.btn_DangKy_SignIn);
        btnDangNhap = (Button) findViewById(R.id.btn_DangNhap_SignIn);
        btnKhach = (Button) findViewById(R.id.btn_Khach_SignIn);
        cbLuuDangNhap = (CheckBox) findViewById(R.id.cb_LuuDangNhap_SignIn);

        //Xử lý sự kiện
        btnDangNhap.setOnClickListener(this);
        btnDangKy.setOnClickListener(this);
        btnKhach.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_DangNhap_SignIn:
                if (etUser.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty()){
                    Toast.makeText(SignIn.this, "Chưa nhập user hoặc password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intentDangNhap = new Intent(this, MainActivity.class);
                startActivity(intentDangNhap);
                break;
            case R.id.btn_DangKy_SignIn:
                Intent intentDangKy = new Intent(this, ChonViec.class);
                startActivity(intentDangKy);
                break;
            case R.id.btn_Khach_SignIn:
                Intent  intentKhach = new Intent(this, MainActivity.class);
                startActivity(intentKhach);
                break;
        }
    }
}
