package com.greenacademy.redboxentertainment.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.greenacademy.redboxentertainment.fragment.DanhSach_CD_Nhac;
import com.greenacademy.redboxentertainment.fragment.DanhSach_CD_Phim;
import com.greenacademy.redboxentertainment.fragment.Them_CD_Nhac;
import com.greenacademy.redboxentertainment.fragment.Them_CD_Phim;

/**
 * Created by ADMIN on 4/28/2017.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    String[] arrTitle = {"Thêm CD Nhạc", "Thêm CD Phim", "Danh Sách CD Nhạc", "Danh Sách CD Phim"};

    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new Them_CD_Nhac();
                break;
            case 1:
                fragment = new Them_CD_Phim();
                break;
            case 2:
                fragment = new DanhSach_CD_Nhac();
                break;
            case 3:
                fragment = new DanhSach_CD_Phim();
                break;
        }
        return fragment;

    }

    @Override
    public int getCount() {
        return arrTitle.length;
    }

    public String getTitle(int position) {
        return arrTitle[position];
    }
}