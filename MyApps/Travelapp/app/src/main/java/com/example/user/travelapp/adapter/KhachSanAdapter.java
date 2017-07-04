package com.example.user.travelapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.travelapp.KhuVucHolder;
import com.example.user.travelapp.R;
import com.example.user.travelapp.control.ControlDiaDiem;

/**
 * Created by User on 5/16/2017.
 */

public class KhachSanAdapter extends RecyclerView.Adapter<KhuVucHolder> {
    Context context;

    public KhachSanAdapter(Context context) {
        this.context = context;
    }

    @Override
    public KhuVucHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Ánh xạ xml thành view -> đẩy vào viewholder -> ánh xạ và xử lý item trong viewholder
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View root = layoutInflater.inflate(R.layout.list_item_dulich, parent, false);
        return new KhuVucHolder(root);
    }

    @Override
    public void onBindViewHolder(KhuVucHolder holder, int position) {
        holder.tvName.setText(ControlDiaDiem.getInstance(context).getListDiaDiem().get(position).getTenKhuVuc());
        holder.tvDescription.setText(ControlDiaDiem.getInstance(context).getListDiaDiem().get(position).getMoTa());
        //holder.imgDiaDiem.setImageResource(ControlDiaDiem.getInstance(context).getListDiaDiem().get(position).getImgDiaDiem());
    }

    @Override
    public int getItemCount() {
        return ControlDiaDiem.getInstance(context).getListDiaDiem().size();
    }
}
