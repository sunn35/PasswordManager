package utilities;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by sudhanshu on 13/2/16.
 */
public class Databases extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Pm.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "DETAILS";
    private static final String KEY_ID = "ID";
    private static final String KEY_NAMES = "NAMES";
    private static final String KEY_NOTES = "NOTES";
    private static final String KEY_PASSWORDS = "PASSWORDS";
    private static final String COMMA_SEP = ", ";
    private static final String TEXT_TYPE = " TEXT";
    private static SQLiteDatabase database;
    private static String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL" + COMMA_SEP + KEY_NAMES + TEXT_TYPE + COMMA_SEP + KEY_PASSWORDS +
            TEXT_TYPE + COMMA_SEP + KEY_NOTES + TEXT_TYPE + ");";
    private static Cursor c;
    private static String[] projection = {KEY_NAMES, KEY_PASSWORDS, KEY_PASSWORDS};

    public Databases(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            this.database = db;
            db.execSQL(CREATE_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }

    public void insert(String name, String pass, String notes) {
        try {
            database.execSQL("INSERT INTO " + TABLE_NAME + "(" + KEY_NAMES + "," + KEY_PASSWORDS + "," + KEY_NOTES + ") VALUES('" + name + "','" + pass + "','" + notes + "');");
            Log.d("SUCCESS", "DATABASE VALUES INSERTION");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateByName(String oldName, String newName, String pass, String notes) {
        try {
            database.execSQL("UPDATE " + TABLE_NAME + " SET " + KEY_NAMES + "='" + newName + "', " + KEY_NOTES + "='" + notes + "', " + KEY_PASSWORDS + "='" + pass + "' WHERE " + KEY_NAMES + "='" + oldName + "';");
            Log.d("update", "UPDATE " + TABLE_NAME + " SET " + KEY_NAMES + "=" + newName + "," + KEY_NOTES + "=" + notes + "," + KEY_PASSWORDS + "=" + pass + " WHERE " + KEY_NAMES + "=" + oldName + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteByName(String name) {
        try {
            database.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + KEY_NAMES + "='" + name + "';");
            Log.d("delete", "query executed");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getPassWithName(String name) {
        try {
            Cursor c = database.rawQuery("SELECT * FROM " + TABLE_NAME + ";", null);
            c.moveToFirst();
            for (int i = 0; i < c.getCount(); i++) {
                if (c.getString(1).equals(name)) {
                    return c.getString(2);
                } else {
                    c.moveToNext();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getNoteWithName(String name) {
        try {
            Cursor c = database.rawQuery("SELECT * FROM " + TABLE_NAME + ";", null);
            c.moveToFirst();
            for (int i = 0; i < c.getCount(); i++) {
                if (c.getString(1).equals(name)) {
                    return c.getString(3);
                } else {
                    c.moveToNext();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getTotalNumberOfEntries() {
        c.moveToLast();
        long count = c.getCount();
        return (int) count;
    }

    public ArrayList<String> getAllNames() {
        ArrayList<String> names = new ArrayList<String>();
        try {
            Cursor c = database.rawQuery("SELECT * FROM " + TABLE_NAME + ";", null);
            c.moveToFirst();
            int count = c.getCount();
            Log.d("c get count", "" + c.getCount());
            for (int i = 0; i < count; i++) {
                if (c.getString(1) != null) {
                    names.add(i, c.getString(1));
                }
                c.moveToNext();
                Log.d("" + i, names.get(i).toString());
            }
            if (count == 0) {
                names.add(0, "CLICK BELOW TO ADD A PASSWORD");
            }
            return names;
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("Error", e.toString());
            return names;
        }
    }

    public String[] getAllPasses() {
        String[] passes = {"Hello", "hi"};

        return passes;
    }

    public String[] getAllNotes() {
        String[] notes = {"Hello", "Hi"};

        return notes;
    }
}

