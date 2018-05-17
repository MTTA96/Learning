package com.example.git.server.model;

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

public class User extends Thread{
    private Socket socket;
    private String IP;

    Handler handler;

    public User(Socket socket, String IP, Handler handler) {
        this.socket = socket;
        this.IP = IP;
        this.handler = handler;
    }

    @Override
    public void run() {
        super.run();
        try {
            //Tạo inputstream
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while(true){
                //Chuỗi nhận được từ client
                String sentence = fromClient.readLine();
                if(sentence == null)
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
    public String getIP() {
        return IP;
    }

    public Socket getSocket() {
        return socket;
    }
}
