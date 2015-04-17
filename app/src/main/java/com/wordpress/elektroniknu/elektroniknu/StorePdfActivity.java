package com.wordpress.elektroniknu.elektroniknu;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.webkit.WebView;
import android.widget.Toast;

import java.io.IOException;


public class StorePdfActivity extends ActionBarActivity {

    int position; // Position of item that user clicked
    WebView webview; // WebView that open the URI

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_pdf); // Display the activity_store_pdf xml

        Intent intent = getIntent(); // Get Intent from MainActivity
        position = intent.getIntExtra("key", -1); // Get position from that intent and give it default value -1
        
        String URL; // String URL that save the URI

        if(position >= 0){ // Check if position if bigger or equal to 0
            try { // try and catch IOException
                URL = TextFileHandler.getURL(position, getResources()); // Get URI of position line in StorePdf.txt
                webview = (WebView) findViewById(R.id.Pdf); // Find webview by id
                webview.getSettings().setJavaScriptEnabled(true); // Set Java Script enable to true
                webview.loadUrl(URL); // Load URL
            } catch (IOException e) { // If we catch IOException
                Toast.makeText(this, "Something was Wrong!", Toast.LENGTH_LONG).show(); // Make a toast that display "Something was wrong!"
                intent = new Intent(this, MainActivity.class); // A new intent to MainActivity
                startActivity(intent); // Start that intent
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
