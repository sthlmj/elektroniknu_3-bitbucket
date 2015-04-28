package com.wordpress.elektroniknu.elektroniknu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

class productsAdapter extends ArrayAdapter<Product>{

    //CONSTRUCTOR
    productsAdapter(Context context, Product[] products) {
        super(context, R.layout.product_row, products);
    }

    //ADAPT PROPERTIES OF EVERY VIEW OBJECT
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater myInflater = LayoutInflater.from(getContext());
        View productView = myInflater.inflate(R.layout.product_row, parent, false);

        Product product = getItem(position);

        //LINK TO EVERY VIEW OBJECT IN XML
        WebView productImageView = (WebView) productView.findViewById(R.id.produktWebView);
        TextView productNameTextView = (TextView) productView.findViewById(R.id.produktNameTextView);
        TextView storeTextView = (TextView) productView.findViewById(R.id.storeTextView);
        TextView priceTextView = (TextView) productView.findViewById(R.id.pricebotton);
        TextView description1TextView = (TextView) productView.findViewById(R.id.description1TextView);
        TextView description2TextView = (TextView) productView.findViewById(R.id.description2TextView);
        TextView description3TextView = (TextView) productView.findViewById(R.id.description3TextView);

        //ADAPTS THE PROPERTIES ON WHOLE PRODUCT ROW
        String html = "<html><body><img src=\"" + product.getProductImageUrl() + "\" width=\"100%\" height=\"100%\"\"/></body></html>";
        productImageView.loadData(html, "text/html", null);
        productNameTextView.setText(product.getProductName());
        storeTextView.setText(product.getStoreName());
        priceTextView.setText(product.getProductPrice());
        try {
            description1TextView.setText(product.getProductDescription()[0]);
            try{
                description2TextView.setText(product.getProductDescription()[1]);
                try{
                    description3TextView.setText(product.getProductDescription()[2]);
                }catch (NullPointerException e){}
            }catch(NullPointerException e){
            }
        }catch(NullPointerException e){
            description1TextView.setText("Ingen beskrivning");
        }

        return productView;
    }

    /*private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }*/
}
