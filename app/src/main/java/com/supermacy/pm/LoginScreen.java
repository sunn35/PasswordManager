package com.supermacy.pm;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import utilities.ApplicationConstants;
import utilities.Databases;

public class LoginScreen extends AppCompatActivity {
    private EditText username_editText, pass_editText;
    private Button login_button;
    private final String USERNAME = "Sudhanshu_Gupta";
    private final String PASSWORD = "letsroll";
    private final String FAKE_PASSWORD = "letsroll!!!";
    private ApplicationConstants constants;
    public static Databases database;
    public static SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        username_editText = (EditText) findViewById(R.id.editText);
        pass_editText = (EditText) findViewById(R.id.editText2);
        login_button = (Button) findViewById(R.id.button);

        database = new Databases(this.getApplicationContext());
        db = database.getWritableDatabase();
        database.onCreate(db);

    }

    public void login(View view) {
        String user = username_editText.getText().toString();
        String pass = pass_editText.getText().toString();
        if (user.equals(USERNAME) && pass.equals(PASSWORD)) {
            Toast.makeText(this.getApplicationContext(), "Logged In", Toast.LENGTH_SHORT).show();
            Intent trueIntent = new Intent(this, PassManager.class);
            startActivity(trueIntent);
        } else if (user.equals(USERNAME) && pass.equals(FAKE_PASSWORD)) {
            Toast.makeText(this.getApplicationContext(), "LOGGED IN", Toast.LENGTH_LONG).show();
            Intent fakeIntent = new Intent(this, FakePassManager.class);
            startActivity(fakeIntent);
        } else {
            Toast.makeText(this.getApplicationContext(), "Invalid Credentials", Toast.LENGTH_LONG).show();
        }
    }
}
