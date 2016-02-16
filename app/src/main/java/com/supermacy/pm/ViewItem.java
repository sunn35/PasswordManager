package com.supermacy.pm;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import utilities.Databases;

public class ViewItem extends AppCompatActivity {
    private TextView nameTextView, passTextView, notesTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);

        nameTextView = (TextView) findViewById(R.id.textView);
        passTextView = (TextView) findViewById(R.id.textView2);
        notesTextView = (TextView) findViewById(R.id.textView3);

        String selectedName = getIntent().getExtras().getString("name");
        Databases database = new Databases(this.getApplicationContext());
        //SQLiteDatabase db = database.getWritableDatabase();
        String notes = database.getNoteWithName(selectedName);
        Log.d("notes", notes);
        String pass = database.getPassWithName(selectedName);
        Log.d("pass", pass);
        nameTextView.setText(selectedName);
        passTextView.setText(pass);
        notesTextView.setText(notes);
    }
}
