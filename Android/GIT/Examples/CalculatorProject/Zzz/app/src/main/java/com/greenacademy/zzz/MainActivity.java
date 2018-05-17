package com.greenacademy.zzz;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    Button btnStart;
    EditText etInput1;
    EditText etInput2;


    public void display(String content){
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = (Button)findViewById(R.id.btnStart);
        etInput1 = (EditText) findViewById(R.id.etInput1);
        etInput2 = (EditText) findViewById(R.id.etInput2);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strInput1 = etInput1.getText().toString();
                String strInput2 = etInput2.getText().toString();

                Bundle bundle = new Bundle();
                bundle.putString(Constant.Key1, strInput1);
                bundle.putString(Constant.Key2, strInput2);

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
        display("onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        display("onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        display("onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        display("onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        display("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        display("onDestroy");
    }

}
