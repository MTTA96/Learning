package com.greenacademy.bai04_giaiphuongtrinh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //1.
    EditText etSoA;
    EditText etSoB;
    EditText etSoC;
    Button btnNhapLai;
    Button btnGiai;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSoA = (EditText) findViewById(R.id.etNumberA);
        etSoB = (EditText) findViewById(R.id.etNumberB);
        etSoC = (EditText) findViewById(R.id.etNumberC);
        btnNhapLai = (Button) findViewById(R.id.btnNhapLai);
        btnGiai = (Button) findViewById(R.id.btnGiai);
        tvResult = (TextView) findViewById(R.id.tvKetQua);

        btnGiai.setOnClickListener(this);
        btnNhapLai.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //1. Kiem tra nhap
        switch (v.getId())
        {
            case R.id.btnGiai:
                String strNumberA = etSoA.getText().toString();
                String strNumberB = etSoB.getText().toString();
                String strNumberC = etSoC.getText().toString();
                if(strNumberA.isEmpty() || strNumberB.isEmpty() || strNumberC.isEmpty())
                    return;
                int A = Integer.parseInt(strNumberA);
                int B = Integer.parseInt(strNumberB);
                int C = Integer.parseInt(strNumberC);
                String result = Giai(A,B,C);
                tvResult.setText(result);
                break;
            case R.id.btnNhapLai:
                etSoA.setText("");
                etSoB.setText("");
                etSoC.setText("");
                break;
        }
    }
    String Giai(int A, int B, int C){
        String result = "";
        if(A == 0)
            result = GiaiPTBac1(B,C);
        else
            result = GiaiPTBac2(A,B,C);
        return result;
    }
    String GiaiPTBac1(int B, int C)
    {
        String result = "";
        if(B == 0)
            if(C == 0)
                result = "PT có vô số nghiệm.";
            else
                result = "PT vô nghiệm";
        else
            if(C != 0)
                result = "PT co nghiem X = " + String.valueOf(-(C/B));
            else
                result = "PT có nghiệm X = 0";
        return result;
    }
    String GiaiPTBac2(int A, int B, int C)
    {
        String result  = "";
        double delta = (double) B*B - 4*A*C;
        if(delta < 0)
            result = "PT vô nghiệm.";
        if(delta == 0)
            result = "PT có một nghiệm X = " + String.valueOf(-B/(2*A));
        if(delta > 0)
            result = "PT có hai nghiệm: X1 = " + String.valueOf((-B + Math.sqrt(delta))/(2*A)) + ", X2 = " + String.valueOf(((-B - Math.sqrt(delta))/(2*A)));
        return result;
    }
}
