package hte.edu.demoviewpagerswipe.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hte.edu.demoviewpagerswipe.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NhanVienFragment extends Fragment {


    public NhanVienFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nhan_vien, container, false);
    }

}
