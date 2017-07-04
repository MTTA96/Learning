package com.example.mtta.demowebservice;

import java.util.ArrayList;

/**
 * Created by 508-2 on 6/3/2017.
 */

public class KhuVuc {
    private ArrayList<KhuVucs> listKV;
    private int status;
    private String description;
}

public class KhuVucs{
    private int id;
    private String tenKhuVuc;
    private String linkAnh;
    private String moTa;
    private int danhGia;
    private int soLuotdanhGia;
    private int yeuThich;
    private ArrayList<Link_Anh> link_anhs;
}

public class Link_Anh{
    private String link;
    private int like;
    private int view;
}

