package com.greenacademy.dialogmultichoice;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.greenacademy.dialogmultichoice.fragment.ChonDialogFragment;

public class MainActivity extends AppCompatActivity {
    Button btnChon;
    TextView tvChon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnChon = (Button) findViewById(R.id.btnChonMain);
        tvChon = (TextView) findViewById(R.id.tvChonMain);

        btnChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlartDialog();
            }
        });
    }

    private void showAlartDialog() {
        FragmentManager fm = getSupportFragmentManager();
        ChonDialogFragment dialog = ChonDialogFragment.newInstance("Chọn ngôn ngữ:", tvChon);
        dialog.show(fm, "frag_chon_ngon_ngu");
    }
}
