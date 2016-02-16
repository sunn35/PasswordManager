package com.supermacy.pm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import utilities.Databases;

public class EditItem extends AppCompatActivity {
    private EditText nameEditText, passEditText, noteEditText;
    private Databases database;
    private String oldName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        nameEditText = (EditText) findViewById(R.id.editText6);
        passEditText = (EditText) findViewById(R.id.editText7);
        noteEditText = (EditText) findViewById(R.id.editText8);
        oldName = getIntent().getExtras().getString("name");
        database = new Databases(this.getApplicationContext());
        String note = database.getNoteWithName(oldName);
        String pass = database.getPassWithName(oldName);
        nameEditText.setText(oldName);
        passEditText.setText(pass);
        noteEditText.setText(note);
    }

    public void update(View view) {
        try {
            String newName = nameEditText.getText().toString();
            String pass = passEditText.getText().toString();
            String notes = noteEditText.getText().toString();
            database.updateByName(oldName, newName, pass, notes);
            onBackPressed();
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
