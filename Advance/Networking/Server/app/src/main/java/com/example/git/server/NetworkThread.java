package com.example.git.server;

import android.os.Handler;

import com.example.git.server.model.User;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by GIT on 5/25/2017.
 */

public class NetworkThread extends Thread {
    Handler handler;

    private List<User> listOnline = new LinkedList<>();
    public NetworkThread(Handler handler){

    }

    @Override
    public void run() {
        super.run();

        try {
            //Server dùng ServerSocket
            //set port là 9999
            ServerSocket serverSocket = new ServerSocket(9999);
            //chạy loop chờ request
            while (true) {
                //Client dùng socket
                final Socket client = serverSocket.accept();
                User user = new User(client, client.getInetAddress().toString(),handler);
                listOnline.add(user);
                user.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
