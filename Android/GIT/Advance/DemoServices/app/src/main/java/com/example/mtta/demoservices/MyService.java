package com.example.mtta.demoservices;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }
    int soGiay = 0;
    int gioiHan;
    @Override
    public void onCreate() {
        super.onCreate();
//        Thread thread = new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                while(soGiay < gioiHan) {
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    Log.d("Services", "services: " + String.valueOf(soGiay++));
//                }
//            }
//        });
//        thread.start();
        for (int i = 0; i < gioiHan; i++) {
            try {
                Thread.sleep(1000);
                Log.d("Services", "services: " + String.valueOf(soGiay++));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    //Cấu hình cách services chạy (tự động khởi tạo khi bị kill)
    @Override
    public int onStartCommand(Intent intent,int flags, int startId) {
        Bundle bundle = intent.getBundleExtra("data");
        gioiHan = bundle.getInt("so");
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
