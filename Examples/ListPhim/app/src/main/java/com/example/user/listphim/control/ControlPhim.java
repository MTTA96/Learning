package com.example.user.listphim.control;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;

import com.example.user.listphim.R;

import java.util.ArrayList;

/**
 * Created by User on 4/20/2017.
 */

public class ControlPhim {
    ArrayList<Integer> listImage;
    ArrayList<String> listName;
    ArrayList<String> listDescription;
    ArrayList<String> listURL;

    Context context;

    private static ControlPhim instance;

    public static ControlPhim getInstance(Context context){
        if(instance == null){
            instance = new ControlPhim();
            instance.context = context;
            instance.loadDataControl();
        }
        return instance;
    }

    public void loadDataControl(){
        Resources resources = context.getResources();
        listName = new ArrayList<>();
        listDescription = new ArrayList<>();
        listURL = new ArrayList<>();
        String[] description = resources.getStringArray(R.array.description);
        String[] name = resources.getStringArray(R.array.ten_film);
        String[] url = resources.getStringArray(R.array.urls);
        for (int i = 0; i < description.length; i++) {
            listDescription.add(description[i]);
            listName.add(name[i]);
            listURL.add(url[i]);
        }

        listImage = new ArrayList<>();
        TypedArray image = resources.obtainTypedArray(R.array.film_image);
        int imageCout = url.length;
        for (int i = 0; i < imageCout; i++) {
            listImage.add(image.getResourceId(i, 0));
        }
        image.recycle();
    }

    public ArrayList<Integer> getListImage() {
        return listImage;
    }

    public ArrayList<String> getListURL() {
        return listURL;
    }

    public ArrayList<String> getListDescription() {
        return listDescription;
    }

    public ArrayList<String> getListName() {
        return listName;
    }
}
