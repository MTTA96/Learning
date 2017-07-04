package com.greenacademy.mycontextmenucontextual;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listviewNumber;

    ArrayAdapter<String> adapter;
    ArrayList<String> listNumber;

    int currentIndex;
    //Sử dụng menu contextual khai báo ActionMode
    private ActionMode currentActionMode;
    private ActionMode.Callback modeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.setTitle("Remove Item");
            mode.getMenuInflater().inflate(R.menu.context_menu,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch(item.getItemId()){
                case R.id.itemDelete:

                    break;
                case R.id.itemEdit:
                    break;
                case R.id.itemShare:
                    break;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            currentActionMode = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listviewNumber = (ListView) findViewById(R.id.lvNumber_Main);

        loadData();

        listviewNumber.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(currentActionMode != null){
                    return;
                }
                currentActionMode = startActionMode(modeCallback);
                currentIndex = position;

            }
        });
    }

    private void loadData() {
        String[] arrSo = getResources().getStringArray(R.array.listNumber);

        listNumber = new ArrayList<>();
        for (int i = 0; i < arrSo.length; i++) {
            listNumber.add(arrSo[i]);
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listNumber);

        listviewNumber.setAdapter(adapter);
    }
}
