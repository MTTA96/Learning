package com.greenacademy.calendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Calendar cal = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
