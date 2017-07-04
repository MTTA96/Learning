package com.example.user.travelapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by User on 5/16/2017.
 */

public class KhachSanHolder extends RecyclerView.ViewHolder {
    public TextView tvName;
    public TextView tvDescription;
    public ImageView imgDiaDiem;


    public KhachSanHolder(View itemView) {
        super(itemView);
        tvName = (TextView) itemView.findViewById(R.id.tv_TenDiaDia_ItemList);
        tvDescription = (TextView) itemView.findViewById(R.id.tv_Description_ItemList);
        imgDiaDiem = (ImageView) itemView.findViewById(R.id.iv_DiaDiem_ItemList);
    }
}
