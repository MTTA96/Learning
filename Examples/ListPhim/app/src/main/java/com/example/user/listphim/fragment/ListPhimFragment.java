package com.example.user.listphim.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.listphim.R;
import com.example.user.listphim.adapter.OnFilmSelected;
import com.example.user.listphim.adapter.PhimAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListPhimFragment extends Fragment {
    private static ListPhimFragment instance;
    private PhimAdapter adapter;

    public ListPhimFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //instanceof kiểm tra context có phải dạng của onfilmselected hay không
        if(context instanceof OnFilmSelected){
            adapter = new PhimAdapter(context);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_phim, container, false);
        RecyclerView recycleView = (RecyclerView) view.findViewById(R.id.recycler_view);
        //2 cột
        recycleView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recycleView.setAdapter(adapter);
        return inflater.inflate(R.layout.fragment_list_phim, container, false);
    }

    public static Fragment newInstance() {
        if(instance == null){
            instance = new ListPhimFragment();
        }
        return instance;
    }
}
