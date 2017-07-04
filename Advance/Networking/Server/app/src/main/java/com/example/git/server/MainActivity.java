package com.example.git.server;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.git.server.model.User;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    TextView tvSentence;
    Button btnStart;

    private List<User> listOnline = new LinkedList<>();
    Socket client;
    XuLyData xuLyData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvSentence = (TextView) findViewById(R.id.tvSentenceFromClient);
        btnStart = (Button) findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //Server dùng ServerSocket
                            //set port là 9999
                            ServerSocket serverSocket = new ServerSocket(9999);
                            //chạy loop chờ request
                            while (true) {
                                //Client dùng socket
                                client = serverSocket.accept();

                                //Test kết nối
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tvSentence.setText(client.getInetAddress().toString());
                                    }
                                });
                                User user = new User(client, client.getInetAddress().toString().substring(client.getInetAddress().toString().length()),handler);
                                listOnline.add(user);
                                user.start();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
            }
        });

    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.arg1){
                case 0:
                    //Hiển thị data từ client lên text
                    Bundle bundle = msg.getData();
                    tvSentence.setText(bundle.getString("sentence"));
                    //Gửi dữ liệu về client
                    xuLyData = new XuLyData(bundle.getString("sentence"));
                    User user=xuLyData.PhanTichData(listOnline);
                    if(user!=null){
                        user.send("Server: " + bundle.getString("sentence").split("@")[2]);
                    }
//                    try {
//                        DataOutputStream sendToClient = new DataOutputStream(client.getOutputStream());
//                        sendToClient.writeBytes("Server: " + bundle.getString("sentence")+'\n');
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                   break;
            }
        }
    };
}
