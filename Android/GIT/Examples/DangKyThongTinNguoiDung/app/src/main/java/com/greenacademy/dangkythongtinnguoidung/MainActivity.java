package com.greenacademy.dangkythongtinnguoidung;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnBasicInfo;
    Button btnOptionalInfo;
    TableLayout tlBasicInfo;
    TableLayout tlOptionalInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBasicInfo = (Button) findViewById(R.id.btnBasicInfo);
        btnOptionalInfo = (Button)findViewById(R.id.btnOptionalInfo);
        tlBasicInfo = (TableLayout) findViewById(R.id.tlBasicInfo);
        tlOptionalInfo = (TableLayout) findViewById(R.id.tlOptionalInfo);

        btnBasicInfo.setOnClickListener(this);
        btnOptionalInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnBasicInfo:
                tlBasicInfo.setVisibility(View.VISIBLE);
                tlOptionalInfo.setVisibility(View.GONE);
                break;
            case R.id.btnOptionalInfo:
                tlOptionalInfo.setVisibility(View.VISIBLE);
                tlBasicInfo.setVisibility(View.GONE);
                break;
        }
    }
}
