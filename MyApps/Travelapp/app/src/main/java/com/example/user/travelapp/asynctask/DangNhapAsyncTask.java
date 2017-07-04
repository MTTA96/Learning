package com.example.user.travelapp.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by 508-2 on 6/8/2017.
 */

public class DangNhapAsyncTask extends AsyncTask<String, Void, Integer> {
    private Context context;

    public DangNhapAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected Integer doInBackground(String... s) {
        try {
            URL url = new URL("http://103.237.147.137:9045/TaiKhoan/DangNhap");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Accept", "text/json");
            conn.setRequestProperty("content-type", "application/json; charset=utf-8");
            conn.setRequestMethod("POST");
            //gửi data lên server
            OutputStream outputStream = conn.getOutputStream();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Username", s[0]);
            jsonObject.put("MatKhau", s[1]);
            jsonObject.put("KieuTk", 0);
            //Chuyển obj sang kiểu string json
            String json = jsonObject.toString();
            //Đẩy data vào outputstream
            outputStream.write(json.getBytes());
            conn.connect();
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                InputStream inputStream = conn.getInputStream();
                ByteArrayOutputStream result = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int lenght;
                while((lenght = inputStream.read(buffer)) != -1){
                    result.write(buffer, 0, lenght);
                }
                JSONObject root = new JSONObject(result.toString("UTF-8"));
                int status = root.getInt("Status");
                return status;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);

        if (integer == 1){
            Toast.makeText(context, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
        }
    }
}
