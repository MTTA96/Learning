package com.greenacademy.exitprogram.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.greenacademy.exitprogram.R;

/**
 * Created by GIT on 3/18/2017.
 */

//DialogFragment: support.v...
public class MyExitDialogFragment extends DialogFragment {
    private static MyExitDialogFragment instance= null;
    String titleDialog;
    String MessageDialog;

    public static MyExitDialogFragment newInstance(String title, String message) {
        //Kiểm tra instance đã được khởi tạo chưa
        if(instance == null){
            instance = new MyExitDialogFragment();
        }

        //Cập nhật lại title và message
        instance.titleDialog = title;
        instance.MessageDialog = message;

        return instance;
    }

    //chọn Generate... -> override
    //Tạo dialog
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle(titleDialog)
                .setMessage(MessageDialog)
                .setPositiveButton(R.string.thoat, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().finish();
                    }
                })
                .setNegativeButton(R.string.huy, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                }).create();
    }
}
