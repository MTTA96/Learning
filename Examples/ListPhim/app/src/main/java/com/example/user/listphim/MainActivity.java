package com.example.user.listphim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.user.listphim.adapter.OnFilmSelected;
import com.example.user.listphim.fragment.DetailPhimFragment;
import com.example.user.listphim.fragment.ListPhimFragment;

public class MainActivity extends AppCompatActivity implements OnFilmSelected {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.activity_main, ListPhimFragment.newInstance(), "FilmList")
                    .commit();
        }
    }

    @Override
    public void onFilmSelected(int ImageResID, String name, String description, String url) {
        DetailPhimFragment detailPhimFragment = DetailPhimFragment.newInstance(ImageResID, name, description, url);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_main, detailPhimFragment, "FilmDetail")
                //Mở màn hình khác không quay lại màn hình này
                .addToBackStack(null)
                .commit();
    }
}
