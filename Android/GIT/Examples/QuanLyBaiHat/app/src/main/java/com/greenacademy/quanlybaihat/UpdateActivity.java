
package com.greenacademy.quanlybaihat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.greenacademy.quanlybaihat.model.BaiHat;
import com.greenacademy.quanlybaihat.util.SupportAdvanceList;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etTenCaSi;
    EditText etTenBaiHat;
    EditText etThoiGian;
    Button btnReset;
    Button btnUpdate;

    BaiHat bh;
    String strTenBaiHat;
    String strTenCaSi;
    String strThoiGian;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        etTenBaiHat = (EditText) findViewById(R.id.etTenBaiHatUpdate);
        etTenCaSi = (EditText) findViewById(R.id.etTenCaSiUpdate);
        etThoiGian = (EditText) findViewById(R.id.etThoiGianUpdate);
        btnReset = (Button) findViewById(R.id.btnResetUpdate);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);

        btnUpdate.setOnClickListener(this);
        btnReset.setOnClickListener(this);

        Intent intent = getIntent();
        bh = (BaiHat) intent.getSerializableExtra(SupportAdvanceList.KEY_BH);
        position = intent.getIntExtra(SupportAdvanceList.KEY_Position, 0); //Giá trị mặc định = 0

        strTenBaiHat = bh.getTenBaiHat();
        strTenCaSi = bh.getTenCaSi();
        strThoiGian = bh.getThoiGian();

        etTenBaiHat.setHint(bh.getTenBaiHat());
        etTenCaSi.setHint(bh.getTenCaSi());
        etThoiGian.setHint(bh.getThoiGian());
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnResetUpdate:
                etTenBaiHat.setHint(strTenBaiHat);
                etTenCaSi.setHint(strTenCaSi);
                etThoiGian.setHint(strThoiGian);
                break;
            case R.id.btnUpdate:
                //1. Kiểm tra dữ liệu tên bài hát
                String strTenBaiHat = etTenBaiHat.getText().toString();
                String strTenCaSi = etTenCaSi.getText().toString();
                String strThoiGian = etThoiGian.getText().toString();
                if(strTenBaiHat.isEmpty()){
                }
                else {
                    bh.setTenBaiHat(strTenBaiHat);
                }
                //Kiểm tra tên ca sĩ
                if(strTenCaSi.isEmpty()) {
                }
                else{
                    bh.setTenCaSi(strTenCaSi);
                }
                //Kiểm tra thời gian
                if(strThoiGian.isEmpty()) {
                }
                else{
                    bh.setThoiGian(strThoiGian);
                }

                //Truyền dữ liệu qua main
                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                intent.putExtra(SupportAdvanceList.KEY_BH, bh);
                intent.putExtra(SupportAdvanceList.KEY_Position, position);
                startActivity(intent);
                break;
        }
    }
}
