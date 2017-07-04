package com.greenacademy.mymenuoption;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TextView tvDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplay = (TextView) findViewById(R.id.tvDisplay);
    }

    //Tạo menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Xử lý sự kiện
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemGamePad:
                //tvDisplay.setText(item.getTitle());
                break;
            case R.id.itemCamera:
                //tvDisplay.setText(item.getTitle());
                break;
            case R.id.itemVideo:
                //tvDisplay.setText(item.getTitle());
                break;
            case R.id.itemEmail:
                //tvDisplay.setText(item.getTitle());
                break;
        }
        tvDisplay.setText(item.getTitle());
        return super.onOptionsItemSelected(item);
    }
}
