package com.wordpress.elektroniknu.elektroniknu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.util.concurrent.ExecutionException;


public class MainActivity extends ActionBarActivity {

    //CREATE NEW CATALOG
     Catalog catalog = new Catalog();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the first screen the user should view
        setContentView(R.layout.activity_main);

        // Data that I want too put in my ListView
        // Array of Strings
        String[] electronicSupplier;
        try {
            // Get how many lines the resource file should be
            int lines = TextFileHandler.getLines(getResources());

            // Create an new array with the size of lines
            electronicSupplier = new String[lines];

            // Put store names in the array
            // with for loop
            for(int i=0; i<lines; i++){
                electronicSupplier[i] = TextFileHandler.getStoreName(i+1, getResources());

            }
        // Catch IOException put dummycode in string
        } catch (IOException e) {
            electronicSupplier = new String[]{"adw", "@da"};
        }

        // ListAdapter too be able to adapt our array in too
        // something that our listview is able to work with
        ListAdapter theAdapter = new storesAdapter(this,electronicSupplier);
        ListView theListView = (ListView) findViewById(R.id.theListView);
        theListView.setAdapter(theAdapter);

        // Set Actionbar Title
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.titlebar);

        // Set Title ("Produkter"/"Butiker"/"Kategori")
        TextView titleTextView = (TextView) findViewById(R.id.titleTextView);
        titleTextView.setText("Butiker");

        // Create Parser Object of stores
        sibaHtmlParser sibaParser = new sibaHtmlParser();
        elgigantenHtmlParser elgigantenParser = new elgigantenHtmlParser();
        try {
            catalog.sortProducts(new getProductsfromserver().execute((HtmlParser) elgigantenParser).get()); //start new Thread which will parse and put in catalog
            catalog.sortProducts(new getProductsfromserver().execute((HtmlParser) sibaParser).get());       //start new Thread which will parse and put in catalog
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //ONCLICK LISTS ON OUR LISTVIEW
        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                TextView titleTextView = (TextView) findViewById(R.id.titleTextView);
                if(titleTextView.getText().equals("Butiker")) {
                    // because TextFileHandler starts at one we set
                    // our index too 1 too
                    int val = i + 1;

                    // This intent it used too start another activity
                    // The first parameter is the context of the intent
                    // The second parameter is the activity the intent should start
                    Intent intent = new Intent(MainActivity.this, StorePdfActivity.class);

                    // Add extra data to the intent
                    intent.putExtra("key", val);

                    // Starts the other Activity
                    MainActivity.this.startActivity(intent);
                }else{ //if on "Produktvy", create new Intent with Category Objects
                    Intent intent = new Intent(MainActivity.this, ProductActivity.class);
                    intent.putExtra("Category", (java.io.Serializable) catalog.getCategories(i));
                    MainActivity.this.startActivity(intent);
                }
            }
        });
    }

    //WHEN CLICK ON ARROW BUTTON ON TITLE
    public void onPreviosImageButtonClick(View v){
        TextView titleTextView = (TextView) findViewById(R.id.titleTextView);

        //if title is "Butiker", open corresponding listView
        if(titleTextView.getText().equals("Butiker")){
            Category[] categories = catalog.getCategories();
            String[] categoriesName = new String[categories.length];
            int i = 0;
            for(Category c: categories){
                categoriesName[i] = c.getCategoryName();
                i++;
            }

            //Use Adapter to define the design of the listView
            ListAdapter theAdapter = new storesAdapter(this,categoriesName);
            ListView theListView = (ListView) findViewById(R.id.theListView);
            theListView.setAdapter(theAdapter);

            //DON'T TOUCH
            getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            getSupportActionBar().setCustomView(R.layout.titlebar);
            titleTextView.setText("Produkter");
        }else{
            String[] electronicSupplier;
            try {
                // Get how many lines the resource file should be
                int lines = TextFileHandler.getLines(getResources());

                // Create an new array with the size of lines
                electronicSupplier = new String[lines];

                // Put store names in the array
                // with for loop
                for(int i=0; i<lines; i++){
                    electronicSupplier[i] = TextFileHandler.getStoreName(i+1, getResources());

                }
                // Catch IOException put dummycode in string
            } catch (IOException e) {
                electronicSupplier = new String[]{"adw", "@da"};
            }
            ListAdapter theAdapter = new storesAdapter(this,electronicSupplier);
            ListView theListView = (ListView) findViewById(R.id.theListView);

            theListView.setAdapter(theAdapter);
            getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            getSupportActionBar().setCustomView(R.layout.titlebar);
            titleTextView.setText("Butiker");
        }
    }

    //EXACTLY AS onPreviousImageButtonClick()
    public void onNextImageButtonClick(View v){
        TextView titleTextView = (TextView) findViewById(R.id.titleTextView);
        if(titleTextView.getText().equals("Butiker")){
            Category[] categories = catalog.getCategories();
            String[] categoriesName = new String[categories.length];
            int i = 0;
            for(Category c: categories){
                categoriesName[i] = c.getCategoryName();
                i++;
            }
            ListAdapter theAdapter = new storesAdapter(this,categoriesName);
            ListView theListView = (ListView) findViewById(R.id.theListView);

            theListView.setAdapter(theAdapter);
            getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            getSupportActionBar().setCustomView(R.layout.titlebar);
            titleTextView.setText("Produkter");
        }else{
            String[] electronicSupplier;
            try {
                // Get how many lines the resource file should be
                int lines = TextFileHandler.getLines(getResources());

                // Create an new array with the size of lines
                electronicSupplier = new String[lines];

                // Put store names in the array
                // with for loop
                for(int i=0; i<lines; i++){
                    electronicSupplier[i] = TextFileHandler.getStoreName(i+1, getResources());

                }
                // Catch IOException put dummycode in string
            } catch (IOException e) {
                electronicSupplier = new String[]{"adw", "@da"};
            }
            ListAdapter theAdapter = new storesAdapter(this,electronicSupplier);
            ListView theListView = (ListView) findViewById(R.id.theListView);

            theListView.setAdapter(theAdapter);
            getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            getSupportActionBar().setCustomView(R.layout.titlebar);
            titleTextView.setText("Butiker");
        }
    }

    public class getProductsfromserver extends AsyncTask<HtmlParser, Void, Product[]> {
        @Override
        //Starts new parsers with thread
        protected Product[] doInBackground(HtmlParser... parsers) {
            parsers[0].startParser();
            return parsers[0].getProductArray();
        }

        @Override
        //SHOW A TOAST WHEN PARSING IS DONE
        protected void onPostExecute(Product[] products) {
//            Toast.makeText(getBaseContext(), "Klar", Toast.LENGTH_LONG).show();
        }
    }

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


            case R.id.action_about:
                openAbout();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * Main activity bar list actions.
     */



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


}
