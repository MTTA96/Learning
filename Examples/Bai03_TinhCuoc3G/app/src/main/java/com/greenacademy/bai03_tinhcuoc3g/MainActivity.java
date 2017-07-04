package com.greenacademy.bai03_tinhcuoc3g;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText etInput;
    TextView tvReuslt;
    Button btnTinh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInput = (EditText) findViewById(R.id.etInputNumber);
        tvReuslt = (TextView) findViewById(R.id.tvResult);
        btnTinh = (Button) findViewById(R.id.btnHandle);

        btnTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //1. Kiem tra user da nhap du lieu chua
                String strNumber = etInput.getText().toString();
                if(strNumber.isEmpty())
                    return;
            //2. Chuyen tu chuoi sang so
                int Cuoc = Integer.parseInt(strNumber);
            //3. Tinh cuoc
                String result = TinhCuoc(Cuoc);
                tvReuslt.setText(result);
            }
        });
    }
    public String TinhCuoc (int Cuoc){
        String result = "";
        if(Cuoc > 0 && Cuoc <= 100)
            result = "10000";
        else
            if(Cuoc <= 250)
                result = "25000";
            else
                return String.valueOf(Cuoc * 1200);
        return result;
    }
}
