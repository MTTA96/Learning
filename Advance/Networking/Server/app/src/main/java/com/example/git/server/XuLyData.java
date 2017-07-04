package com.example.git.server;

import com.example.git.server.model.User;

import java.util.List;

/**
 * Created by GIT on 5/27/2017.
 */

public class XuLyData {
    String data;

    public XuLyData(String data) {
        this.data = data;
    }

    public User PhanTichData(List<User> listUser) {
        String[] tachData = data.split("@");
        if (tachData[0].equals("1")) {
            for (int i = 0; i < listUser.size(); i++) {
                if (tachData[1].equals(listUser.get(i).getIP()))
                    return listUser.get(i);
            }
        }
        return null;
    }
}
