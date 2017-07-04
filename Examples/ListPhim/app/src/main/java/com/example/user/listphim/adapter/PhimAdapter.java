package com.example.user.listphim.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.listphim.control.ControlPhim;
import com.example.user.listphim.model.PhimViewHolder;


/**
 * Created by User on 4/20/2017.
 */

public class PhimAdapter extends RecyclerView.Adapter<PhimViewHolder> {
    private LayoutInflater mLayoutInflater;
    Context context;
    private OnFilmSelected mListener;

    //Khỏi tạo layoutinflater
    public PhimAdapter(Context context){
        mLayoutInflater = LayoutInflater.from(context);
        this.context = context;

        //instanceof kiểm tra context có phải dạng của onfilmselected hay không
        if(context instanceof OnFilmSelected){
            mListener = (OnFilmSelected) context;
        }
        else{
            throw new ClassCastException(context.toString() + "must implement OnFilmSelected");
        }
    }
    @Override
    public PhimViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PhimViewHolder(mLayoutInflater.inflate(R.layout.item_layout_list_phim, parent, false));
    }

    @Override
    public void onBindViewHolder(PhimViewHolder holder, int position) {
        final int imageResID = ControlPhim.getInstance(context).getListImage().get(position);
        final String name = ControlPhim.getInstance(context).getListName().get(position);
        final String description = ControlPhim.getInstance(context).getListDescription().get(position);
        final String url = ControlPhim.getInstance(context).getListURL().get(position);
        //Truyền dữ liệu vào để hiển thị lên
        holder.setData(imageResID, name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onFilmSelected(imageResID, name, description, url);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ControlPhim.getInstance(context).getListURL().size();
    }
}
