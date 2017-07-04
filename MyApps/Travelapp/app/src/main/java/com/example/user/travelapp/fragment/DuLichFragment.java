package com.example.user.travelapp.fragment;


import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.user.travelapp.R;
import com.example.user.travelapp.adapter.KhuVucAdapter;
import com.example.user.travelapp.asynctask.KetNoiDataKhuVuc;

/**
 * A simple {@link Fragment} subclass.
 */
public class DuLichFragment extends Fragment {
    RecyclerView listView;
    ImageButton search;

    public DuLichFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_du_lich, container, false);
        listView = (RecyclerView) root.findViewById(R.id.KhuVucList_DuLichFragment);
        search = (ImageButton) root.findViewById(R.id.ib_Search_DuLichFragment);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Đã click thành công!", Toast.LENGTH_SHORT).show();
            }
        });

        listView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        KetNoiDataKhuVuc ketNoiDataKhuVuc = new KetNoiDataKhuVuc(getContext(),listView);
        //listView.setAdapter(new KhuVucAdapter(getActivity()));
        ketNoiDataKhuVuc.execute();
        return root;
    }

}
