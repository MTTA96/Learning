package com.example.user.demoslidemenu;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.user.demoslidemenu.fragment.HinhChuNhatFragment;
import com.example.user.demoslidemenu.fragment.SinhVienFragment;
import com.example.user.demoslidemenu.fragment.TaiKhoanFragment;
import com.example.user.demoslidemenu.utils.Constant;

public class MainActivity extends AppCompatActivity {
    //Quản lý nhãn
    private String[] arrFunctionMenu;
    //
    private DrawerLayout mDrawerLayout;
    private ListView listView;
    //Xử lý sự kiện click vào item của menu
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.activity_main);
        listView = (ListView) findViewById(R.id.left_drawer);

        arrFunctionMenu = getResources().getStringArray(R.array.menu_slide);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrFunctionMenu);
        listView.setAdapter(adapter);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(R.string.open_slide);
            }
        };

        //Dang ký event cho Slide menu
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        //Dang ký cho listview trong SlideMenu
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                selectItem(i);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(android.R.drawable.ic_menu_sort_by_size);
        getSupportActionBar().setHomeButtonEnabled(true);
        selectItem(Constant.HCNFragment);
    }

    private void selectItem(int i) {
        Fragment fragment = null;
        switch (i){
            case Constant.HCNFragment:
                fragment = new HinhChuNhatFragment();
                break;
            case Constant.SinhVienFragment:
                fragment = new SinhVienFragment();
                break;
            case Constant.TaiKhoanFragment:
                fragment = new TaiKhoanFragment();
                break;
            case Constant.ExitFragment:
                moveTaskToBack(true);
                break;
        }

        listView.setItemChecked(i, true);
        mDrawerLayout.closeDrawer(listView);
        if(fragment != null){
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mDrawerToggle.onOptionsItemSelected(item)){
            return(true);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        //boolean drawerOpen = mDrawerLayout.isDrawerOpen(listView);
        return super.onPrepareOptionsMenu(menu);
    }

    //Thay đổi icon khi người dùng click vào slidemenu
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //Khôi phục lại icon
        mDrawerToggle.syncState();
    }
}
