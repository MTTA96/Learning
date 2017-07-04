package com.greenacademy.dialogtudinhnghia;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.greenacademy.dialogtudinhnghia.fragment.DialogCustomFragment;

public class MainActivity extends AppCompatActivity {
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }
    public void showDialog(){
        FragmentManager fm = getSupportFragmentManager();
        DialogCustomFragment dialog = DialogCustomFragment.getInstance();
        dialog.show(fm, "frag_custom_dialog");
    }
}
