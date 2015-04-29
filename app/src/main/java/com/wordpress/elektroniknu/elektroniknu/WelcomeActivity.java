package com.wordpress.elektroniknu.elektroniknu;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class WelcomeActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.titlebar);

        HtmlParser[] parsers = new HtmlParser[2];
        sibaHtmlParser sibaHtmlParser = new sibaHtmlParser();
        elgigantenHtmlParser elgigantenHtmlParser = new elgigantenHtmlParser();
        parsers[0] = sibaHtmlParser;
        parsers[1] = elgigantenHtmlParser;
        new getProductsfromserver().execute(parsers);
    }


    public class getProductsfromserver extends AsyncTask<HtmlParser, String, HtmlParser[]> {

        private ProgressBar downloadingProgressBar;
        private TextView downloadingTextView;

        protected void onPreExecute() {
            downloadingProgressBar = (ProgressBar) findViewById(R.id.downloadingProgressBar);
            downloadingTextView = (TextView) findViewById(R.id.downloadingTextView);
            downloadingProgressBar.setProgress(0);
        }

        @Override
        protected HtmlParser[] doInBackground(HtmlParser... parsers) {
            String[] progressOnStart = new String[2];
            String[] progressOnEnd = new String[3];
            int length = parsers.length;

            for(int i = 0; i < length; i++){
                progressOnStart[0] = Integer.toString((int) ((i / (float) length) * 100));
                progressOnStart[1] = parsers[i].getParserName();
                publishProgress(progressOnStart);

                parsers[i].startParser();

                progressOnEnd[0] = Integer.toString((int) ((i+1 / (float) length) * 100));
                progressOnEnd[1] = Integer.toString(parsers[i].getProductArray().length) + " produkter från " + parsers[i].getParserName();
                publishProgress(progressOnEnd);
            }
            return parsers;
        }

        @Override
        protected void onProgressUpdate(String... progress) {
            if(progress.length == 2){
                downloadingProgressBar.setProgress(Integer.parseInt(progress[0]));
                downloadingTextView.setText("Laddar ner produkter från " + progress[1]);
            }else {
                downloadingProgressBar.setProgress(Integer.parseInt(progress[0]));
                Toast.makeText(getBaseContext(), progress[1], Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        //SHOW A TOAST WHEN PARSING IS DONE
        protected void onPostExecute(HtmlParser[] parsers) {
            downloadingTextView.setText("Appen startas nu");

            Catalog catalog = new Catalog();
            for(int i = 0; i < parsers.length; i++){
                catalog.sortProducts(parsers[i].getProductArray());
            }

            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
            intent.putExtra("Catalog", (java.io.Serializable) catalog);
            startActivity(intent);
        }
    }
}
