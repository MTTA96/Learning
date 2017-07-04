package com.greenacademy.contextmenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {
    EditText etName;
    Button btnLuu;

    String itemMenu;
    int itemPosition;
    Intent intent;
    public static final String KEY = "key";
    public static final String KEY_POSITION = "POSITION";
    public static final String KEY_BUNDLE = "BUNDLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etName = (EditText) findViewById(R.id.etName_Edit);
        btnLuu = (Button) findViewById(R.id.btnLuu_Edit);

        intent = getIntent();
        itemMenu = intent.getStringExtra(KEY);
        itemPosition = intent.getIntExtra(KEY_POSITION, 0);
        etName.setHint(itemMenu);

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(KEY, etName.getText().toString());
                bundle.putInt(KEY_POSITION, itemPosition);
                intent.putExtra(KEY_BUNDLE, bundle);
                EditActivity.this.setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
