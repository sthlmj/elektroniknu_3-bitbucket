
package com.wordpress.elektroniknu.elektroniknu;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;


public class ProductActivity extends ActionBarActivity {

    productsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        Intent intent = this.getIntent();           //Create new Intent for receiving category
        Category category = (Category) intent.getSerializableExtra("Category");     //receive category

        //new setAdapter().execute(category);         //execute new Thread for the ListView in Category
        adapter = new productsAdapter(ProductActivity.this, category.getProductArray());
        ListView listView = (ListView) findViewById(R.id.ProductListView);
        listView.setAdapter(adapter);

        new setPicture().execute(category.getProductArray());

        if(category.getProductList().size() == 0)
        {
            Toast.makeText(getBaseContext(), "inga erbjudanden", Toast.LENGTH_SHORT).show();

        }
        getSupportActionBar().setTitle(category.getCategoryName());

    }


    //DEFINE THE THREAD
    public class setPicture extends AsyncTask<Product, Void, Void> {

        @Override
        protected Void doInBackground(Product... products) {
            for(Product p : products){
                p.loadImage(adapter);
            }
            return null;
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
