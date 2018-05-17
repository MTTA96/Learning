package com.greenacademy.dialogmultichoice.fragment;

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

public class ChonDialogFragment extends DialogFragment {
    private static ChonDialogFragment instance = null;
    String title;
    TextView tvDisplay;
    String myDrink = "";
    boolean[] myChoice = {false,false,false,false};
    String[] choice = {"XML", "Java", "PHP", "Python"};

    public static ChonDialogFragment newInstance(String title, TextView textView) {
        if(instance == null){
            instance = new ChonDialogFragment();
        }

        instance.title = title;
        instance.tvDisplay = textView;
        return instance;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMultiChoiceItems(choice, myChoice, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if(isChecked == true){
                            myChoice[which] = true;
                        }
                        else{
                            myChoice[which] = false;
                        }
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = 0; i < myChoice.length; i++) {
                            if(myChoice[i] == true){
                                myDrink = myDrink + choice[i] + "," ;
                            }
                        }
                        tvDisplay.setText(myDrink);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                }).create();
    }
}
