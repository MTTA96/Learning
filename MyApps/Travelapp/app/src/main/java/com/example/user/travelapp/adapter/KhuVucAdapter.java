package com.example.user.travelapp.adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.travelapp.KhuVucHolder;
import com.example.user.travelapp.R;
import com.example.user.travelapp.asynctask.DownloadImageTask;
import com.example.user.travelapp.control.ControlDiaDiem;
import com.example.user.travelapp.model.DiaDiem;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by User on 5/16/2017.
 */

public class KhuVucAdapter extends RecyclerView.Adapter<KhuVucHolder> {
    private Context context;
    private ArrayList<DiaDiem> listDiaDiem;

    public KhuVucAdapter(Context context, ArrayList<DiaDiem> listDiaDiem) {
        this.context = context;
        this.listDiaDiem = listDiaDiem;
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
//        holder.tvName.setText(ControlDiaDiem.getInstance(context).getListDiaDiem().get(position).getTenKhuVuc());
//        holder.tvDescription.setText(ControlDiaDiem.getInstance(context).getListDiaDiem().get(position).getMoTa());
        //holder.imgDiaDiem.setImageResource(ControlDiaDiem.getInstance(context).getListDiaDiem().get(position).getImgDiaDiem());
        holder.tvName.setText(listDiaDiem.get(position).getTenKhuVuc());
        holder.tvDescription.setText(listDiaDiem.get(position).getMoTa());
        holder.tvView.setText(String.valueOf(listDiaDiem.get(position).getSoLuotXem()));
        holder.tvRating.setText(String.valueOf(listDiaDiem.get(position).getDanhGia()));
        holder.tvLike.setText(String.valueOf(listDiaDiem.get(position).getYeuThich()));
        //set Image
        DownloadImageTask downloadImageTask = new DownloadImageTask(holder.imgDiaDiem);
        downloadImageTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, listDiaDiem.get(position).getLinkAnh());
    }

    @Override
    public int getItemCount() {
        return listDiaDiem.size();
    }
}
