package com.greenacademy.demolistview;

import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.greenacademy.demolistview.util.SupportList;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    TextView tvLabel;
    TextView tvIndex;
    ListView listPhone;
    EditText etSearch;
    Button btnSeach;
    Button btnSort;

    //load data ListView
    String[] arrPhone;
    ArrayAdapter<String> adapter;
    SupportList sup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvIndex = (TextView) findViewById(R.id.tvIndexMain);
        tvLabel = (TextView) findViewById(R.id.tvLabelMain);
        listPhone = (ListView) findViewById(R.id.listPhoneMain);
        etSearch = (EditText) findViewById(R.id.etInputNameMain);
        btnSeach = (Button) findViewById(R.id.btnSearchMain);
        btnSort = (Button) findViewById(R.id.btnSortMain);

        listPhone.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tvIndex.setText(String.valueOf(position));
                tvLabel.setText(String.valueOf(arrPhone[position]));
            }
        });

        btnSeach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneSearch = etSearch.getText().toString();
                ArrayList<String> result = sup.searchMobile(phoneSearch);
                ArrayAdapter<String> newAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, result);
                listPhone.setAdapter(newAdapter);
            }
        });

        btnSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] sortPhone = arrPhone;
                Arrays.sort(sortPhone);
                ArrayAdapter<String> newAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, sortPhone);
                listPhone.setAdapter(newAdapter);

            }
        });
        loadData();

        sup = new SupportList(arrPhone);
    }

    private void loadData() {
        //Hiển thị content len listview phai thông qua adapter
        arrPhone = getResources().getStringArray(R.array.arr_phone);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrPhone);
        listPhone.setAdapter(adapter);

    }
}
