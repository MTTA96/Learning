package com.example.user.demofragment;

import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.user.demofragment.frag.DocFragment;
import com.example.user.demofragment.frag.NgangFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Configuration config = getResources().getConfiguration();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(config.orientation == Configuration.ORIENTATION_LANDSCAPE){
            NgangFragment ngangFragment = new NgangFragment();
            fragmentTransaction.replace(android.R.id.content, ngangFragment);
        }
        else {
            DocFragment docFragment = new DocFragment();
            fragmentTransaction.replace(android.R.id.content, docFragment);
        }
        fragmentTransaction.commit();
    }
}
