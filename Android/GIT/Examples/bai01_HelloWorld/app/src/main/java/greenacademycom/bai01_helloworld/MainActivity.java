package greenacademycom.bai01_helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //1. Khai bao view chuong trinh tuong tac
    EditText etInput;
    Button btnSayHello;
    TextView tvOutput;

    //1.1 Khai bao cac gia tri ban dau
    String strHello = "Hello, ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //2. anh xa
        etInput = (EditText) findViewById(R.id.etInputName);
        btnSayHello = (Button) findViewById(R.id.btnSayHello);
        tvOutput = (TextView) findViewById(R.id.tvOutputName);

        //3. Dang ky xu ly su kien
        btnSayHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strInput = etInput.getText().toString();
                String strOutput = strHello + strInput;
                tvOutput.setText(strOutput);
            }
        });

        //4. Khoi tao gia tri ban dau
    }
}
