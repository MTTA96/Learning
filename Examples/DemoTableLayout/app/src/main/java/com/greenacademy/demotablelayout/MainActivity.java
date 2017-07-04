package com.greenacademy.demotablelayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvDisplay;
    Button btnDelete;
    Button btnCham;
    Button btnBang;
    Button btnCong;
    Button btnTru;
    Button btnNhan;
    Button btnChia;
    Button btn0;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    Button btn8;
    Button btn9;

    float SoHang1;
    float SoHang2;
    int operator;
    String strResult;
    float KetQua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplay = (TextView) findViewById(R.id.tvDisplay);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnCham = (Button) findViewById(R.id.btnCham);
        btnBang = (Button) findViewById(R.id.btnBang);
        btnCong = (Button) findViewById(R.id.btnCong);
        btnTru = (Button) findViewById(R.id.btnTru);
        btnNhan = (Button) findViewById(R.id.btnNhan);
        btnChia = (Button) findViewById(R.id.btnChia);
        btn0 = (Button) findViewById(R.id.btn0);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);

        btnDelete.setOnClickListener(this);
        btnCham.setOnClickListener(this);
        btnBang.setOnClickListener(this);
        btnCong.setOnClickListener(this);
        btnTru.setOnClickListener(this);
        btnNhan.setOnClickListener(this);
        btnChia.setOnClickListener(this);
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);

        //Khoi tao gia tri
        initialData();
    }

    private void initialData() {
        SoHang1 = SoHang2 = 0;
        strResult = "";
        KetQua = 0;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnDelete:
                if(strResult.isEmpty())
                {
                    return;
                }
                strResult = strResult.substring(0, strResult.length()-1);
                tvDisplay.setText(strResult);
                break;
            case R.id.btnCham:
                break;
            case R.id.btnBang:
                if(strResult.isEmpty())
                {
                    return;
                }
                SoHang2 = Float.parseFloat(strResult);
                KetQua =  cal(SoHang1, SoHang2, operator);
                SoHang1 = KetQua;
                strResult = "";
                tvDisplay.setText(String.valueOf(KetQua));
                break;
            case R.id.btnCong:
                operator = 0;
                if(strResult.isEmpty())
                {
                    return;
                }
                SoHang1 = Float.parseFloat(strResult);
                tvDisplay.setText(" + ");
                strResult = "";
                break;
            case R.id.btnTru:
                operator = 1;
                if(strResult.isEmpty())
                {
                    return;
                }
                SoHang1 = Float.parseFloat(strResult);
                tvDisplay.setText(" - ");
                strResult = "";
                break;
            case R.id.btnNhan:
                operator = 2;
                if(strResult.isEmpty())
                {
                    return;
                }
                SoHang1 = Float.parseFloat(strResult);
                tvDisplay.setText(" * ");
                strResult = "";
                break;
            case R.id.btnChia:
                operator = 3;
                if(strResult.isEmpty())
                {
                    return;
                }
                SoHang1 = Float.parseFloat(strResult);
                tvDisplay.setText(" / ");
                strResult = "";
                break;
            case R.id.btn0:
                strResult = strResult + "0";
                tvDisplay.setText(strResult);
                break;
            case R.id.btn1:
                strResult = strResult + "1";
                tvDisplay.setText(strResult);
                break;
            case R.id.btn2:
                strResult = strResult + "2";
                tvDisplay.setText(strResult);
                break;
            case R.id.btn3:
                strResult = strResult + "3";
                tvDisplay.setText(strResult);
                break;
            case R.id.btn4:
                strResult = strResult + "4";
                tvDisplay.setText(strResult);
                break;
            case R.id.btn5:
                strResult = strResult + "5";
                tvDisplay.setText(strResult);
                break;
            case R.id.btn6:
                strResult = strResult + "6";
                tvDisplay.setText(strResult);
                break;
            case R.id.btn7:
                strResult = strResult + "7";
                tvDisplay.setText(strResult);
                break;
            case R.id.btn8:
                strResult = strResult + "8";
                tvDisplay.setText(strResult);
                break;
            case R.id.btn9:
                strResult = strResult + "9";
                tvDisplay.setText(strResult);
                break;
        }
    }

    private float cal(float soHang1, float soHang2, int operator) {
        float result = 0;
        switch(operator){
            case 0:
                result = SoHang1 + SoHang2;
                break;
            case 1:
                result = SoHang1 - SoHang2;
                break;
            case 2:
                result = SoHang1 * SoHang2;
                break;
            case 3:
                result = SoHang1 / SoHang2;
                break;
        }
        return result;
    }
}
