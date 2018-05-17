package com.greenacademy.lab06_handletext;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    //Khai báo
    TextView tvFont1;
    TextView tvFont2;
    TextView tvFont3;
    TextView tvFont4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ánh xạ
        tvFont1 = (TextView) findViewById(R.id.tvFont1Main);
        tvFont2 = (TextView) findViewById(R.id.tvFont2Main);
        tvFont3 = (TextView) findViewById(R.id.tvFont3Main);
        tvFont4 = (TextView) findViewById(R.id.tvFont4Main);

        //Thiết lập màn hình ban đầu
        setUpScreen();
    }

    private void setUpScreen() {
        //Thiết lập kiểu font
        tvFont1.setTypeface(Typeface.DEFAULT);
        tvFont2.setTypeface(Typeface.MONOSPACE);
        tvFont3.setTypeface(Typeface.SANS_SERIF);
        tvFont4.setTypeface(Typeface.SERIF);

        //Kiểu font nguồn ngoài lưu trong folder assets
        Typeface type = Typeface.createFromAsset(this.getAssets(), "tmcongdo.TTF");
        tvFont1.setTypeface(type);

        //Thay đổi kích thước
        tvFont1.setTypeface(type,Typeface.NORMAL);
        tvFont2.setTypeface(null,Typeface.BOLD);
        tvFont3.setTypeface(null,Typeface.BOLD_ITALIC);
        tvFont4.setTypeface(null, Typeface.ITALIC);

        //Thay đổi size
        tvFont1.setTextSize(20);
        tvFont2.setTextSize(30);
        tvFont3.setTextSize(40);
        tvFont4.setTextSize(50);

        //Thay đổi màu chữ
        tvFont1.setTextColor(Color.RED);
        tvFont2.setTextColor(Color.GREEN);
        tvFont3.setTextColor(Color.BLUE);
        tvFont4.setTextColor(Color.parseColor("#FFE06262"));

        //HTML
        //i: nghiêng, b: in đậm, u: gạch dưới
        String strHTML = "This <i> is </i> a <b> test </b> of <u> html </u>";
        tvFont2.setText(Html.fromHtml(strHTML));
        //Gạch trên chữ
        String strHTML1 = "This is a test of html";
        SpannableString spanStr= new SpannableString(strHTML1);
        spanStr.setSpan(new StrikethroughSpan(), 5, 14, 0);
        tvFont3.setText(spanStr);

        //Thêm hình vào text view
        tvFont4.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_launcher, 0, R.mipmap.ic_launcher, R.mipmap.ic_launcher); //left, top, right, bottom

        //Thiết lập shadow
        tvFont2.setShadowLayer(20, -15,-15, Color.BLACK);

        //Thiết lập gradient
        Shader shader = new LinearGradient(0, 0, 0, tvFont3.getTextSize()/2, Color.BLUE, Color.RED, Shader.TileMode.MIRROR);
        tvFont3.getPaint().setShader(shader);

        //Thiết lập textline
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cheetah);
        Shader shader1 = new BitmapShader(bitmap, Shader.TileMode.REPEAT,Shader.TileMode.REPEAT);
        tvFont4.getPaint().setShader(shader1);
    }
}
