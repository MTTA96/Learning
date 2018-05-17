package greenacademy.edu.calculatorproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import greenacademy.edu.utils.Constant;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvResult;
    Button btn0;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    Button btn8;
    Button btn9;
    Button btnDot;
    Button btnDelete;
    Button btnEqual;
    Button btnAdd;
    Button btnSub;
    Button btnMul;
    Button btnDiv;

    String strNumber = "";
    double doubleNumber1 = 0;
    double doubleNumber2 = 0;
    //1 - Add
    //2 - Sub
    //3 - Mul
    //4 - Div
    int Operation = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = (TextView) findViewById(R.id.tvResult);

        btn0 = (Button) findViewById(R.id.btn0);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnSub = (Button) findViewById(R.id.btnSub);
        btnMul = (Button) findViewById(R.id.btnMul);
        btnDiv = (Button) findViewById(R.id.btnDiv);
        btnDot = (Button) findViewById(R.id.btnDot);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnEqual = (Button) findViewById(R.id.btnEqual);

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnSub.setOnClickListener(this);
        btnMul.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
        btnDot.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnEqual.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn0:
                strNumber = strNumber + "0";
                display(strNumber);
                break;
            case R.id.btn1:
                strNumber = strNumber + "1";
                display(strNumber);
                break;
            case R.id.btn2:
                strNumber = strNumber + "2";
                display(strNumber);
                break;
            case R.id.btn3:
                strNumber = strNumber + "3";
                display(strNumber);
                break;
            case R.id.btn4:
                strNumber = strNumber + "4";
                display(strNumber);
                break;
            case R.id.btn5:
                strNumber = strNumber + "5";
                display(strNumber);
                break;
            case R.id.btn6:
                strNumber = strNumber + "6";
                display(strNumber);
                break;
            case R.id.btn7:
                strNumber = strNumber + "7";
                display(strNumber);
                break;
            case R.id.btn8:
                strNumber = strNumber + "8";
                display(strNumber);
                break;
            case R.id.btn9:
                strNumber = strNumber + "9";
                display(strNumber);
                break;
            case R.id.btnAdd:
                doubleNumber1 = convertNumber(strNumber);
                strNumber = "";
                display(strNumber);
                Operation = Constant.ADD;
                display(strNumber);
                break;
            case R.id.btnSub:
                doubleNumber1 = convertNumber(strNumber);
                strNumber = "";
                Operation = Constant.SUB;
                display(strNumber);
                break;
            case R.id.btnMul:
                doubleNumber1 = convertNumber(strNumber);
                strNumber = "";
                Operation = Constant.MUL;
                display(strNumber);
                break;
            case R.id.btnDiv:
                doubleNumber1 = convertNumber(strNumber);
                strNumber = "";
                Operation = Constant.DIV;
                display(strNumber);
                break;
            case R.id.btnDot:
                strNumber = strNumber + ".";
                display(strNumber);
                break;
            case R.id.btnDelete:
                strNumber = strNumber.substring(0, strNumber.length()-1);
                tvResult.setText(strNumber);
                break;
            case R.id.btnEqual:
                doubleNumber2 = convertNumber(strNumber);
                strNumber = "";
                double Result = calOperation (Operation);
                display(String.valueOf(Result));
                break;
        }

    }

    private void display(String s) {
        tvResult.setText(s);
    }

    private double calOperation(int operation) {
        double result = 0;
        switch (operation){
            case Constant.ADD:
                result = doubleNumber1 + doubleNumber2;
                break;
            case Constant.SUB:
                result = doubleNumber1 - doubleNumber2;
                break;
            case Constant.MUL:
                result = doubleNumber1 * doubleNumber2;
                break;
            case Constant.DIV:
                result = doubleNumber1 / doubleNumber2;
                break;
        }
        return result;
    }

    private double convertNumber(String strNumber) {
        return Double.parseDouble(strNumber);
    }
}