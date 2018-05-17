package com.example.user.finalbasicproject.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.finalbasicproject.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DSignUpFragment extends Fragment {
    //Khai báo để tạo fragment 1 lần
    private static DSignUpFragment instance = null;

    public static DSignUpFragment getInstance(){
        if(instance == null){
            instance =  new DSignUpFragment();
        }
        return instance;
    }
    public DSignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dsign_up, container, false);
    }

}
