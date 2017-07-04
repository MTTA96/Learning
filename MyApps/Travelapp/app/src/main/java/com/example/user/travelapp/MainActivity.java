package com.example.user.travelapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.user.travelapp.fragment.DangKyFragment;
import com.example.user.travelapp.fragment.DangNhapFragment;
import com.example.user.travelapp.fragment.DuLichFragment;
import com.example.user.travelapp.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(android.R.id.content, new DangNhapFragment()).commit();
    }
}
