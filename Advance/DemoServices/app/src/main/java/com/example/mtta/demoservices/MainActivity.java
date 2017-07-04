package com.example.mtta.demoservices;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DemSoCallBack {
    Button btnStart, btnStop, btnDemLe, btnDemChan, btnDemSo;
    EditText etInput;
    TextView tvLe, tvChan, tvBindService;

    MyBindService.DataBinder dataBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        btnDemChan = (Button) findViewById(R.id.btnDemChan);
        btnDemLe = (Button) findViewById(R.id.btnDemLe);
        btnDemSo = (Button) findViewById(R.id.btnDemSo);
        etInput = (EditText) findViewById(R.id.etInput);
        tvChan = (TextView) findViewById(R.id.tv_Chan);
        tvLe = (TextView) findViewById(R.id.tv_LE);
        tvBindService = (TextView) findViewById(R.id.tvBindService);

        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        btnDemChan.setOnClickListener(this);
        btnDemLe.setOnClickListener(this);
        btnDemSo.setOnClickListener(this);

        //đăng ký nhận data từ broadcast
        registerReceiver(broadcastReceiver, new IntentFilter(MyIntentService.ACTION_DEMLE));
        registerReceiver(broadcastReceiver, new IntentFilter(MyIntentService.ACTION_DEMCHAN));

        //bind service
        Intent service = new Intent(MainActivity.this, MyBindService.class);
        bindService(service, serviceConnection, BIND_AUTO_CREATE);
    }

    public ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //service = binder từ onBind bên service
            dataBinder = (MyBindService.DataBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnStart:
                //Khởi chạy services
                Intent intent = new Intent(MainActivity.this, MyService.class);
                Bundle bundle = new Bundle();
                bundle.putInt("so", Integer.parseInt(etInput.getText().toString()));
                intent.putExtra("data", bundle);
                startService(intent);
                break;
            //Intent service
            case R.id.btnStop:
                Intent intent1 = new Intent(MainActivity.this, MyService.class);
                stopService(intent1);
                break;
            case R.id.btnDemChan:
                MyIntentService.startActionDemChan(this);
                break;
            case R.id.btnDemLe:
                MyIntentService.startActionDemLe(this);
                break;
            //Bind service
            case R.id.btnDemSo:
                dataBinder.SetInterface(MainActivity.this);
                dataBinder.DemSoBinder(100);
                break;
        }
    }

    //nhan data
    public BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //Kiểm tra action intent được gửi tới
            if(intent.getAction().equals(MyIntentService.ACTION_DEMLE)){
                tvLe.setText(intent.getStringExtra("Le"));
            }
            if (intent.getAction().equals(MyIntentService.ACTION_DEMCHAN)){
                tvChan.setText(intent.getStringExtra("Chan"));
            }
        }
    };

    @Override
    public void DemSo(final int so) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvBindService.setText("" + String.valueOf(so));
            }
        });
    }
}
