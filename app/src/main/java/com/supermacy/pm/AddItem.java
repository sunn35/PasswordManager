package com.supermacy.pm;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import utilities.Databases;

public class AddItem extends Activity {

    private EditText name_edit_text;
    private EditText pass_edit_text;
    private EditText notes_edit_text;
    private Databases database;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_add_item);

        name_edit_text = (EditText) findViewById(R.id.editText3);
        pass_edit_text = (EditText) findViewById(R.id.editText4);
        notes_edit_text = (EditText) findViewById(R.id.editText5);

        database = new Databases(this.getApplicationContext());
        db = database.getWritableDatabase();
    }

    public void savePass(View view) {
        String name = name_edit_text.getText().toString();
        String pass = pass_edit_text.getText().toString();
        String notes = notes_edit_text.getText().toString();
        if (name.length() <= 0 || pass.length() <= 0) {
            //name = name_edit_text.getText().toString();
            Toast.makeText(this.getApplicationContext(), "NAME OR PASSWORD FIELDS CANNOT BE EMPTY", Toast.LENGTH_LONG).show();
            //throw error
        } else {
            try {
                database.insert(name, pass, notes);
                Toast.makeText(this.getApplicationContext(), "Item added successfully", Toast.LENGTH_SHORT).show();
                //search if database contains this name and then
                //db.insertIntoPasses("a", "zxc", "asd");
                //save to database
            } catch (SQLException e) {
                e.printStackTrace();
            }
            onBackPressed();
            finish();
        }
    }
}
