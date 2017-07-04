package com.greenacademy.dialogtudinhnghia.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.greenacademy.dialogtudinhnghia.R;

/**
 * Created by GIT on 3/21/2017.
 */

public class DialogCustomFragment extends DialogFragment {
    private  static DialogCustomFragment instance = null;

    //Contructor khởi tạo
    public static DialogCustomFragment getInstance() {
        if(instance == null){
            instance = new DialogCustomFragment();
        }
        return instance;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Khởi taọ Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //Tạo biến lấy layout
        LayoutInflater inflater = getActivity().getLayoutInflater();

        //Gán tiêu đề cho dialog
        builder.setTitle("Login");

        //Gán layout cho Dialog
        builder.setView(inflater.inflate(R.layout.user_defined_dialog, null));

        //Xử lý button của Dialog
        builder.setPositiveButton("Login", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "Login Success", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //dismiss();
                DialogCustomFragment.this.getDialog().cancel();
            }
        });

        //Gắn icon cho Dialog
        builder.setIcon(R.mipmap.ic_launcher);

        return builder.create(); //.create() để tạo Dialog
    }
}
