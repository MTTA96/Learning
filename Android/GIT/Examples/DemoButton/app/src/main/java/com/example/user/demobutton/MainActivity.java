package com.example.user.demobutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;

import com.example.user.demobutton.thread.MyAsync;

public class MainActivity extends AppCompatActivity {
    Button btnMyButton;
    MyAsync myAsync;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMyButton = (Button) findViewById(R.id.btnCatch);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        myAsync = new MyAsync(btnMyButton, width, height);
        myAsync.execute();
    }
}
