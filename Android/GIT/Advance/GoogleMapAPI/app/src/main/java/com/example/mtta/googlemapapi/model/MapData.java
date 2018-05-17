package com.example.mtta.googlemapapi.model;

import java.util.ArrayList;

/**
 * Created by 508-2 on 6/15/2017.
 */

public class MapData {
    private ArrayList<MapMarker> mapMarkerTranfers = new ArrayList<>();
    private int status;
    private String description;

    public ArrayList<MapMarker> getMapMarkerTranfers() {
        return mapMarkerTranfers;
    }

    public void setMapMarkerTranfers(ArrayList<MapMarker> mapMarkerTranfers) {
        this.mapMarkerTranfers = mapMarkerTranfers;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
