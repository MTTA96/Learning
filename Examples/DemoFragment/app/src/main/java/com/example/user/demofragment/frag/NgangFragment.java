package com.example.user.demofragment.frag;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.demofragment.MainActivity;
import com.example.user.demofragment.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NgangFragment extends Fragment implements View.OnClickListener {
    EditText etNumberX;
    Button btnTinh;
    TextView tvResult;

    EditText etNumberXTinhCanBacHai;
    Button btnTinhTinhCanBacHai;
    TextView tvResultCanBacHai;
    double result;
    public NgangFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewFragment = inflater.inflate(R.layout.fragment_ngang3, container, false);
        etNumberX = (EditText) viewFragment.findViewById(R.id.etInputNumberX_FragmentNgang);
        btnTinh = (Button) viewFragment.findViewById(R.id.btnTinhBinhPhuong_FragmentNgang);
        tvResult = (TextView) viewFragment.findViewById(R.id.tvResultBinhPhuong_FragmentNgang);
        etNumberXTinhCanBacHai = (EditText) viewFragment.findViewById(R.id.etInputNumberXTinhCanBacHai_FragmentNgang);
        btnTinhTinhCanBacHai = (Button) viewFragment.findViewById(R.id.btnTinhCanBacHai_FragmentNgang);
        tvResultCanBacHai = (TextView) viewFragment.findViewById(R.id.tvResultCanBacHai_FragmentNgang);

        //set events
        btnTinh.setOnClickListener(this);
        btnTinhTinhCanBacHai.setOnClickListener(this);
        return viewFragment;
    }

    @Override
    public void onClick(View v) {
        int x;
        switch (v.getId()){
            case R.id.btnTinhBinhPhuong_FragmentNgang:
                if(etNumberX.getText().toString().isEmpty()){
                    Toast.makeText(getActivity() , "Chưa nhập dữ liệu!", Toast.LENGTH_SHORT).show();
                    return;
                }
                x = Integer.parseInt(etNumberX.getText().toString());
                result = x*x;
                tvResult.setText(tvResult.getText().toString() + " " + String.valueOf(x) + "^2 = " +String.valueOf(result));
                break;
            case R.id.btnTinhCanBacHai_FragmentNgang:
                if (etNumberXTinhCanBacHai.getText().toString().isEmpty()){
                    Toast.makeText(getActivity() , "Chưa nhập dữ liệu!", Toast.LENGTH_SHORT).show();
                    return;
                }
                x = Integer.parseInt(etNumberXTinhCanBacHai.getText().toString());
                result = Math.sqrt(x);
                tvResultCanBacHai.setText("Căn bậc 2 của " + String.valueOf(x) + " = " + String.valueOf(result));
        }
    }
}
