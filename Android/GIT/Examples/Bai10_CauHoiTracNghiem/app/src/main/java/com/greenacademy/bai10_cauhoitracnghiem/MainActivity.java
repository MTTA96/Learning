package com.greenacademy.bai10_cauhoitracnghiem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.greenacademy.bai10_cauhoitracnghiem.control.HandleCauHoi;
import com.greenacademy.bai10_cauhoitracnghiem.model.CauHoi;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnTrue;
    Button btnFalse;
    Button btnPrevious;
    Button btnNext;
    ImageButton ibNext;
    ImageButton ibPrevious;
    TextView tvCauHoi;

    ArrayList<CauHoi> list;
    CauHoi cauhoi;
    HandleCauHoi handle;

    public void display(String content)
    {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCauHoi = (TextView) findViewById(R.id.tvCauHoi);
        btnTrue = (Button) findViewById(R.id.btnTrue);
        btnFalse = (Button) findViewById(R.id.btnFalse);
        btnPrevious = (Button) findViewById(R.id.btnPrevious);
        btnNext = (Button) findViewById(R.id.btnNext);
        ibNext = (ImageButton) findViewById(R.id.ibNext);
        ibPrevious = (ImageButton) findViewById(R.id.ibPrevious);

        btnTrue.setOnClickListener(this);
        btnFalse.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnPrevious.setOnClickListener(this);
        ibNext.setOnClickListener(this);
        ibPrevious.setOnClickListener(this);

        //Khoi tao dữ liệu
        initialData();
        loadFirstQuestion();
    }

    private void loadFirstQuestion() {
        cauhoi = handle.get(0);
        tvCauHoi.setText(cauhoi.getNoiDungCauHoi());
    }

    private void initialData() {
        String []arrCauHoi = this.getResources().getStringArray(R.array.arr_cau_hoi);
        String []arrDapAn = this.getResources().getStringArray(R.array.arr_dap_an);
        list = new ArrayList<>();
        boolean getAnswer;
        for (int i = 0; i < arrCauHoi.length ; i++) {
            cauhoi = new CauHoi();
            cauhoi.setNoiDungCauHoi(arrCauHoi[i]);
            if(arrDapAn[i].equals("0"))
            {
                getAnswer = false;
            }
            else{
                getAnswer = true;
            }
            cauhoi.setDapAn(getAnswer);
            list.add(cauhoi);
        }
        handle = new HandleCauHoi(list);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnTrue:
                testAnswerUser(true);
                break;
            case R.id.btnFalse:
                testAnswerUser(false);
                break;
            case R.id.btnPrevious:
                cauhoi = handle.previousQuestion();
                tvCauHoi.setText(cauhoi.getNoiDungCauHoi());
                break;
            case R.id.btnNext:
                cauhoi = handle.nextQuestion();
                tvCauHoi.setText(cauhoi.getNoiDungCauHoi());
                break;
            case R.id.ibPrevious:
                cauhoi = handle.previousQuestion();
                tvCauHoi.setText(cauhoi.getNoiDungCauHoi());
                break;
            case R.id.ibNext:
                cauhoi = handle.nextQuestion();
                tvCauHoi.setText(cauhoi.getNoiDungCauHoi());
                break;
        }
    }

    private void testAnswerUser(boolean b) {
        cauhoi.setDapAnNguoiDung(b);
        if(cauhoi.compareUser())
        {
            display("Dung");
        }
        else
        {
            display("Sai");
        }
    }
}
