package utilities;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by sudhanshu on 13/2/16.
 */
public class ApplicationConstants {

    private static Context context;

    public static Databases database = new Databases(context);
    public static SQLiteDatabase db = database.getWritableDatabase();

    public ApplicationConstants(Context context) {
        this.context = context;
    }
}
