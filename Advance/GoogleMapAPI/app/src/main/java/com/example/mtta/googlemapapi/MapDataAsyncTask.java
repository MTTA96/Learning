package com.example.mtta.googlemapapi;

import android.os.AsyncTask;

import com.example.mtta.googlemapapi.model.MapData;
import com.example.mtta.googlemapapi.model.MapMarker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by 508-2 on 6/15/2017.
 */

public class MapDataAsyncTask extends AsyncTask<Double, Void, MapData> {
    private MapDataCallBack mapDataCallBack;

    public MapDataAsyncTask(MapDataCallBack mapDataCallBack) {
        this.mapDataCallBack = mapDataCallBack;
    }

    @Override
    protected MapData doInBackground(Double... params) {
        try {
            URL url = new URL("http://103.237.147.137:9045/DiaDiem/MapMarker?lat=" + String.valueOf(params[0]) + "&lng=" + String.valueOf(params[1]));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.addRequestProperty("Accept", "text/json");
            conn.setRequestMethod("GET");
            conn.connect();
            if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                InputStream inputStream = conn.getInputStream();
                ByteArrayOutputStream result = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int length;
                while((length = inputStream.read(buffer)) != -1){
                    result.write(buffer, 0, length);
                }

                JSONObject root = new JSONObject(result.toString("UTF-8"));
                MapData mapData = new MapData();
                mapData.setStatus(root.getInt("Status"));
                mapData.setDescription(root.getString("Description"));

                JSONArray listMapMarker = root.getJSONArray("MapMarkerTranfers");
                for (int i = 0; i < listMapMarker.length(); i++) {
                    JSONObject jsonMapMarker = listMapMarker.getJSONObject(i);
                    MapMarker mapMarker = new MapMarker();
                    mapMarker.setId(jsonMapMarker.getInt("Id"));
                    mapMarker.setTen(jsonMapMarker.getString("Ten"));
                    mapMarker.setMoTa(jsonMapMarker.getString("MoTa"));
                    mapMarker.setDanhGia(jsonMapMarker.getInt("DanhGia"));
                    mapMarker.setSoLuotXem(jsonMapMarker.getInt("SoLuotXem"));
                    mapMarker.setYeuThich(jsonMapMarker.getInt("YeuThich"));
                    mapMarker.setCheckIn(jsonMapMarker.getInt("CheckIn"));
                    mapMarker.setLoaiMarKer(jsonMapMarker.getString("LoaiMarker"));
                    mapMarker.setLinkAnh(jsonMapMarker.getString("LinkAnh"));
                    mapMarker.setLat(jsonMapMarker.getDouble("Lat"));
                    mapMarker.setLng(jsonMapMarker.getDouble("Lng"));
                    mapData.getMapMarkerTranfers().add(mapMarker);
                }
                return mapData;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(MapData mapData) {
        super.onPostExecute(mapData);

        mapDataCallBack.DiaDiemCallBack(mapData);
    }
}
