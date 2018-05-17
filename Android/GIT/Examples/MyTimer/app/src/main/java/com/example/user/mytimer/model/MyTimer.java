package com.example.user.mytimer.model;

/**
 * Created by User on 4/27/2017.
 */

public class MyTimer {
    int Hour;
    int Minute;
    int Second;

    public MyTimer() {
    }

    public MyTimer(int minute, int second, int hour) {
        Minute = minute;
        Second = second;
        Hour = hour;
    }

    public int getHour() {
        return Hour;
    }

    public int getMinute() {
        return Minute;
    }

    public int getSecond() {
        return Second;
    }

    public void increaseSecond(){
        Second++;
        if(Second == 60){
            Second = 0;
            increaseMinute();
        }
    }

    public void increaseMinute(){
        Minute++;
        if(Minute == 60){
            Minute = 0;
            inceaseHour();
        }
    }
    private void inceaseHour(){
        Hour++;
        if(Hour == 24){
            Hour = 0;
        }
    }

    public String convertString(int number){
        if(number < 10){
            return "0" + number;
        }
        else{
            return String.valueOf(number);
        }
    }

    public String toString(){
        return convertString(Hour) + " : " + convertString(Minute) + " : " + convertString(Second);
    }
}
