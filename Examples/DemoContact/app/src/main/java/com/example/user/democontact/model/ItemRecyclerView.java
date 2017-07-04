package com.example.user.democontact.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.user.democontact.R;

/**
 * Created by User on 5/4/2017.
 */

public class ItemRecyclerView extends RecyclerView.ViewHolder {
    TextView tvTitle;

    public ItemRecyclerView(View itemView) {
        super(itemView);

        tvTitle = (TextView) itemView.findViewById(R.id.tv_contact);
    }

    public void setTvTitle(String data){
        tvTitle.setText(data);
    }
}
