package com.supermacy.pm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FakePassManager extends AppCompatActivity {
    private ListView namesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fake_pass_manager);


        //initialize database variables
        String[] arrayList = {"Hello", "hi"};

        //arrayList=get all items from database

        ArrayAdapter listAdapter = new ArrayAdapter<String>(this, R.layout.listview, arrayList);
        namesList = (ListView) findViewById(R.id.listView);
        namesList.setAdapter(listAdapter);
    }

    public void additem(View view) {
        Intent intent = new Intent(this, AddItem.class);
        startActivity(intent);
    }
}
