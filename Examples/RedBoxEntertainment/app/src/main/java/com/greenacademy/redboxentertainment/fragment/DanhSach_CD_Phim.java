package com.greenacademy.redboxentertainment.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.greenacademy.redboxentertainment.R;
import com.greenacademy.redboxentertainment.adapter.MyAdapterCDN;
import com.greenacademy.redboxentertainment.adapter.MyAdapterCDPhim;
import com.greenacademy.redboxentertainment.sqlite.MySQLite;

/**
 * A simple {@link Fragment} subclass.
 */
public class DanhSach_CD_Phim extends Fragment {

    ListView lvCDPhim;
    MyAdapterCDPhim myAdapterCDPhim;
    MySQLite mySQLite;


    public DanhSach_CD_Phim() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_danh_sach__cd__phim, container, false);
        // anh xa
        lvCDPhim = (ListView) view.findViewById(R.id.lvCDPhim);

        // khoi tao gia tri ban dau
        mySQLite = new MySQLite(getActivity());
        myAdapterCDPhim = new MyAdapterCDPhim(getActivity(), R.layout.layout_item_cd_dsp, mySQLite.getCD(0));
        // xuat du lieu vao listview
        lvCDPhim.setAdapter(myAdapterCDPhim);

        return view;
    }

}
