package com.greenacademy.xulyinputtext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etName;
    EditText etFullName;
    EditText etPhonenumber;
    EditText etEmail;
    EditText etPassword;
    Button btnKiemTra;
    Button btnXoa;
    Button btnThoat;

    Spinner spnTitle;
    String[] arrStrTitle;

    public void display(String content){
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.etInputNameMain);
        etFullName = (EditText) findViewById(R.id.etInputFullNameMain);
        etPhonenumber = (EditText) findViewById(R.id.etInputPhonenumberMain);
        etEmail = (EditText) findViewById(R.id.etInputEmailMain);
        etPassword = (EditText) findViewById(R.id.etInputPasswordMain);
        btnKiemTra = (Button) findViewById(R.id.btnKiemTraMain);
        btnXoa = (Button) findViewById(R.id.btnDeleteMain);
        btnThoat = (Button) findViewById(R.id.btnExitMain);
        spnTitle = (Spinner) findViewById(R.id.spnTitleMain);

        btnKiemTra.setOnClickListener(this);
        btnXoa.setOnClickListener(this);
        btnThoat.setOnClickListener(this);
        spnTitle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //tv.setText(arrStrTitle[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Dữ liệu ban đầu
        loadData();
    }

    private void loadData() {
        arrStrTitle = getResources().getStringArray(R.array.arrDataSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrStrTitle);
        spnTitle.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnKiemTraMain:
                String strName = etName.getText().toString();
                String strFullName = etFullName.getText().toString();
                String strPhonenumber = etPhonenumber.getText().toString();
                String strEmail = etEmail.getText().toString();
                String strPassword = etPassword.getText().toString();

                if(strName.isEmpty() || strFullName.isEmpty() || strPhonenumber.isEmpty() || strEmail.isEmpty() || strPassword.isEmpty()){
                    display("Chưa nhập đủ thông tin");
                }
                else{
                    display("Xong");
                }
                break;
            case R.id.btnDeleteMain:
                etName.setText("");
                etFullName.setText("");
                etPhonenumber.setText("");
                etEmail.setText("");
                etPassword.setText("");
                break;
            case R.id.btnExitMain:
                System.exit(0);
                break;
        }
    }
}
