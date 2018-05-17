package com.greenacademy.demolistview.util;

import java.util.ArrayList;

/**
 * Created by GIT on 3/14/2017.
 */

public class SupportList {
    String[] arrPhone;

    public SupportList(String[] arrPhone) {
        this.arrPhone = arrPhone;
    }

    public ArrayList<String> searchMobile(String phone){
        ArrayList<String> result = new ArrayList<>();
        // TH1: người dùng không nhập gì
        if(phone.isEmpty()){
            for (int i = 0; i < arrPhone.length ; i++) {
                result.add(arrPhone[i]);
            }
            return result;
        }
        //TH2: người dùng có nhập
        else{
            phone = ".*" + phone + ".*";
            phone.toLowerCase();
            String temp;
            for (int i = 0; i <arrPhone.length ; i++) {
                temp = arrPhone[i].toLowerCase();
                if(temp.matches(phone)){
                    result.add(arrPhone[i]);
                }
            }
        }
        return result;
    }
}
