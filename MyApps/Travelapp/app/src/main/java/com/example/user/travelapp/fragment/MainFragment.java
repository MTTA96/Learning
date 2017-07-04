package com.example.user.travelapp.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.user.travelapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    View root;
    Button btnDiaDiem;
    Button btnKhachSan;
    Button btnQuanAn;
    ViewPager viewPager;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_main, container, false);
        btnDiaDiem = (Button) root.findViewById(R.id.btn_DiaDiem_MainFragment);
        btnKhachSan = (Button) root.findViewById(R.id.btn_KhachSan_MainFragment);
        btnQuanAn = (Button) root.findViewById(R.id.btn_QuanAn_MainFragment);
        viewPager = (ViewPager) root.findViewById(R.id.viewPager_MainFragment);
        //Khai báo fragment
        final DuLichFragment duLichFragment = new DuLichFragment();
        final KhachSanFragment khachSanFragment = new KhachSanFragment();
        final QuanAnFragment quanAnFragment = new QuanAnFragment();

        //set Fragment cho viewpager
        viewPager.setAdapter(new FragmentStatePagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch(position){
                    case 0:
                        return duLichFragment;
                    case 1:
                        return quanAnFragment;
                    case 2:
                        return khachSanFragment;
                    default:
                        return duLichFragment;
                }
            }

            @Override
            public int getCount() {
                return 3;
            }
        });

        //set sự kiện chuyển page
        //Chuyển màu cho button
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch(position){
                    case 0:
                        btnDiaDiem.setBackgroundResource(R.color.colorAccent);
                        btnKhachSan.setBackgroundColor(0);
                        btnQuanAn.setBackgroundColor(0);
                        break;
                    case 1:
                        btnKhachSan.setBackgroundResource(R.color.colorAccent);
                        btnDiaDiem.setBackgroundColor(0);
                        btnQuanAn.setBackgroundColor(0);
                        break;
                    case 2:
                        btnQuanAn.setBackgroundResource(R.color.colorAccent);
                        btnKhachSan.setBackgroundColor(0);
                        btnDiaDiem.setBackgroundColor(0);
                        break;
//                    default:
//                        btnDiaDiem.setBackgroundResource(R.color.colorAccent);
//                        btnKhachSan.setBackgroundColor(0);
//                        btnQuanAn.setBackgroundColor(0);
//                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return root;
    }

}
