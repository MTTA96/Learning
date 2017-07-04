package com.example.git.client;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by GIT on 5/25/2017.
 */

public class ThreadSocket extends Thread {
    private Socket socket;
    private Handler handler;

    public ThreadSocket(Socket socket, Handler handler){
        this.socket = socket;
        this.handler = handler;
    }

    @Override
    public void run() {
        super.run();
        try {
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while(true){
                //Chuỗi nhận được từ Server (phải có \n để đọc từng dòng)
                String sentence = fromServer.readLine(); //Đọc từng dòng
                if(sentence==null)
                    continue;
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("sentence", sentence);
                message.arg1 = 0;
                message.setData(bundle);
                handler.sendMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String data){
        try {
            //Tạo output gửi data lên server
            DataOutputStream sendToClient = new DataOutputStream(socket.getOutputStream());
            sendToClient.writeBytes(data + '\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
