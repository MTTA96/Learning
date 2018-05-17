package com.greenacademy.handlefileapp;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.greenacademy.handlefileapp.data.HandleFile;

public class RawFileActivity extends AppCompatActivity {
    Button btnBack;
    TextView tvContent;

    HandleFile handleFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raw_file);

        btnBack = (Button) findViewById(R.id.btnBackRaw);
        tvContent = (TextView) findViewById(R.id.tvContentRaw);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        handleFile = new HandleFile(this);
        String content = handleFile.readFileRaw();
        tvContent.setText(content);
    }
}
