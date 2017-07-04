package com.greenacademy.demonotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText etUserName;
    EditText etPassword;
    Button btnDangKy;

    NotificationManager notificationManager;
    Notification myNotify;

    public static final String key = "user_name";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPassword = (EditText) findViewById(R.id.etPassword);
        etUserName = (EditText) findViewById(R.id.etUserName);
        btnDangKy = (Button) findViewById(R.id.btnDangKy);

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayNotification();
            }
        });
    }

    private void displayNotification() {
        Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putString("user_name", etUserName.getText().toString());
        intent.putExtra(key, etUserName.getText().toString());
        //hoãn việc chuyển màn hình (có thể dùng trong tin nhắn)
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder mBuidler = new NotificationCompat.Builder(this);
        mBuidler.setContentTitle("Xin chào");
        mBuidler.setContentText("Chúc mừng bạn đã đăng ký thành công!");
        mBuidler.setSmallIcon(R.mipmap.ic_launcher);
        mBuidler.setAutoCancel(true);
        mBuidler.setContentIntent(pendingIntent);

        myNotify = mBuidler.build();

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, myNotify);
    }
}
