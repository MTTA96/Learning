package com.example.user.travelapp.control;

import android.content.Context;
import android.content.res.TypedArray;

import com.example.user.travelapp.R;
import com.example.user.travelapp.asynctask.KetNoiDataKhuVuc;
import com.example.user.travelapp.model.DiaDiem;

import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by User on 5/18/2017.
 */

public class ControlDiaDiem {
    ArrayList<DiaDiem> listDiaDiem;
    DiaDiem diaDiem;

    Context context;

    private static ControlDiaDiem instance;

    public static ControlDiaDiem getInstance(Context context){
        if(instance == null){
            instance = new ControlDiaDiem();
            instance.context = context;
            instance.loadData();
        }
        return instance;
    }

    private void loadData() {
        String[] listTen = context.getResources().getStringArray(R.array.arr_ten_dia_diem);
        String[] listDescription = context.getResources().getStringArray(R.array.arr_description);
        TypedArray image = context.getResources().obtainTypedArray(R.array.arr_images);
        listDiaDiem = new ArrayList<>();
        for (int i = 0; i < listTen.length; i++) {
            diaDiem = new DiaDiem();
            diaDiem.setTenKhuVuc(listTen[i]);
            diaDiem.setMoTa(listDescription[i]);
            //diaDiem.setLinkAnh(image.getResourceId(i, 0));
            listDiaDiem.add(diaDiem);
        }
        image.recycle();

    }

    public ArrayList<DiaDiem> getListDiaDiem() {
        return listDiaDiem;
    }
}
