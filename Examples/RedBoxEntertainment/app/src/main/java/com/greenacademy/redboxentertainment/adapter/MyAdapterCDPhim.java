package com.greenacademy.redboxentertainment.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.greenacademy.redboxentertainment.R;
import com.greenacademy.redboxentertainment.model.CD;

import java.util.ArrayList;

/**
 * Created by ADMIN on 4/29/2017.
 */

public class MyAdapterCDPhim extends ArrayAdapter {
    // ???????????????
    Activity con;
    int layoutItem;
    ArrayList<CD> arrayList;
    // khai bao bien
    CD cd;

    // ?????????????
    public MyAdapterCDPhim (Activity context, int resource, ArrayList<CD> objects){
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
        TextView tvtencdp = (TextView) convertView.findViewById(R.id.tvTenCDP);
        TextView tvdaodien = (TextView) convertView.findViewById(R.id.tvDaoDien);
        TextView tvgiacdp = (TextView) convertView.findViewById(R.id.tvGiaCDP);
        TextView tvcodecdp = (TextView) convertView.findViewById(R.id.tvCodeCDP);
        TextView tvsoluongcdp = (TextView) convertView.findViewById(R.id.tvSoLuongCDP);

        // thiet lap xuat mang hinh
        tvtencdp.setText(cd.getTenCD());
        tvdaodien.setText(cd.getTacgia());
        tvcodecdp.setText(cd.getCode());
        tvgiacdp.setText(cd.getGia());
        tvsoluongcdp.setText(cd.getSoluong());

        // xuat ra mang hinh
        return convertView;
    }
}
