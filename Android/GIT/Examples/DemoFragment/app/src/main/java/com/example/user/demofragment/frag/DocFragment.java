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

import com.example.user.demofragment.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DocFragment extends Fragment implements View.OnFocusChangeListener, View.OnClickListener {
    EditText etNgay;
    EditText etThang;
    EditText etNam;
    TextView tvResult;

    EditText etNumberX;
    Button btnTinh;
    TextView tvResultTinhBieuThuc;
    double resultBieuThuc;

    public DocFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewFragment = inflater.inflate(R.layout.fragment_doc, container, false);
        etNgay = (EditText) viewFragment.findViewById(R.id.etInputNgay_FragmentDoc);
        etThang = (EditText) viewFragment.findViewById(R.id.etInputThang_FragmentDoc);
        etNam = (EditText) viewFragment.findViewById(R.id.etInputNam_FragmentDoc);
        tvResult = (TextView) viewFragment.findViewById(R.id.tvOutputNgay_FragmentDoc);
        etNumberX = (EditText) viewFragment.findViewById(R.id.etInputNumberX_FragmentDoc);
        btnTinh = (Button) viewFragment.findViewById(R.id.btnTinhBieuThuc_FragmentDoc);
        tvResultTinhBieuThuc = (TextView) viewFragment.findViewById(R.id.tvResultBinhPhuong_FragmentDoc);

        //Sử dụng OnFocusChangListener cho EditText: khi người dùng ấn vào et dữ liệu sẽ được truyền vào tvResult
        etNgay.setOnFocusChangeListener(this);
        etThang.setOnFocusChangeListener(this);
        etNam.setOnFocusChangeListener(this);
        btnTinh.setOnClickListener(this);
        return viewFragment;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()){
            case R.id.etInputNgay_FragmentDoc:
                tvResult.setText(etNgay.getText().toString());
                break;
            case R.id.etInputThang_FragmentDoc:
                tvResult.setText(etThang.getText().toString());
                break;
            case R.id.etInputNam_FragmentDoc:
                tvResult.setText(etNam.getText().toString());
                break;
            default:
                tvResult.setText(etNgay.getText().toString() + "/" + etThang.getText().toString() + "/" + etNam.getText().toString());
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnTinhBieuThuc_FragmentDoc:
                if(etNumberX.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Chưa nhập dữ liệu!", Toast.LENGTH_SHORT);
                    return;
                }
                int x = Integer.parseInt(etNumberX.getText().toString());
                resultBieuThuc = 3 * Math.pow(x,3) - 5 * Math.pow(x,2) + 6;
                tvResultTinhBieuThuc.setText("3x^3 - 5x^2 + 6 = " + String.valueOf(resultBieuThuc));
                break;
        }
    }
}
