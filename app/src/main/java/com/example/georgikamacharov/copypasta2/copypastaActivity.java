package com.example.georgikamacharov.copypasta2;

import android.app.ActionBar;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;


public class copypastaActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copypasta);

        LinearLayout copypastaMainView = (LinearLayout) findViewById(R.id.copypastaMainView);

        DataBaseHelper dbHelper = new DataBaseHelper(this);
        dbHelper.createDataBase();
        dbHelper.openDataBase();
        dbHelper.close();

        String id = "1";
        dbHelper.openDataBase();
        Cursor c = dbHelper.fetchData();
        if(c.getCount() > 0){
            c.moveToFirst();
            while(!c.isAfterLast()){
                String text = c.getString(1);

                TextView textView = new TextView(this);
                textView.setPadding(0, 0, 0, 40);
                ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
                textView.setLayoutParams(lp);
                textView.setText(text + "\n");

                copypastaMainView.addView(textView);

                c.moveToNext();
            }
        }
        c.close();
        dbHelper.close();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_copypasta, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
