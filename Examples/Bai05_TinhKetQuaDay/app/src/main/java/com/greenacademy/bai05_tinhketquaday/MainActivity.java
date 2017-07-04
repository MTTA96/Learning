package com.greenacademy.bai05_tinhketquaday;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etInputNumber;
    TextView tvResult;
    Button btnNhapLai;
    Button btnGiai;
    RadioGroup rgDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etInputNumber = (EditText) findViewById(R.id.etNhapSo);
        tvResult = (TextView) findViewById(R.id.tvKetQua);
        btnNhapLai = (Button) findViewById(R.id.btnNhapLai);
        btnGiai = (Button) findViewById(R.id.btnGiai);
        rgDay = (RadioGroup) findViewById(R.id.rgDay);

        btnNhapLai.setOnClickListener(this);
        btnGiai.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //I. Lấy và kiểm tra input có đươc nhập chưa.
        String inputnumber = etInputNumber.getText().toString();
        if(inputnumber.isEmpty())
            return;
        switch(v.getId()){
            case (R.id.btnNhapLai):
                etInputNumber.setText("");
                break;
            case(R.id.btnGiai):
                //II. Chuyển chuỗi thành số.
                int intNumber = Integer.parseInt(inputnumber);

                //III. Kiểm tra người dùng đang click vào dãy nào.
                double dbResult = 0;
                switch(rgDay.getCheckedRadioButtonId()){
                    case R.id.radDay1:
                        dbResult = tinhDay1(intNumber);
                        break;
                    case R.id.radDay2:
                        dbResult = tinhDay2(intNumber);
                        break;
                    case R.id.radDay3:
                        dbResult = tinhDay3(intNumber);
                        break;
                }
                //V. Hiển thị giá trị lên textview.
                tvResult.setText(String.valueOf(dbResult));
                break;
        }
    }

    private double tinhDay3(double intNumber) {
        double result = 0;
        for (double i = 1; i <=intNumber ; i++) {
            result = (Math.pow(-1,i+1) * i);
        }
        return result;
    }

    private double tinhDay2(double intNumber) {
        double result = 0;
        for (double i = 1; i <=intNumber ; i++) {
            result += (i/(i+1));
        }
        return result;
    }

    private double tinhDay1(double intNumber) {
        double result = 0;
        for(double i=1; i<=intNumber; i ++)
            result += i;
        return result;
    }

}
