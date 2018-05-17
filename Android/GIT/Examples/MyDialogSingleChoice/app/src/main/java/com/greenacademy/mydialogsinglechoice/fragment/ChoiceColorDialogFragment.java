package com.greenacademy.mydialogsinglechoice.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by GIT on 3/18/2017.
 */

public class ChoiceColorDialogFragment extends DialogFragment {
    private static ChoiceColorDialogFragment instance = null;
    String titleDialog;
    TextView tvDisplay;
    CharSequence[] choies = {"RED", "BLUE", "YELLOW"};

    public static ChoiceColorDialogFragment newInstance(String title, TextView textView) {
        //Kiểm tra instance đã được khởi tạo chưa
        if(instance == null){
            instance = new ChoiceColorDialogFragment();
        }

        //Cập nhật lại
        instance.titleDialog = title;
        instance.tvDisplay = textView;
        return instance;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle(titleDialog)
                .setItems(choies, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch(which){
                            case 0:
                                tvDisplay.setBackgroundColor(Color.RED);
                                break;
                            case 1:
                                tvDisplay.setBackgroundColor(Color.BLUE);
                                break;
                            case 2:
                                tvDisplay.setBackgroundColor(Color.YELLOW);
                                break;
                        }
                    }
                }).create();
    }
}
