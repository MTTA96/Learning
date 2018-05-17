package com.example.user.demobutton.thread;

import android.os.AsyncTask;
import android.widget.AbsoluteLayout;
import android.widget.Button;

import com.example.user.demobutton.model.MyPosition;

/**
 * Created by User on 4/27/2017.
 */

public class MyAsync extends AsyncTask<Void, Void, Void> {
    Button btnCatch;
    MyPosition myPosition;
    int Width;

    public MyAsync(Button btnA, int width, int height){
        super();
        btnCatch = btnA;
        Width = width;
        myPosition = new MyPosition();
    }
    @Override
    protected Void doInBackground(Void... params) {
        while(true) {
            try {
                Thread.sleep(50);
                myPosition.increaseX();
                myPosition.resetX(Width);
                //Thiet lap giao dien
                publishProgress();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        AbsoluteLayout.LayoutParams absParams = (AbsoluteLayout.LayoutParams) btnCatch.getLayoutParams();
        absParams.x = myPosition.getX();
        absParams.y = myPosition.getY();
        btnCatch.setLayoutParams(absParams);
    }
}
