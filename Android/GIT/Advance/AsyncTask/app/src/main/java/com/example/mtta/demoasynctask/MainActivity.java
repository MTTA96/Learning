package com.example.mtta.demoasynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tvDemGiay;
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDemGiay = (TextView) findViewById(R.id.tvDemGiay);
        btnStart = (Button) findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DemGiayAsyncTask demGiayAsyncTask = new DemGiayAsyncTask(MainActivity.this);
                //excute: chay tung cai
                //executeOnExcutor: chay song song
                demGiayAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, 61);
            }
        });
    }
}
