package projectcontrolerbeta1.com.allscriptpage;

/**
 * Created by tappasarn on 26/9/2558.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



/**
 * Created by admin on 9/25/2015.
 */
public class ScriptViewDatabaseHelper extends SQLiteOpenHelper {

    public static String dbName;
    private Context context;
    public static final int DB_VERSION =5;
    ScriptViewDatabaseHelper(Context context) {
        super(context, context.getResources().getString(R.string.database_name), null, DB_VERSION);
        dbName = context.getResources().getString(R.string.database_name);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db, 0, DB_VERSION);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+dbName);
        onCreate(db);
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            String str = String.format("CREATE TABLE %s (_id INTEGER PRIMARY KEY AUTOINCREMENT, ", dbName);
            db.execSQL(str + "NAME TEXT);");
            insertScript(db, "Script001");
            insertScript(db, "Script002");
            insertScript(db, "Script003");
            insertScript(db, "Script004");
            insertScript(db, "Script005");
            insertScript(db, "Script006");
            insertScript(db, "Script007");
            insertScript(db, "Script008");
            insertScript(db, "Script009");
            insertScript(db, "Script0010");
            insertScript(db, "Script0011");
            insertScript(db, "Script0012");

        }
    }
    public static void insertScript(SQLiteDatabase db, String name) {
        ContentValues actValues = new ContentValues();
        actValues.put("NAME", name);
        db.insert(dbName, null, actValues);
    }

}