package com.greenacademy.quanlybaihat.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.greenacademy.quanlybaihat.MainActivity;
import com.greenacademy.quanlybaihat.R;
import com.greenacademy.quanlybaihat.UpdateActivity;
import com.greenacademy.quanlybaihat.model.BaiHat;
import com.greenacademy.quanlybaihat.util.SupportAdvanceList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GIT on 3/11/2017.
 */

public class MyAdapterListBaiHat extends ArrayAdapter {
    Activity con;
    int layoutItem;
    ArrayList<BaiHat> arrayList;

    int index;
    //Constructor khởi tạo
    //(layout_item_bai_hat, các item trong layout_item_bai_hat, danh sách bài hát)
    public MyAdapterListBaiHat(Activity context, int resource, ArrayList<BaiHat> objects) {
        super(context, resource, objects);

        con = context;
        layoutItem = resource;
        arrayList = objects;
    }

    @NonNull
    @Override
    //load dữ liệu cho từng item
    // position: vị trí bài hát, convertView: giao dien item(layout_item)
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = con.getLayoutInflater();
        convertView = inflater.inflate(layoutItem, null); //Ánh xạ
        final BaiHat baiHat = arrayList.get(position);

        //Ánh xạ
        TextView tvTenCaSi = (TextView) convertView.findViewById(R.id.tvTenCaSi);
        TextView tvTenBaiHat = (TextView) convertView.findViewById(R.id.tvTenBaiHat);
        TextView tvThoiGian = (TextView) convertView.findViewById(R.id.tvThoiGian);
        ImageView ivCaSi = (ImageView) convertView.findViewById(R.id.ivHinhCaSi);
        ImageView ibNext = (ImageView) convertView.findViewById(R.id.ibNext);
        //Xử lý imageview (note: không sử dụng button)
        ImageView ivNext = (ImageView) convertView.findViewById(R.id.ibNext);
        ivNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //Gán giá trị hiện thị
        tvTenBaiHat.setText(baiHat.getTenBaiHat());
        tvTenCaSi.setText(baiHat.getTenCaSi());
        tvThoiGian.setText(baiHat.getThoiGian());
        ivCaSi.setImageResource(baiHat.getHinhCaSi());

        return convertView;
    }
}
