package com.greenacademy.homeassistance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnLogin;
    Button btnSignUp;
    CheckBox chkRemember;
    EditText etUserName;
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = (Button) findViewById(R.id.btnLoginMain);
        btnSignUp = (Button) findViewById(R.id.btnSignUpMain);
        chkRemember = (CheckBox) findViewById(R.id.cbRememberMain);
        etUserName = (EditText) findViewById(R.id.etInputNameMain);
        etPassword = (EditText) findViewById(R.id.etPasswordMain);

        btnSignUp.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLoginMain:
                String strUser = etUserName.getText().toString();
                String strPassword = etPassword.getText().toString();
                //1. Kiểm tra input
                if(strUser.isEmpty() || strPassword.isEmpty()){

                }
                //2. Kiểm tra tài khoản

                //3. Kiểm tra checkbox
                break;
            case R.id.btnSignUpMain:
                //Chuyển sang màn hình Sign Up
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
                break;
        }
    }
}
