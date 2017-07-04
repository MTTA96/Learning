package com.example.user.travelapp.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import com.example.user.travelapp.adapter.KhuVucAdapter;
import com.example.user.travelapp.model.DiaDiem;
import com.example.user.travelapp.model.KhuVuc;

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
 * Created by 508-2 on 6/6/2017.
 */

public class KetNoiDataKhuVuc extends AsyncTask<Void, Void, KhuVuc> {
    private RecyclerView listView;
    private Context context;
    public KetNoiDataKhuVuc(Context context, RecyclerView listView) {
        this.context = context;
        this.listView = listView;
    }

    @Override
    protected KhuVuc doInBackground(Void... params) {
        try {
            URL url = new URL("http://103.237.147.137:9045/KhuVuc/AllKhuVuc");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //tùy chỉnh kiểu trả về từ server
            conn.addRequestProperty("Accept","text/json");
            //Chọn phương thức gửi lên server
            conn.setRequestMethod("GET");
            conn.connect();

            //Kiểm tra kết nối thành công
            if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                InputStream inputStream = conn.getInputStream();
                ByteArrayOutputStream result = new ByteArrayOutputStream();
                //Khởi tạo biến đọc từng khối data
                byte[] buffer = new byte[1024];
                //Dữ liệu nhận được
                int length;
                //Đọc dữ liệu
                while((length = inputStream.read(buffer)) != -1){
                    result.write(buffer, 0, length);
                }

                //parse data
                JSONObject root = new JSONObject(result.toString("UTF-8"));
                KhuVuc khuVuc = new KhuVuc();

                JSONArray listKhuVuc = root.getJSONArray("KhuVucs");
                khuVuc.setStatus(root.getInt("Status"));
                khuVuc.setDecription(root.getString("Description"));
                for (int i = 0; i< listKhuVuc.length(); i++) {
                    JSONObject khuVucJSON = listKhuVuc.getJSONObject(i);
                    DiaDiem diaDiem = new DiaDiem();
                    diaDiem.setID(khuVucJSON.getInt("Id"));
                    diaDiem.setTenKhuVuc(khuVucJSON.getString("TenKhuVuc"));
                    diaDiem.setMoTa(khuVucJSON.getString("MoTa"));
                    diaDiem.setLinkAnh(khuVucJSON.getString("LinkAnh"));
                    diaDiem.setDanhGia(khuVucJSON.getInt("DanhGia"));
                    diaDiem.setSoLuotXem(khuVucJSON.getInt("SoLuotXem"));
                    diaDiem.setYeuThich(khuVucJSON.getInt("YeuThich"));
                    khuVuc.getKhuVucs().add(diaDiem);
                }
                return khuVuc;
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
    protected void onPostExecute(KhuVuc khuVuc) {
        super.onPostExecute(khuVuc);

        listView.setAdapter(new KhuVucAdapter(context, khuVuc.getKhuVucs()));
    }
}
