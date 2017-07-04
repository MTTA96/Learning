package com.greenacademy.dialogchonmonuong.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.widget.TextView;

/**
 * Created by GIT on 3/18/2017.
 */

public class ChonMonDialogFragment extends DialogFragment {
    //Khởi tạo giá trị
    private static ChonMonDialogFragment instance = null;
    String myDrink;
    String title;
    TextView tvDisplay;
    CharSequence[] choice = {"Orange", "Coffe", "Matcha"};

    public static ChonMonDialogFragment newInstance(String title, TextView textView){
        if(instance == null){
            instance = new ChonMonDialogFragment();
        }

        instance.title= title;
        instance.tvDisplay = textView;
        return instance;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setSingleChoiceItems(choice, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                myDrink = (String) choice[0];
                                break;
                            case 1:
                                myDrink = (String) choice[1];
                                break;
                            case 2:
                                myDrink = (String) choice[2];
                                break;
                        }
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvDisplay.setText(myDrink);
                    }
                })
                .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                }).create();
    }
}
