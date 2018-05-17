package com.example.user.listphim.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.listphim.R;

public class DetailPhimFragment extends Fragment {
    private static final String ARGUMENT_IMAGE = "imageResID";
    private static final String ARGUMENT_NAME = "name";
    private static final String ARGUMENT_DESCRIPTION = "description";
    private static final String ARGUMENT_URL = "URL";

    TextView tvTenPhim;
    ImageView imagePhim;
    TextView tvDescription;
    public DetailPhimFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_detail_phim, container, false);
        tvTenPhim = (TextView) view.findViewById(R.id.tvTenPhim_PhimDetail);
        tvDescription = (TextView) view.findViewById(R.id.description_PhimDetail);
        imagePhim = (ImageView) view.findViewById(R.id.imageFilm_PhimDetail);

        Bundle args = getArguments();
        tvTenPhim.setText(args.getString(ARGUMENT_NAME));
        tvDescription.setText(args.getString(ARGUMENT_DESCRIPTION));
        imagePhim.setImageResource(args.getInt(ARGUMENT_IMAGE));
        //String url = args.getString(ARGUMENT_URL);
        return view;
    }

    public static DetailPhimFragment newInstance(int imageResID, String name, String description, String url) {
        final Bundle args = new Bundle();
        args.putInt(ARGUMENT_IMAGE, imageResID);
        args.putString(ARGUMENT_NAME, name);
        args.putString(ARGUMENT_DESCRIPTION, description);
        args.putString(ARGUMENT_URL, url);

        DetailPhimFragment fragment = new DetailPhimFragment();
        fragment.setArguments(args);

        return fragment;
    }
}
