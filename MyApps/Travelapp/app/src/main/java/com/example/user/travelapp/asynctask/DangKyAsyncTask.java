package com.example.user.travelapp.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.user.travelapp.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by 508-2 on 6/8/2017.
 */

public class DangKyAsyncTask extends AsyncTask<String, Void, Integer> {
    Context context;

    public DangKyAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected Integer doInBackground(String... s) {
        try {
            URL url = new URL("http://103.237.147.137:9045/TaiKhoan/DangKy");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.addRequestProperty("Accept", "text/json");
            //config gửi data lên server
            conn.addRequestProperty("content-type", "application/json");

            conn.setRequestMethod("POST");
            //gửi data lên server
            OutputStream outputStream = conn.getOutputStream();
            //Tạo obj
            JSONObject object = new JSONObject();
            object.put("Username", s[0]);
            object.put("MatKhau", s[1]);
            object.put("TenHienThi", s[2]);
            //Chuyển obj sang kiểu string
            String json = object.toString();
            //Đẩy data vào outputstream
            outputStream.write(json.getBytes());
            conn.connect();

            if(conn.getResponseCode() == 200 || conn.getResponseCode() == 201){
                Log.d("thanhj cong","thanh cong");
                InputStream inputStream = conn.getInputStream();
                ByteArrayOutputStream result = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int lenght;
                while ((lenght = inputStream.read(buffer)) != -1){
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
        if (integer == 1)
            Toast.makeText(context, "Thanh cong", Toast.LENGTH_LONG).show();
    }
}
