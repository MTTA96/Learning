package com.example.user.travelapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by User on 5/16/2017.
 */

public class KhuVucHolder extends RecyclerView.ViewHolder {
    public TextView tvName;
    public TextView tvDescription;
    public TextView tvView;
    public TextView tvLike;
    public TextView tvRating;
    public ImageView imgDiaDiem;

    public KhuVucHolder(View itemView) {
        super(itemView);
        tvName = (TextView) itemView.findViewById(R.id.tv_TenDiaDia_ItemList);
        tvDescription = (TextView) itemView.findViewById(R.id.tv_Description_ItemList);
        tvLike = (TextView) itemView.findViewById(R.id.tvLike_ItemList);
        tvView = (TextView) itemView.findViewById(R.id.tv_LuotView_ItemList);
        tvRating = (TextView) itemView.findViewById(R.id.tvRating_ItemList);
        imgDiaDiem = (ImageView) itemView.findViewById(R.id.iv_DiaDiem_ItemList);
    }
}
