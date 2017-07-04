package com.example.mtta.googlemapapi.asynstask;

import android.os.AsyncTask;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by 508-2 on 6/20/2017.
 */

public class ChiDuongAsyncTask extends AsyncTask<String, Void, Void> {
    @Override
    protected Void doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Accept", "json");
            conn.setRequestMethod("GET");
            conn.connect();
            if (conn.getResponseCode() == 200 || conn.getResponseCode() == 201){

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
