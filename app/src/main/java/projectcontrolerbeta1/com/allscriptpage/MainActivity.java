package projectcontrolerbeta1.com.allscriptpage;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.Script;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //init variables here
    public static final String TAG = "TIME";
    ListView listScriptView;
    private ArrayAdapter<String> scriptListAdapter;
    private Cursor ScriptDBCursor;
    private ArrayList <String> scriptArrayList = new ArrayList <String>();
    private int addToPosition,old_position = -1;
    private ImageButton addScript, editScript, runScript;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listScriptView = (ListView) findViewById(R.id.list_options);

        // create sql helper below here
        try {
            SQLiteOpenHelper scriptDatabaseHelper = new ScriptViewDatabaseHelper(this);
            SQLiteDatabase db = scriptDatabaseHelper.getReadableDatabase();
            // create cursor and it will contain _id and name coloums
            ScriptDBCursor = db.query(getResources().getString(R.string.database_name), new String[]{"_id", "NAME"},
                    null, null, null, null, null);

            //copy value from database to arraylist
            ScriptDBCursor.moveToFirst();
            while (!ScriptDBCursor.isLast()) {
                scriptArrayList.add(ScriptDBCursor.getString(1));
                ScriptDBCursor.moveToNext();
            }

            //adding the last value of ArrayList bcuz it was excluded in the above while loop
            scriptArrayList.add(ScriptDBCursor.getString(1));
            scriptListAdapter = new ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_list_item_activated_1,
                    scriptArrayList);
            listScriptView.setAdapter(scriptListAdapter);

            //defult adding position will be appending to the last of the list
            addToPosition = scriptArrayList.size();

            //implement the listview so it can receive long click and normal click to delete
            listScriptView.setLongClickable(true);
            listScriptView.setClickable(true);
            listScriptView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

            //set listenner for both longclick and normal click
            listScriptView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                               int pos, long id) {
                    Toast toast = Toast.makeText(MainActivity.this, String.format("The script %s is removed", scriptArrayList.get(pos)), Toast.LENGTH_SHORT);
                    toast.show();
                    scriptArrayList.remove(pos);
                    scriptListAdapter.notifyDataSetChanged();
                    return true;
                }
            });
            listScriptView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                    //unchecking item in list view
                    if (old_position == pos) {
                        Toast toast = Toast.makeText(MainActivity.this, String.format("The script %s is uncheck", scriptArrayList.get(pos)), Toast.LENGTH_SHORT);
                        toast.show();
                        listScriptView.setItemChecked(pos, false);
                        //use for moving old_position to unreal pos allowing toggle
                        old_position = scriptArrayList.size();
                    }
                    //for checking item on list view
                    else {
                        Toast toast = Toast.makeText(MainActivity.this, String.format("The script %s is check", scriptArrayList.get(pos)), Toast.LENGTH_SHORT);
                        toast.show();
                        listScriptView.setItemChecked(pos, true);
                        old_position = pos;
                    }

                }
            });
        }

            catch (SQLiteException e){
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

        //FAB listenner implement here
        addScript = (ImageButton) findViewById(R.id.addButton);
        addScript.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //here more code on add button here
                Toast toast = Toast.makeText(MainActivity.this, "implement add buttion function", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        editScript = (ImageButton) findViewById(R.id.editButton);
        editScript.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //here more code on add button here
                Toast toast = Toast.makeText(MainActivity.this, "implement edit buttion function", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        runScript = (ImageButton) findViewById(R.id.uploadButton);
        runScript.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //here more code on add button here
                Toast toast = Toast.makeText(MainActivity.this,"implement run buttion function", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }


}