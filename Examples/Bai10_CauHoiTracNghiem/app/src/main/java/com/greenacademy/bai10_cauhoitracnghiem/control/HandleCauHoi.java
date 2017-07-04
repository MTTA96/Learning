package com.greenacademy.bai10_cauhoitracnghiem.control;

import com.greenacademy.bai10_cauhoitracnghiem.model.CauHoi;

import java.util.ArrayList;

/**
 * Created by GIT on 2/25/2017.
 */

// điều khiển câu hỏi dùng array list để quản lý.
public class HandleCauHoi {
    ArrayList<CauHoi> listCauHoi;
    int number;
    public HandleCauHoi(ArrayList<CauHoi> listCauHoi) {
        this.listCauHoi = listCauHoi;
        int i = 0;
    }

    public CauHoi get(int number){
        number = 0;
        return listCauHoi.get(number);
    }

    public CauHoi nextQuestion(){
        number++;
        if(number == listCauHoi.size()){
            number = 0;
        }
        return listCauHoi.get(number);
    }

    public  CauHoi previousQuestion(){
        number--;
        if(number < 0){
            number = listCauHoi.size()-1;
        }
        return listCauHoi.get(number);
    }
}
