package com.greenacademy.contextmenu;

import android.content.Intent;
import android.icu.lang.UProperty;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listviewNumber;

    ArrayAdapter<String> adapter;
    ArrayList<String> listSo;
    String[] arrSo;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listviewNumber = (ListView) findViewById(R.id.listSo);

        listviewNumber.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.this.position = position;
            }
        });
        //load dữ diệu
        loadData();
        registerForContextMenu(listviewNumber);

        Intent intent = getIntent();
//        String strName = intent.getStringExtra(EditActivity.KEY);
//        int pos = intent.getIntExtra(EditActivity.KEY_POSITION, 0);


    }

    private void loadData() {
        arrSo = getResources().getStringArray(R.array.arr_so);
        listSo = new ArrayList<>();
        for (int i = 0; i < arrSo.length; i++) {
            listSo.add(arrSo[i]);
        }
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listSo);
        listviewNumber.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //Lấy chỉ số index của listview
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.itemDelete:
                listSo.remove(info.position);
                adapter.notifyDataSetChanged();
                listviewNumber.invalidateViews();
                listviewNumber.refreshDrawableState();
                break;
            case R.id.itemEdit:
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                intent.putExtra(EditActivity.KEY, listSo.get(info.position));
                intent.putExtra(EditActivity.KEY_POSITION, info.position);
                Log.v("Main Item", String.valueOf(info.position));
                startActivityForResult(intent, 202);
                break;
            case R.id.itemShare:
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 202 && resultCode == RESULT_OK){
            Bundle bundle = data.getBundleExtra(EditActivity.KEY_BUNDLE);
            if(bundle != null){
                int pos = bundle.getInt(EditActivity.KEY_POSITION, 0);
                String strName = bundle.getString(EditActivity.KEY);
                listSo.remove(pos);
                listSo.add(pos, strName);
                adapter.notifyDataSetChanged();
                listviewNumber.invalidateViews();
                listviewNumber.refreshDrawableState();
            }
        }
    }
}
