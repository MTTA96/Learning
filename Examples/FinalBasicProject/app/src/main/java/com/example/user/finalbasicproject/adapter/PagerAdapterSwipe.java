package com.example.user.finalbasicproject.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


/**
 * Created by User on 4/25/2017.
 */

public class PagerAdapterSwipe extends FragmentPagerAdapter {

    String[] arrChucNang = {};
    public PagerAdapterSwipe(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return arrTitle.length;
    }

    public String getTitle(int position){
        return arrTitle[position];
    }
}