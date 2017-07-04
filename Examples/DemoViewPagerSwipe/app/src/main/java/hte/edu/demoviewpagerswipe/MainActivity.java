package hte.edu.demoviewpagerswipe;

import android.graphics.Color;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import hte.edu.demoviewpagerswipe.adapter.PagerAdapterSwipe;

public class MainActivity extends AppCompatActivity {
    ViewPager pager;
    PagerAdapterSwipe adapter;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = (ViewPager) findViewById(R.id.pager);

        adapter = new PagerAdapterSwipe(getSupportFragmentManager());
        pager.setAdapter(adapter);

        actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                pager.setCurrentItem(tab.getPosition());
                //updateColor(actionBar.getSelectedTab(), Color.RED);
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
                //updateColor(actionBar.getSelectedTab(), Color.BLACK);
            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

            }
        };

        // add title vao tab
        for (int i = 0; i < adapter.getCount(); i++) {
            actionBar.addTab(actionBar.newTab().setText(adapter.getTitle(i))
                    .setTabListener(tabListener));
        }

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                pager.setCurrentItem(position);
            }

            @Override
            public void onPageSelected(int position) {
                //pager.setCurrentItem(position);
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void updateColor(ActionBar.Tab tab, int color) {
        tab.getCustomView().setBackgroundColor(color);
    }
}
