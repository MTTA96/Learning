package com.greenacademy.demonotification;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class NotificationActivity extends AppCompatActivity {
    TextView tvThongBao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        tvThongBao = (TextView) findViewById(R.id.tvThongBao);

        Intent intent = getIntent();
        String UserName = intent.getStringExtra(MainActivity.key);
        tvThongBao.setText("Chúc mừng " + UserName + " đăng ký thành công");
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//        String username = bundle.getString(MainActivity.key);
//        tvThongBao.setText("Chúc mừng" + username + " đăng ký thành công!");
//    }
}
