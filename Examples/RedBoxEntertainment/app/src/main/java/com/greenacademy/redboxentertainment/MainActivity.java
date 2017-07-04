package com.greenacademy.redboxentertainment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.greenacademy.redboxentertainment.adapter.MyFragmentPagerAdapter;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    ViewPager pager;
    MyFragmentPagerAdapter adapter;
    ActionBar actionBar;
    int numberNotify = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = (ViewPager) findViewById(R.id.pager);

        adapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

        actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                pager.setCurrentItem(tab.getPosition());
                updateColor(actionBar.getSelectedTab(), Color.RED);
                numberNotify++;
                updateNumber(actionBar.getSelectedTab(), numberNotify);
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
                updateColor(actionBar.getSelectedTab(), Color.WHITE);
            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
               updateColor(actionBar.getSelectedTab(), Color.WHITE);
            }
        };
        for (int i = 0; i < adapter.getCount(); i++) {
            actionBar.addTab(actionBar.newTab().setText(adapter.getTitle(i))
                    .setCustomView(renderTabView(MainActivity.this, adapter.getTitle(i), 2))
                    .setText(adapter.getTitle(i))
                    .setTabListener(tabListener));
        }

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                pager.setCurrentItem(position);
            }

            @Override
            public void onPageSelected(int position) {
//                pager.setCurrentItem(position);
                actionBar.setSelectedNavigationItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void updateNumber(ActionBar.Tab tab, int number){
        ((TextView) (tab.getCustomView().findViewById(R.id.tvTabbadge))).setText(String.valueOf(number));
    }

    private void updateColor(ActionBar.Tab tab, int color){
        tab.getCustomView().setBackgroundColor(color);
    }

    private View renderTabView(Context context, String title, int num) {
        FrameLayout view = (FrameLayout) LayoutInflater.from(context).inflate(R.layout.item_tab_layout, null);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        TextView tvTabItem = ((TextView) view.findViewById(R.id.tab_text));
        tvTabItem.setText(title);
        view.setBackgroundColor(Color.WHITE);
        TextView tvTabbadge = (TextView) view.findViewById(R.id.tvTabbadge);
        tvTabbadge.setText(String.valueOf(num));
//        TextView tvNameBar = new TextView(context);
//        tvNameBar.setTypeface(Typeface.DEFAULT);
//        tvNameBar.setLayoutParams(new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        tvNameBar.setGravity(Gravity.CENTER);
//        tvNameBar.setText(title);
//        tvNameBar.setBackgroundColor(Color.WHITE);
        return view;
    }
}
