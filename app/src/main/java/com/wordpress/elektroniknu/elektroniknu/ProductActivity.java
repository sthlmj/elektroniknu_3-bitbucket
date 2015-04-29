
package com.wordpress.elektroniknu.elektroniknu;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
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
        getSupportActionBar().setTitle(category.getCategoryName());

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
  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_product, menu);
        return true;
    }*/

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
