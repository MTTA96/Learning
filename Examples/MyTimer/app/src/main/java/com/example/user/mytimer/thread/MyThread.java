package com.example.user.mytimer.thread;

import com.example.user.mytimer.model.MyTimer;

/**
 * Created by User on 4/27/2017.
 */

public class MyThread extends Thread {
    MyTimer myTimer;

    public MyThread(MyTimer myTimer){
        this.myTimer = myTimer;
    }

    @Override
    public void run(){
        super.run();
        while(true){
            myTimer.increaseSecond();
            try{
                sleep(1000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
