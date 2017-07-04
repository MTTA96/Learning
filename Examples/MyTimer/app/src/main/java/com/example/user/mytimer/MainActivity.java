package com.example.user.mytimer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.user.mytimer.thread.MyAsync;

public class MainActivity extends AppCompatActivity {
    TextView tvTimer;
    MyAsync myAsync;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTimer = (TextView) findViewById(R.id.tvtimer);
        myAsync = new MyAsync(tvTimer);
        myAsync.execute();
    }
}
