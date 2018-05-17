package com.example.git.client;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnSend;
    Button btnStart;
    TextView tvSentenceFromServer;
    EditText etNoiDung;

    Socket socket;
    ThreadSocket threadSocket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvSentenceFromServer = (TextView) findViewById(R.id.tvSentenceFromServer);
        etNoiDung = (EditText) findViewById(R.id.etNoiDung);
        btnSend = (Button) findViewById(R.id.btnSend);
        btnStart = (Button) findViewById(R.id.btnStart);

        btnStart.setOnClickListener(this);
        btnSend.setOnClickListener(this);
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.arg1){
                case 0:
                    Bundle bundle = msg.getData();
                    tvSentenceFromServer.setText(bundle.getString("sentence"));
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnStart:
                //Kết nối với server
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            socket = new Socket("192.168.28.104", 9999);
                            //Lấy data server gửi về
                            threadSocket = new ThreadSocket(socket, handler);
                            threadSocket.start();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
                break;
            case R.id.btnSend:
                //Định nghĩa data khi gửi:
                // VD: 1@id@nội_dung       VD login: 3@user_name@password
                // 1 -> chat 1 - 1
                // id -> ip người nhận
                threadSocket.send(etNoiDung.getText().toString());

                break;
        }
    }
}
