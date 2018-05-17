package com.example.user.democontact.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.democontact.R;
import com.example.user.democontact.model.ItemRecyclerView;

import java.util.ArrayList;

/**
 * Created by User on 5/4/2017.
 */

public class ContactAdapter extends RecyclerView.Adapter<ItemRecyclerView> {
    private ArrayList<String> titles;

    public ContactAdapter(ArrayList<String> titles) {
        this.titles = titles;
    }

    @Override
    public ItemRecyclerView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview, parent, false);
        return new ItemRecyclerView(view);
    }

    @Override
    public void onBindViewHolder(ItemRecyclerView holder, int position) {
        holder.setTvTitle(titles.get(position));
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    //Thêm danh bạ
    public void addItem(String title){
        titles.add(title);
        notifyItemInserted(titles.size());
    }

    //Xóa danh bạ
    public void removeItem(int position){
        titles.remove(position);
        notifyItemRemoved(position);
        notifyItemChanged(position, titles.size());
    }
}
