package com.greenacademy.redboxentertainment.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.greenacademy.redboxentertainment.R;
import com.greenacademy.redboxentertainment.adapter.MyAdapterCDN;
import com.greenacademy.redboxentertainment.sqlite.MySQLite;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DanhSach_CD_Nhac extends Fragment {

    ListView lvCDNhac;
    MyAdapterCDN myAdapterCDN;
    MySQLite mySQLite;

    public DanhSach_CD_Nhac() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_danh_sach__cd__nhac, container, false);
        // anh xa
        lvCDNhac = (ListView) view.findViewById(R.id.lvCDNhac);

        // khoi tao gia tri ban dau
        mySQLite = new MySQLite(getActivity());
        myAdapterCDN = new MyAdapterCDN(getActivity(), R.layout.layout_item_dsn, mySQLite.getCD(1));
        // xuat du lieu vao listview
        lvCDNhac.setAdapter(myAdapterCDN);
        return view;
    }

}
