package com.wordpress.elektroniknu.elektroniknu;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Data that I want too put in my ListView
        // Array of Strings
        String[] electronicSupplier;
        try {
            int lines = TextFileHandler.getLines();
            electronicSupplier = new String[lines];
            for(int i=0; i<lines; i++){
                electronicSupplier[i] = TextFileHandler.getStoreName(i+1);

            }

        } catch (IOException e) {
            electronicSupplier = new String[]{"adw", "@da"};
        }

        // ListAdapter too be able to adapt our array in too
        // something that our listview is able to work with
        ListAdapter theAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,
                electronicSupplier);


        ListView theListView = (ListView) findViewById(R.id.theListView);

        theListView.setAdapter(theAdapter);

        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //String electronicSupplier = "You selected" +
                //      String.valueOf(adapterView.getItemAtPosition(i));

                //      Toast.makeText(MainActivity.this, electronicSupplier,
                //      Toast.LENGTH_SHORT).show();

                int val = i + 1;
                Intent intent = new Intent(MainActivity.this, StorePdfActivity.class);
                intent.putExtra("key", val);
                MainActivity.this.startActivity(intent);

            }
        });
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
