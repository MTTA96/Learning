package com.example.user.democontact;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.user.democontact.adapter.ContactAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> titles = new ArrayList<>();
    ContactAdapter contactAdapter;

    boolean flag_swipe = false;
    int edit_position = -1;
    boolean add = false;

    AlertDialog.Builder alertDialog;
    EditText etUpdateContact;
    View view;
    Paint p = new Paint();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initDialog();
    }

    private void initDialog() {
        alertDialog = new AlertDialog.Builder(this);
        view = getLayoutInflater().inflate(R.layout.dialog_contact, null);
        alertDialog.setView(view);
        alertDialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                if (add){
                    add = false;
                    titles.add(etUpdateContact.getText().toString());
                    contactAdapter.notifyDataSetChanged();
                    recyclerView.scrollToPosition(titles.size() - 1);
                    dialogInterface.dismiss();
                }
                else{
                    titles.set(edit_position, etUpdateContact.getText().toString());
                    contactAdapter.notifyDataSetChanged();
                    dialogInterface.dismiss();
                }
            }
        });
        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                contactAdapter.notifyDataSetChanged();
            }
        });
        etUpdateContact = (EditText) view.findViewById(R.id.etUpdateContact);
    }

    private void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_contact);
        recyclerView.setHasFixedSize(true);

        //Thiết lập sắp xếp vị trí của item
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        contactAdapter = new ContactAdapter(titles);
        recyclerView.setAdapter(contactAdapter);
        titles.add("Cong phuong");
        titles.add("Tuan Anh");
        titles.add("Van Toan");
        titles.add("Dong Trieu");
        contactAdapter.notifyDataSetChanged();

        //Ite dividerItem = new DividerItemDecoration(recyclerView.getContext(), ((LinearLayoutManager) layoutManager.getOrientation()));
        //recyclerView.addItemDecoration(dividerItem);
        initSwipe();
    }

    private void initSwipe() {
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public int getSwipeDirs(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int position = viewHolder.getAdapterPosition();
                if (position != edit_position){
                    if(edit_position != -1 && flag_swipe == true){
                        contactAdapter.notifyItemChanged(edit_position);
                        flag_swipe = false;
                        edit_position = position;
                    }
                }
                return super.getSwipeDirs(recyclerView, viewHolder);
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                flag_swipe = true;
                if (direction == ItemTouchHelper.LEFT){
                    contactAdapter.removeItem(position);
                }
                else{
                    removeView();
                    edit_position = position;
                    alertDialog.setTitle("Edit Content");
                    etUpdateContact.setText(titles.get(position));
                    alertDialog.show();
                }
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void removeView() {
        if(view.getParent() != null){
            ((ViewGroup) view.getParent()).removeView(view);
        }
    }
}
