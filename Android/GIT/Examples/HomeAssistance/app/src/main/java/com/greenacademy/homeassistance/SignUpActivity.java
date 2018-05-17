package com.greenacademy.homeassistance;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etInputName;
    EditText etPassword;
    EditText etEmail;
    EditText etPhone;
    EditText etCMND;
    EditText etAdress;
    Button btnCapture;
    Button btnBack;
    Button btnNext;
    ImageView ivPersonal;

    final int CaptureImage = 404;

    //Hiển thị thông báo
    public void display(String content){
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Ánh xạ
        etInputName = (EditText) findViewById(R.id.etInputNameSignUp);
        etPassword = (EditText) findViewById(R.id.etPasswordSignUP);
        etEmail = (EditText) findViewById(R.id.etEmailSignUp);
        etPhone = (EditText) findViewById(R.id.etPhoneSignUp);
        etCMND = (EditText) findViewById(R.id.etPhoneSignUp);
        etAdress = (EditText) findViewById(R.id.etAdressSignUp);
        btnCapture = (Button) findViewById(R.id.btnCaptureSignUp);
        btnBack = (Button) findViewById(R.id.btnBackSignUp);
        btnNext = (Button) findViewById(R.id.btnNextSignUp);
        ivPersonal = (ImageView) findViewById(R.id.ivPersonalSignUp);

        //Xử lý sự kiện
        btnNext.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        btnCapture.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnNextSignUp:
                //Kiểm tra người dùng đã nhập đầy đủ thông tin chưa
                String strUser = etInputName.getText().toString();
                String strPassword = etPassword.getText().toString();
                String strEmail = etEmail.getText().toString();
                String strPhone = etPhone.getText().toString();
                String strCMND = etCMND.getText().toString();
                String strAdress = etAdress.getText().toString();

                if(strUser.isEmpty() || strPassword.isEmpty() || strEmail.isEmpty() || strPhone.isEmpty() || strCMND.isEmpty() || strAdress.isEmpty()){
                    display("Chưa nhập đủ thông tin!");
                    return;
                }

                //Chuyển sang AuthorityActivity
                Intent intentToAuthorityActivity = new Intent(SignUpActivity.this, AuthorityActivity.class);
                startActivity(intentToAuthorityActivity);
                break;
            case R.id.btnBackSignUp:
                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.btnCaptureSignUp:
                //Chuyển sang màn hình chụp
                Intent intentCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //Khi sử dụng intent có kq trả về phải sử dụng startActivityForResult(intent, mã trả về)
                //Sử dụng onActivityResult để nhận kết quả trả về
                startActivityForResult(intentCapture, CaptureImage);
                break;
        }
    }

    //Nhận kết quả trả về
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CaptureImage & resultCode == RESULT_OK){
            Bundle bundle = data.getExtras();
            Bitmap imagBitmap = (Bitmap) bundle.get("data");
            ivPersonal.setImageBitmap(imagBitmap);
        }
    }
}
