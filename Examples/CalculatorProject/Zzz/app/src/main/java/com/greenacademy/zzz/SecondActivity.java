package com.greenacademy.zzz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    Button btnBack;
    TextView tvTransfer1;
    TextView tvTransfer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        btnBack = (Button) findViewById(R.id.btnBack);

        tvTransfer1 = (TextView) findViewById(R.id.tvTransfer1);
        tvTransfer2 = (TextView) findViewById(R.id.tvTransfer2);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String strOutput1 = bundle.getString(Constant.Key1);
        String strOutput2 = bundle.getString(Constant.Key2);
        tvTransfer1.setText(strOutput1);
        tvTransfer2.setText(strOutput2);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
