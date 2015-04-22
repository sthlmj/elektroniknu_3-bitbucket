package com.wordpress.elektroniknu.elektroniknu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    /**
     * Implementation of ListView
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Data that I want too put in my ListView

        // Array of Strings
        /*String[] electronicSupplier;
        try {
            int lines = TextFileHandler.getLines(getResources());
            electronicSupplier = new String[lines];
            for(int i=0; i<lines; i++){
                electronicSupplier[i] = TextFileHandler.getStoreName(i+1, getResources());

            }

        } catch (IOException e) {
            electronicSupplier = new String[]{"adw", "@da"};
        }*/
        // ListAdapter too be able to adapt our array in too
        // something that our listview is able to work with
        new getProductsfromserver().execute((htmlParser)new sibaHtmlParser());
        /*ListAdapter theAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, electronicSupplier);
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
        });*/
    }

    public class getProductsfromserver extends AsyncTask<htmlParser, Void, Product[]> {

        @Override
        protected Product[] doInBackground(htmlParser... parsers) {
            List<Product> productList = parsers[0].getProducts();
            System.out.println(productList.toString() + "00000000000000000000000000");
            Product[] productArray = new Product[productList.size()];
            return productList.toArray(productArray);
        }

        @Override
        protected void onPostExecute( Product[] products) {
            ListAdapter theAdapter = new productsAdapter(getBaseContext(), products);
            ListView theListView = (ListView) findViewById(R.id.theListView);
            theListView.setAdapter(theAdapter);
        }
    }


    /**
     * ????????? vad gör den här ????
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Main activity bar switch.
     * @param item
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        // int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
        //  return true;
        //}
        switch (item.getItemId()) {
            case R.id.action_update:
                Toast toast = Toast.makeText(getBaseContext(), "Version: 0.2-alpha", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP|Gravity.RIGHT, 5, 10);
                toast.show();
                return true;
            case R.id.action_city:
                openCity();
                return true;
            case R.id.action_about:
                openAbout();
                return true;
            case R.id.action_settings:
                openSettings();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * Main activity bar list actions.
     */

    //open city
    private void openCity() {
    }

    //open About
    private void openAbout() {
        new AlertDialog.Builder(this)
        .setTitle("Om oss")
        .setMessage("elektroniknu.wordpress.com")
        .setNeutralButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();
    }

    //open settings
    private void openSettings() {
    }
}
