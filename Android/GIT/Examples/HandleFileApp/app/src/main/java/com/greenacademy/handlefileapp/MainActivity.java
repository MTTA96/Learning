package com.greenacademy.handlefileapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.greenacademy.handlefileapp.data.HandleFile;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etTenFile;
    EditText etDoanVanBan;
    TextView tvThongBaoLoi;
    Button btnGhi;
    Button btnDoc;
    Button btnXoa;
    Button btnGo;

    HandleFile handleFile;

    private static final String EMPTY_FILE = "Bạn chưa nhập tên File";
    private static  final String EMPTY_CONTENT = "Bạn chưa nhập nội dung file";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTenFile = (EditText) findViewById(R.id.etTenFile);
        etDoanVanBan = (EditText) findViewById(R.id.etDoanVanBan);
        tvThongBaoLoi = (TextView) findViewById(R.id.tvThongBaoLoi);
        btnGhi = (Button) findViewById(R.id.btnGhi);
        btnDoc = (Button) findViewById(R.id.btnDoc);
        btnXoa = (Button) findViewById(R.id.btnXoa);
        btnGo = (Button) findViewById(R.id.btnRaw);

        btnGhi.setOnClickListener(this);
        btnDoc.setOnClickListener(this);
        btnXoa.setOnClickListener(this);
        btnGo.setOnClickListener(this);

        handleFile = new HandleFile(this);
    }

    @Override
    public void onClick(View v) {
        String strFileName = etTenFile.getText().toString();
        if(strFileName.isEmpty()){
            tvThongBaoLoi.setText(EMPTY_FILE);
            return;
        }
        switch(v.getId()){
            case R.id.btnDoc:
                String content = handleFile.readFileApp(strFileName);
                etDoanVanBan.setText(content);
                break;
            case R.id.btnGhi:
                String contentFile = etDoanVanBan.getText().toString();
                if(contentFile.isEmpty()){
                    tvThongBaoLoi.setText(EMPTY_CONTENT);
                    return;
                }
                handleFile.writeFileApp(strFileName, contentFile);
                //handleFile.writeFileExternal(strFileName, contentFile);
                break;
            case R.id.btnXoa:
                handleFile.deleteFileApp(strFileName);
                break;
            case R.id.btnRaw:
                Intent intent = new Intent(MainActivity.this, RawFileActivity.class);
                startActivity(intent);
                break;
        }
    }
}
