package com.example.user.finalbasicproject;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.user.finalbasicproject.control.FragNavControllerSignInUp;
import com.example.user.finalbasicproject.fragment.DSignUpFragment;
import com.example.user.finalbasicproject.fragment.HSignUpFragment;
import com.example.user.finalbasicproject.utils.Constant;

import java.util.ArrayList;

public class ChonViec extends AppCompatActivity {
    Spinner spnChonViec;
    LinearLayout layoutInfoViec;

    ArrayAdapter<String> adapter;
    HSignUpFragment hSignUpFragment;
    DSignUpFragment dSignUpFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_viec);

        //Ánh xạ
        spnChonViec = (Spinner) findViewById(R.id.spn_ChonViec_ChonViec);
        layoutInfoViec = (LinearLayout) findViewById(R.id.layout_infoViec_ChonViec);

        spnChonViec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragNavControllerSignInUp controller = new FragNavControllerSignInUp(R.id.activity_chon_viec, fragmentManager);

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case Constant.ACCOUNT_TYPE:
                        controller.removeFragment();
                        break;
                    case Constant.ACCOUNT_STUDENT:
                        controller.selectFragment(Constant.ACCOUNT_STUDENT);
                        layoutInfoViec.setVisibility(View.GONE);
                        break;
                    case Constant.ACCOUNT_TEACHER:
                        controller.selectFragment(Constant.ACCOUNT_TEACHER);
                        layoutInfoViec.setVisibility(View.GONE);
                        break;
                    case 3:
                        controller.selectFragment(Constant.ACCOUNT_STUDENT);
                        layoutInfoViec.setVisibility(View.GONE);
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(ChonViec.this, "zz", Toast.LENGTH_SHORT).show();
            }
        });

        loadData();

    }

    private void loadData() {
        String[] tempChonViec = getResources().getStringArray(R.array.arr_viec_lam);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tempChonViec);
        spnChonViec.setAdapter(adapter);
    }
}
