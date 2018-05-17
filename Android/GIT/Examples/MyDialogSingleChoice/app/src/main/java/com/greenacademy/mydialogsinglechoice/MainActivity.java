package com.greenacademy.mydialogsinglechoice;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.greenacademy.mydialogsinglechoice.fragment.ChoiceColorDialogFragment;

public class MainActivity extends AppCompatActivity {
    Button btnChange;
    TextView tvDoiMau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnChange = (Button) findViewById(R.id.btnTest);
        tvDoiMau = (TextView) findViewById(R.id.tvTestColor);

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });
    }

    private void showAlertDialog() {
        FragmentManager fm = getSupportFragmentManager();
        ChoiceColorDialogFragment dialog = ChoiceColorDialogFragment.newInstance("Chọn màu", tvDoiMau);
        dialog.show(fm, "fragment_alert_chonmau");
    }
}
