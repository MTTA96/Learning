package com.greenacademy.redboxentertainment.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.greenacademy.redboxentertainment.R;
import com.greenacademy.redboxentertainment.model.CD;

import java.util.ArrayList;

/**
 * Created by ADMIN on 4/29/2017.
 */

public class MyAdapterCDN extends ArrayAdapter {
    // ???????????????
    Activity con;
    int layoutItem;
    ArrayList<CD> arrayList;
    // khai bao bien
    CD cd;

    // ?????????????
    public MyAdapterCDN (Activity context, int resource, ArrayList<CD> objects){
        super(context, resource, objects);
        con = context;
        layoutItem = resource;
        arrayList = objects;
    }

    // ??????????????
    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // dinh nghia thanh phan giao dien cho tung item cua listview
        // ???????????????
        LayoutInflater inflater = con.getLayoutInflater();
        // ????????????????
        convertView = inflater.inflate(layoutItem,null);
        // ???????????????????
        final CD cd = arrayList.get(position);

        // ????????????
        TextView tvtencdn = (TextView) convertView.findViewById(R.id.tvTenCDN);
        TextView tvcasi = (TextView) convertView.findViewById(R.id.tvCaSi);
        TextView tvgiacdn = (TextView) convertView.findViewById(R.id.tvGiaCDN);
        TextView tvcodecdn = (TextView) convertView.findViewById(R.id.tvCodeCDN);
        TextView tvsoluongcdn = (TextView) convertView.findViewById(R.id.tvSoLuongCDN);

        // thiet lap xuat mang hinh
        tvtencdn.setText(cd.getTenCD());
        tvcasi.setText(cd.getTacgia());
        tvcodecdn.setText(cd.getCode());
        tvgiacdn.setText(cd.getGia());
        tvsoluongcdn.setText(cd.getSoluong());

        // xuat ra mang hinh
        return convertView;
    }
}
