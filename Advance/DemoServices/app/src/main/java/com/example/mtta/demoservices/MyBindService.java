package com.example.mtta.demoservices;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;

public class MyBindService extends Service {
    public MyBindService() {
    }

    DemSoCallBack demSoCallBack;

    private ArrayList<String> linkAnh = new ArrayList<>();

    private DataBinder dataBinder = new DataBinder();

    public class DataBinder extends Binder{
        public void SetInterface(DemSoCallBack demSo){
            demSoCallBack = demSo;
        }
        public void AddLinkAnh (String s){
            linkAnh.add(s);
        }
        public void DemSoBinder(int gioiHan){
            DemSo(gioiHan);
        }
    }

    //Sử dụng interface || broadcast || handler để truyền data qua activity
    public void DemSo(final int gioiHan){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while(i<gioiHan){
                    try {
                        Thread.sleep(1000);
                        Log.d("demso", "Dem so: " + i++);
                        demSoCallBack.DemSo(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }
    @Override
    public IBinder onBind(Intent intent) {
        return dataBinder;
    }
}
