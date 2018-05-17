package com.greenacademy.exitprogram;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.greenacademy.exitprogram.fragment.MyExitDialogFragment;

public class MainActivity extends AppCompatActivity {
    Button btnThoat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnThoat = (Button) findViewById(R.id.btnExit);

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });
    }

    private void showAlertDialog() {
        FragmentManager fm = getSupportFragmentManager();
        MyExitDialogFragment dialog = MyExitDialogFragment.newInstance("Thoát Chương Trình", "Bạn có muốn thoát chương trình?");
        dialog.show(fm, "fragment_alert_exit");
    }
}
