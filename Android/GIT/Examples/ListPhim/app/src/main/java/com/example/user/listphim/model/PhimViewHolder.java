package com.example.user.listphim.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.listphim.R;

/**
 * Created by User on 4/20/2017.
 */

public class PhimViewHolder extends RecyclerView.ViewHolder {
    //Sử dụng recycleview định nghĩa trực tiếp view chứa nội dung
    private ImageView mImageView;
    private TextView mTextView;

    public PhimViewHolder(View itemView) {
        super(itemView);

        mImageView = (ImageView) itemView.findViewById(R.id.ivPhim_ListPhim);
        mTextView = (TextView) itemView.findViewById(R.id.tvTenPhim__ListPhim);
    }

    public void setData(int imageResId, String name){
        mImageView.setImageResource(imageResId);
        mTextView.setText(name);
    }
}
