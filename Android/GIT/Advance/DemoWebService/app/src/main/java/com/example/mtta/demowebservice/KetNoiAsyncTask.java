package com.example.mtta.demowebservice;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.example.mtta.demowebservice.R.id.tvInfo;

/**
 * Created by 508-2 on 6/1/2017.
 */

public class KetNoiAsyncTask extends AsyncTask<Void, Void, String> {
    Activity activity;

    public KetNoiAsyncTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected String doInBackground(Void... params) {
        String responseString = null;
        try {
            URL url = new URL("http://103.237.147.137:9045/KhuVuc/AllKhuVuc");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //config server trả về data kiểu xml
            conn.addRequestProperty("Accept", "text/json");
            //config phương thức truyền lên server
            conn.setRequestMethod("GET");
            conn.connect();
            //Check code = 200 thì thành công
            int testrescode = conn.getResponseCode();
            if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                //Tra ve ByteArray
                InputStream inputStream = conn.getInputStream();
                ByteArrayOutputStream result = new ByteArrayOutputStream();
                //đọc từng khối data, tối da 1024 byte
                byte[] buffer = new byte[1024];
                //Dữ liệu nhận được
                int length;
                //Đọc dữ liệu r trà vào buffer
                while((length = inputStream.read(buffer)) != -1){
                    //Ghép các khối byte(buffer)
                    //buffer mảng, 0 vị trí bắt đầu cần lấy trong buffer, length: số lượng
                    result.write(buffer, 0, length);
                }
                //UTF_8 cho phép có dấu
                return result.toString("UTF-8");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        //{ -> object, [ -> list
        try {
            JSONObject root = new JSONObject(s);
            KhuVuc khuVuc = new KhuVuc();

            JSONArray listKV = root.getJSONArray("KhuVucs");
            khuVuc.getStatus = root.getInt("Status");
            khuVuc.getDecription = root.getString("Decription");
            for (int i = 0; i < listKV.length(); i++) {
                JSONObject khuVucJson = listKV.getJSONObject(i);
                KhuVucs khuVucs = new KhuVucs();
                khuVucs.getID =khuVucJson.getInt("Id");
                //....
                khuVuc.getlistKV.add(khuVuc);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }



        TextView tvInfo = (TextView) activity.findViewById(R.id.tvInfo);
        tvInfo.setText(s);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
