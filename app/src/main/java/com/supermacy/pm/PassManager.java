package com.supermacy.pm;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

import utilities.ApplicationConstants;
import utilities.Databases;
import utilities.ListViewAdapter;
import utilities.ListViewBaseAdapter;

public class PassManager extends AppCompatActivity {
    private ListView namesList;
    private ApplicationConstants constants;
    private Databases database;
    private SQLiteDatabase db;
    public static ArrayList<String> namesArrayList;
    public static ListViewAdapter listViewAdapter;
    View customOptionsView, parentView;
    Button AddItemButton;
    View selectedListItem;
    int selectedItemId;
    private String selectedName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_manager);

        AddItemButton = (Button) findViewById(R.id.button);
        database = new Databases(this.getApplicationContext());
        db = database.getWritableDatabase();
        parentView = findViewById(R.id.parentView);
        namesArrayList = database.getAllNames();
        listViewAdapter = new ListViewAdapter(this.getApplicationContext(), namesArrayList);
        listViewAdapter.notifyDataSetChanged();
        namesList = (ListView) findViewById(R.id.listViewproper);
        namesList.setAdapter(listViewAdapter);
        namesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    if (selectedListItem != null) {
                        selectedListItem.setBackgroundColor(Color.WHITE);
                    }
                    selectedListItem = view;
                    selectedListItem.setBackgroundColor(Color.BLUE);
                    selectedItemId = position;
                    selectedName = namesArrayList.get(position);
                    Log.d("name", "" + selectedName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        namesArrayList = database.getAllNames();
        listViewAdapter.clear();
        listViewAdapter.addAll(namesArrayList);
        listViewAdapter.notifyDataSetChanged();
    }

    public void addItem(View view) {
        Intent intent = new Intent(this, AddItem.class);
        startActivity(intent);
    }

    public void viewItem(View view) {
        Intent intent = new Intent(this, ViewItem.class);
        intent.putExtra("name", selectedName);
        startActivity(intent);
    }

    public void editItem(View view) {
        Intent intent = new Intent(this, EditItem.class);
        intent.putExtra("name", selectedName);
        startActivity(intent);
    }

    public void deleteItem(View view) {
        database.deleteByName(selectedName);
        namesArrayList = database.getAllNames();
        listViewAdapter.clear();
        listViewAdapter.addAll(namesArrayList);
        listViewAdapter.notifyDataSetChanged();
    }
}
