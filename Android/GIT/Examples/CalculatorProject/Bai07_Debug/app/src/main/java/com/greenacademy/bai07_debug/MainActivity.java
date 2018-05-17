package com.greenacademy.bai07_debug;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etInput1;
    TextView etInput2;
    Button btnAdd;
    Button btnSub;
    Button btnMul;
    Button btnDiv;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInput1 = (EditText) findViewById(R.id.etInput1);
        etInput2 = (TextView) findViewById(R.id.etInput2);
        tvResult = (TextView) findViewById(R.id.tvResult);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnSub = (Button) findViewById(R.id.btnSub);
        btnDiv = (Button) findViewById(R.id.btnDiv);
        btnMul = (Button) findViewById(R.id.btnMul);

        btnAdd.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
        btnMul.setOnClickListener(this);
        btnSub.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String strInput1 = etInput1.getText().toString();
        String strInput2 = etInput2.getText().toString();
        int Number1 = Integer.parseInt(strInput1);
        int Number2 = Integer.parseInt(strInput2);
        int Result = 0;

        switch (v.getId()){
            case R.id.btnAdd:
                Result = Number1 + Number2;
                break;
            case R.id.btnSub:
                Result = Number1 - Number2;
                break;
            case R.id.btnMul:
                Result = Number1 * Number2;
                break;
            case R.id.btnDiv:
                Result = Number1 / Number2;
                break;
        }

        tvResult.setText(Integer.toString(Result));
    }
}
