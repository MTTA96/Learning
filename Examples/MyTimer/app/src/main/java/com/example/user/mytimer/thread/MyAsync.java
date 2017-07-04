package com.example.user.mytimer.thread;

import android.graphics.Color;
import android.os.AsyncTask;
import android.widget.TextView;

import com.example.user.mytimer.model.MyTimer;

/**
 * Created by User on 4/27/2017.
 */

public class MyAsync extends AsyncTask<Void, String, Void> {
    TextView tvTimer;
    MyTimer myTimer;
    MyThread myThread;

    public MyAsync(TextView Timer){
        super();
        tvTimer = Timer;
        myTimer = new MyTimer();
        myThread = new MyThread(myTimer);
    }
    @Override
    protected Void doInBackground(Void... params) {
        myThread.start();
        publishProgress(myTimer.toString());
        while (true){
            try {
                Thread.sleep(1000);
                myTimer.increaseSecond();
                publishProgress(myTimer.toString());
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        tvTimer.setBackgroundColor(Color.MAGENTA);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        tvTimer.setBackgroundColor(Color.WHITE);
    }

    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        tvTimer.setText(values[0]);
    }
}
