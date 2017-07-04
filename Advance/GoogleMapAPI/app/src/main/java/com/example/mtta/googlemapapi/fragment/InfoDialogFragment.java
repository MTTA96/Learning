package com.example.mtta.googlemapapi.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mtta.googlemapapi.R;
import com.example.mtta.googlemapapi.model.MapMarker;

/**
 * Created by 508-2 on 6/17/2017.
 */

public class InfoDialogFragment extends DialogFragment {
    private MapMarker mapMarker;
    private View.OnClickListener onClickListener;
    public void setMapData(MapMarker mapMarker, View.OnClickListener onClickListener) {
        this.mapMarker = mapMarker;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_dia_diem);
        TextView tvTitle = (TextView) dialog.findViewById(R.id.tvTitle);
        TextView tvDescription = (TextView) dialog.findViewById(R.id.tvDescription);
        Button btnChiDuong = (Button) dialog.findViewById(R.id.btnChiDuong);

        tvTitle.setText(mapMarker.getTen());
        tvDescription.setText(mapMarker.getMoTa());

        btnChiDuong.setOnClickListener(onClickListener);
        return dialog;
    }
}
