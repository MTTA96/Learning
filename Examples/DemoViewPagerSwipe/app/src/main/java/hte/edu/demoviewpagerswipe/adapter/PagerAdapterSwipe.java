package hte.edu.demoviewpagerswipe.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import hte.edu.demoviewpagerswipe.fragment.NhanVienFragment;
import hte.edu.demoviewpagerswipe.fragment.SinhVienFragment;
import hte.edu.demoviewpagerswipe.fragment.TamGiacFragment;

/**
 * Created by User on 4/25/2017.
 */

public class PagerAdapterSwipe extends FragmentPagerAdapter {

    String[] arrTitle = {"Sinh Viên", "Tam Giác", "Nhân Viên"};
    public PagerAdapterSwipe(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new SinhVienFragment();
                break;
            case 1:
                fragment = new TamGiacFragment();
                break;
            case 2:
                fragment = new NhanVienFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return arrTitle.length;
    }

    public String getTitle(int position){
        return arrTitle[position];
    }
}
