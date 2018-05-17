package com.example.user.demobutton.model;

/**
 * Created by User on 4/27/2017.
 */

public class MyPosition {
    int x;
    int y;

    public MyPosition() {
    }

    public MyPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void increaseX(){
        x++;
    }

    public void increaseY(){
        y++;
    }

    public void resetX(int width) {
        if(x == width){
            x = 0;
        }
    }
}
