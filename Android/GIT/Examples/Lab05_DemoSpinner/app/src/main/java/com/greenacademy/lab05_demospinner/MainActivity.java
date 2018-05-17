package com.greenacademy.lab05_demospinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnDone;
    Spinner spnWork;
    Spinner spnTime;

    public void display(String content){
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDone = (Button) findViewById(R.id.btnDone);
        spnWork = (Spinner) findViewById(R.id.spnWork);
        spnTime = (Spinner) findViewById(R.id.spnTime);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strTime = spnTime.getSelectedItem().toString();
                String strWork = spnWork.getSelectedItem().toString();
                String strContent = strWork + " lúc " + strTime;
                display(strContent);
            }
        });

        initalData();
    }

    private void initalData() {
        String[] arrTime = this.getResources().getStringArray(R.array.arr_time);
        ArrayAdapter<String> adapterTime = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrTime);
        spnTime.setAdapter(adapterTime);

        String[] arrWork = this.getResources().getStringArray(R.array.arr_work);
        ArrayAdapter<String> adapterWork = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrWork);
        spnWork.setAdapter(adapterWork);

    }
}
