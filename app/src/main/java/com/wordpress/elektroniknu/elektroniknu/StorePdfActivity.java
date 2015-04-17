package com.wordpress.elektroniknu.elektroniknu;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.io.IOException;


public class StorePdfActivity extends ActionBarActivity {

    int position; // Position of item that user clicked
    WebView webview; // WebView that open the URI
    ShareActionProvider mShareActionProvider;

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
                webview.setWebViewClient(new myWebViewClient());
                webview.getSettings().setJavaScriptEnabled(true); // Set Java Script enable to true
                webview.loadUrl(URL); // Load URL
            } catch (IOException e) { // If we catch IOException
                Toast.makeText(this, "Something was Wrong!", Toast.LENGTH_LONG).show(); // Make a toast that display "Something was wrong!"
                intent = new Intent(this, MainActivity.class); // A new intent to MainActivity
                startActivity(intent); // Start that intent
            }
        }
    }

    public class myWebViewClient extends WebViewClient { // WebViewClient to override URL loading
        public boolean shouldOverrideUrlLoading(WebView view, String url){ // Override URL method
            view.loadUrl(url); // Load that url to view
            return true; // Tell system we handle it
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_store_pdf, menu);

        MenuItem shareItem = menu.findItem(R.id.menu_item_share);

        if(shareItem != null){
            mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        }

        setShareIntent();

        return true;
    }

    private void setShareIntent(){
        if(mShareActionProvider != null){
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, webview.getUrl());

            mShareActionProvider.setShareIntent(shareIntent);
        }
    }
}
