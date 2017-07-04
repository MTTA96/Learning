package com.example.user.travelapp.model;

import java.util.ArrayList;

/**
 * Created by 508-2 on 6/6/2017.
 */

public class KhuVuc {
    private ArrayList<DiaDiem> khuVucs=new ArrayList<>();
    private int status;
    private String decription;

    public ArrayList<DiaDiem> getKhuVucs() {
        return khuVucs;
    }

    public int getStatus() {
        return status;
    }

    public String getDecription() {
        return decription;
    }

    public void setKhuVucs(ArrayList<DiaDiem> khuVucs) {
        this.khuVucs = khuVucs;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }
}
