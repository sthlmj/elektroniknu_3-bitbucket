package com.wordpress.elektroniknu.elektroniknu;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Toast;

import java.io.IOException;


public class StorePdfActivity extends ActionBarActivity {

    int position;
    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        position = intent.getIntExtra("key", -1);
        
        String URL;

        if(position >= 0){
            try {
                URL = TextFileHandler.getURL(position, getResources());
                webview = new WebView(this);
                setContentView(webview);
                webview.loadUrl(URL);
            } catch (IOException e) {
                Toast.makeText(this, "Something was Wrong!", Toast.LENGTH_LONG).show();
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_store_pdf, menu);
        return true;
    }

  /*  @Override
   /* public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
