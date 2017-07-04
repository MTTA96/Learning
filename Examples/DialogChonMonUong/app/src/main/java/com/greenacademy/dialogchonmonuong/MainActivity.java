package com.greenacademy.dialogchonmonuong;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.greenacademy.dialogchonmonuong.fragment.ChonMonDialogFragment;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    Button btnChonMon;
    TextView tvChon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvChon = (TextView) findViewById(R.id.tvChonMon);
        btnChonMon = (Button) findViewById(R.id.btnChon);

        btnChonMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });
    }

    private void showAlertDialog() {
        FragmentManager fm = getSupportFragmentManager();
        ChonMonDialogFragment dialog = ChonMonDialogFragment.newInstance("Chọn món:", tvChon);
        dialog.show(fm, "frag_chon_mon");
    }
}
