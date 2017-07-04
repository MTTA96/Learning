package com.greenacademy.bai06_pheptoantrenphanso;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etTuSo1;
    EditText etMauSo1;
    EditText etTuSo2;
    EditText etMauSo2;
    TextView tvResult;
    Button btnPhepCong;
    Button btnPhepTru;
    Button btnPhepNhan;
    Button btnPhepChia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ
        etTuSo1 = (EditText) findViewById(R.id.etTu1);
        etMauSo1 = (EditText) findViewById(R.id.etMau1);
        etTuSo2 = (EditText) findViewById(R.id.etTu2);
        etMauSo2 = (EditText) findViewById(R.id.etMau2);
        tvResult = (TextView) findViewById(R.id.tvKetQua);
        btnPhepCong = (Button) findViewById(R.id.btnCong);
        btnPhepTru = (Button) findViewById(R.id.btnTru);
        btnPhepNhan = (Button) findViewById(R.id.btnNhan);
        btnPhepChia = (Button) findViewById(R.id.btnChia);

        //Xử lý sự kiện
        btnPhepCong.setOnClickListener(this);
        btnPhepTru.setOnClickListener(this);
        btnPhepNhan.setOnClickListener(this);
        btnPhepChia.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String strTuSo1 = etTuSo1.getText().toString();
        String strTuSo2 = etTuSo2.getText().toString();
        String strMauso1= etMauSo1.getText().toString();
        String strMauso2 = etMauSo2.getText().toString();
        if(strTuSo1.isEmpty() && strMauso1.isEmpty() && strTuSo2.isEmpty() && strMauso2.isEmpty())
            return;

        //II. Chuyen chuỗi sang số
        int intTuSo1 = Integer.parseInt(strTuSo1);
        int intTuSo2 = Integer.parseInt(strTuSo2);
        int intMauSo1 = Integer.parseInt(strMauso1);
        int intMauSo2 = Integer.parseInt(strMauso2);
        PhanSo phanso1 = new PhanSo(intTuSo1, intMauSo1);
        PhanSo phanso2 =  new PhanSo(intTuSo2, intMauSo2);
        PhanSo result = null;
        switch(v.getId())
        {
            case R.id.btnCong:
                result = phanso1.cong(phanso2);
                break;
            case R.id.btnTru:
                result = phanso1.tru(phanso2);
                break;
            case R.id.btnNhan:
                result = phanso1.nhan(phanso2);
                break;
            case R.id.btnChia:
                result = phanso1.chia(phanso2);
                break;
        }

        tvResult.setText(String.valueOf(result.getTuSo()) + "/" + String.valueOf(result.getMauSo()));
    }
}
