
package com.wordpress.elektroniknu.elektroniknu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class ProductActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        Intent intent = this.getIntent();           //Create new Intent for receiving category
        Category category = (Category) intent.getSerializableExtra("Category");     //receive category

        new setAdapter().execute(category);         //execute new Thread for the ListView in Category
        if(category.getProductList().size() == 0)
        {
            Toast.makeText(getBaseContext(), "inga erbjudanden", Toast.LENGTH_SHORT).show();

        }
        ActionBar myaction = getSupportActionBar();
        myaction.setTitle(category.getCategoryName());
        myaction.setDisplayShowHomeEnabled(true);
        myaction.setHomeButtonEnabled(true);
        myaction.setDisplayHomeAsUpEnabled(true);
    }


    //DEFINE THE THREAD
    public class setAdapter extends AsyncTask<Category, Void, String> {

        @Override
        protected String doInBackground(Category... category) {
            ListAdapter theAdapter = new productsAdapter(ProductActivity.this, category[0].getProductArray());  //Get all products in Category
            ListView theListView = (ListView) findViewById(R.id.ProductListView);
            theListView.setAdapter(theAdapter);
            return(category[0].getCategoryName());
        }

        @Override
        protected void onPostExecute(String string) {
            //Toast when thread is executed
            Toast.makeText(getBaseContext(), string, Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_product, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                openAbout();
            case android.R.id.home:
                Intent homeIntent = new Intent(this, MainActivity.class);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void openAbout()
    {

        final SpannableString stMyWeb = new SpannableString("elektroniknu.wordpress.com");
        Linkify.addLinks(stMyWeb, Linkify.ALL);

        final AlertDialog aboutDialog = new AlertDialog.Builder(ProductActivity.this)
                .setTitle("Om oss")
                .setMessage(stMyWeb)
                .setNeutralButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                    }})
                .create();

        aboutDialog.show();

        ((TextView)aboutDialog.findViewById(android.R.id.message))
                .setMovementMethod(LinkMovementMethod.getInstance());

    }
}
