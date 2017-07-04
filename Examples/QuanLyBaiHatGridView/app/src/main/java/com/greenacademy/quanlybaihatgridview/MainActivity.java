package com.greenacademy.quanlybaihatgridview;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import com.greenacademy.quanlybaihatgridview.model.BaiHat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView tvIndex;
    TextView tvTenBaiHat;
    GridView listBaiHat;

    ArrayList<BaiHat> dsBaiHat;
    int[] arrHinhCaSi;
    String[] arrTenCasi;
    String[] arrTenBaiHat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvIndex = (TextView) findViewById(R.id.tvIndexMain);
        tvTenBaiHat = (TextView) findViewById(R.id.tvTenBaiHatMain);
        listBaiHat = (GridView) findViewById(R.id.listBaiHatMain);

        loadData();
    }

    private void loadData() {
        arrTenBaiHat = getResources().getStringArray(R.array.list_ten_bai_hat);
        arrTenCasi = getResources().getStringArray(R.array.list_ten_ca_si);
        TypedArray listHinh = getResources().obtainTypedArray(R.array.list_anh_ca_si);
        arrHinhCaSi =
    }
}
