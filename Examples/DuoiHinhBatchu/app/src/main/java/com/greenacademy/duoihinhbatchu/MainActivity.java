package com.greenacademy.duoihinhbatchu;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvDisplay;
    TableLayout tblDynamic;
    TableLayout tblDapAn;

    //chuỗi có thay đổi
    ArrayList<Button> arrButton;
    ArrayList<TextView> arrTextView;
    String answer = "demo";
    String fill = "";
    int position=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tblDynamic = (TableLayout) findViewById(R.id.tblDynamic);
        tblDapAn = (TableLayout) findViewById(R.id.tblDapAn);
        //tblDapAn.setStretchAllColumns(true);

        arrButton = new ArrayList<Button>();
        arrTextView = new ArrayList<TextView>();
        creatTableButton(arrButton, tblDynamic, answer, this);
        creatTableTextView(arrTextView, tblDapAn, answer, this);

        // Xử lý sự kiện cho mảng Button
        for (int i = 0; i < arrButton.size() ; i++) {
            arrButton.get(i).setOnClickListener(this);
        }
        for (int i = 0; i < arrTextView.size(); i++) {
            arrTextView.get(i).setOnClickListener(this);
        }
    }

    private void creatTableTextView(ArrayList<TextView> arrTextView, TableLayout tblDapAn, String answer, MainActivity mainActivity) {
        char[] arrCharAnswer = answer.toCharArray();
        TableRow tr = new TableRow(mainActivity);

        //Tạo khoảng cách giữa các text
        TableRow.LayoutParams layouts = new TableRow.LayoutParams(50,50);
        layouts.setMargins(5,5,5,5);

        TextView temptv;
        for (int i = 0; i < arrCharAnswer.length ; i++) {
            temptv = new TextView(this);
            temptv.setGravity(Gravity.CENTER_HORIZONTAL);
            temptv.setBackgroundColor(Color.BLACK);
            temptv.setId(i+arrButton.size());
            //temp.setText(String.valueOf(arrCharAnswer[i]));
            tr.addView(temptv, layouts);
            arrTextView.add(temptv);
        }
        tblDapAn.addView(tr);
    }

    private void creatTableButton(ArrayList<Button> arrButton, TableLayout tblDynamic, String answer, Activity mainActivity) {
        char[] arrCharAnswer = answer.toCharArray();
        TableRow tr = new TableRow(mainActivity);

        Button temp;
        for (int i = 0; i < arrCharAnswer.length ; i++) {
            temp = new Button(this);
            temp.setText(String.valueOf(arrCharAnswer[i]));
            temp.setId(i);
            //Gắn 1 button vào view
            tr.addView(temp);
            arrButton.add(temp);
        }
        tblDynamic.addView(tr);
    }

    @Override
    public void onClick(View v) {
        for(int i=0; i<arrButton.size(); i++){
            Button temp = arrButton.get(i);
            TextView temptv = arrTextView.get(i);
            if(temp.getId() == v.getId()) {
                if (position >= arrTextView.size())
                    return;
                else{
                    arrTextView.get(position).setText(temp.getText().toString());
                    arrTextView.get(position).setBackgroundColor(Color.WHITE);
                    position++;
                }
            }

            if(temptv.getId() == v.getId()) {

                    arrTextView.get(i).setText("");
                    arrTextView.get(i).setBackgroundColor(Color.BLACK);
                    position = i;
            }
        }
    }
}
