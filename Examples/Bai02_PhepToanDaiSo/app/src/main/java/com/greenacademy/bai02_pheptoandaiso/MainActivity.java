package com.greenacademy.bai02_pheptoandaiso;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //1. Khai bao view
    EditText etInput1;
    EditText etInput2;
    Button btnPhepCong;
    Button btnPhepTru;
    Button btnPhepNhan;
    Button btnPhepChia;
    TextView tvOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //2. Anh xa
        etInput1 = (EditText) findViewById(R.id.etSoThuNhat);
        etInput2 = (EditText) findViewById(R.id.etSoThuHai);
        btnPhepCong = (Button) findViewById(R.id.btnCong);
        btnPhepTru = (Button) findViewById(R.id.btnTru);
        btnPhepNhan = (Button) findViewById(R.id.btnNhan);
        btnPhepChia = (Button) findViewById(R.id.btnChia);
        tvOutput = (TextView) findViewById(R.id.tvResult);

        //3. Dang ky xu ly su kien
        btnPhepCong.setOnClickListener(this);
        btnPhepTru.setOnClickListener(this);
        btnPhepNhan.setOnClickListener(this);
        btnPhepChia.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //I. Kiem tra EditText co nhap du lieu chua
        String strNumber1 = etInput1.getText().toString();
        if(strNumber1.isEmpty()){
            return;
        }
        String strNumber2 = etInput2.getText().toString();
        if(strNumber2.isEmpty()){
            return;
        }

        double number1 = Double.parseDouble(strNumber1);
        double number2 = Double.parseDouble(strNumber2);
        double result = 0;

        //II. Thuc hien phep toan
        switch (v.getId()){
            case R.id.btnCong:
                result = number1 + number2;
                break;
            case R.id.btnTru:
                result = number1 - number2;
                break;
            case R.id.btnNhan:
                result = number1 * number2;
                break;
            case R.id.btnChia:
                result = number1/number2;
                break;
        }

        //III. Ket qua
        tvOutput.setText(String.valueOf(result));
    }
}
