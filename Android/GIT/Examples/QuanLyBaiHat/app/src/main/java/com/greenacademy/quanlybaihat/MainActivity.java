package com.greenacademy.quanlybaihat;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.greenacademy.quanlybaihat.adapter.MyAdapterListBaiHat;
import com.greenacademy.quanlybaihat.model.BaiHat;
import com.greenacademy.quanlybaihat.util.SupportAdvanceList;

import java.util.ArrayList;

//Bước 1: tạo model.
//2: Tạo layout
//3: tạo Arraylist
//4: tạo Adapter
//5: đưa lên ListView

public class MainActivity extends AppCompatActivity {
    TextView tvTenBaiHat;
    TextView tvIndex;
    ListView listBaiHat;

    MyAdapterListBaiHat adapter;
    ArrayList<BaiHat> arrayListBaiHat;
    int[] arrAnhCaSi;
    String[] arrTenBaiHat;
    String[] arrTenCaSi;
    String[] arrThoiGian;
    BaiHat baihat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTenBaiHat = (TextView) findViewById(R.id.tvTenBaiHatMain);
        tvIndex = (TextView) findViewById(R.id.tvIndex);
        listBaiHat = (ListView) findViewById(R.id.listBaiHat);

        //Xử lý sự kiện
        listBaiHat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                baihat = arrayListBaiHat.get(position);
                tvTenBaiHat.setText(baihat.getTenBaiHat());
                tvIndex.setText(String.valueOf(position));
                Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                intent.putExtra(SupportAdvanceList.KEY_BH, baihat);
                intent.putExtra(SupportAdvanceList.KEY_Position,position);
                MainActivity.this.startActivity(intent);
            }
        });
        //tải dữ liệu vào listbaihat
        loadData();
        //hiển thị lên ListView
        displayListView();

        //Lấy data -> intent = getIntent
        //Lấy BaiHat từ màn hình Update
        //BaiHat != null -> Update arrayListBaiHat
        Intent intent = getIntent();
        BaiHat bh = (BaiHat) intent.getSerializableExtra(SupportAdvanceList.KEY_BH);
        int position = intent.getIntExtra(SupportAdvanceList.KEY_Position, 0);
        if(bh != null) {
            arrayListBaiHat.remove(position);
            arrayListBaiHat.add(position, bh);
            adapter.notifyDataSetChanged();
            listBaiHat.invalidateViews();
            listBaiHat.refreshDrawableState();
        }
    }


    private void displayListView() {
        adapter = new MyAdapterListBaiHat(this, R.layout.layout_item_bai_hat, arrayListBaiHat);
        listBaiHat.setAdapter(adapter);
    }

    private void loadData() {
        //data dump (dữ liệu giả)
        arrTenBaiHat = getResources().getStringArray(R.array.list_ten_bai_hat);
        arrTenCaSi = getResources().getStringArray(R.array.list_ten_ca_si);
        arrThoiGian = getResources().getStringArray(R.array.list_thoi_gian);
        TypedArray listAnh = getResources().obtainTypedArray(R.array.list_anh_ca_si);
        arrAnhCaSi = new int[arrThoiGian.length];
        for (int i = 0; i < arrThoiGian.length ; i++) {
            arrAnhCaSi[i] = listAnh.getResourceId(i,-1); //vị trí, giá trị mặc định (nếu k có gì)
        }

        //danh sách bài hát
        arrayListBaiHat = new ArrayList<BaiHat>();
        for (int i = 0; i < arrThoiGian.length; i++) {
            baihat = new BaiHat();
            baihat.setTenBaiHat(arrTenBaiHat[i]);
            baihat.setTenCaSi(arrTenCaSi[i]);
            baihat.setThoiGian(arrThoiGian[i]);
            baihat.setHinhCaSi(arrAnhCaSi[i]);
            arrayListBaiHat.add(baihat);
        }
    }
}
