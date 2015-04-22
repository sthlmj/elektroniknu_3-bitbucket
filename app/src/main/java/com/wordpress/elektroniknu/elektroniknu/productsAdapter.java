package com.wordpress.elektroniknu.elektroniknu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.InputStream;


class productsAdapter extends ArrayAdapter<Product>{

    productsAdapter(Context context, Product[] products) {
        super(context, R.layout.product_row, products);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater myInflater = LayoutInflater.from(getContext());
        View productView = myInflater.inflate(R.layout.product_row, parent, false);

        Product product = getItem(position);
        ImageView productImageView = (ImageView) productView.findViewById(R.id.produktImageView);
        TextView productNameTextView = (TextView) productView.findViewById(R.id.produktNameTextView);
        TextView storeTextView = (TextView) productView.findViewById(R.id.storeTextView);
        TextView priceTextView = (TextView) productView.findViewById(R.id.priceTextView);
        TextView description1TextView = (TextView) productView.findViewById(R.id.description1TextView);
        TextView description2TextView = (TextView) productView.findViewById(R.id.description2TextView);
        TextView description3TextView = (TextView) productView.findViewById(R.id.description3TextView);


        new DownloadImageTask(productImageView).execute(product.getProductImageUrl());
        productNameTextView.setText(product.getProductName());
        storeTextView.setText(product.getStoreName());
        priceTextView.setText(product.getProductPrice());
        description1TextView.setText(product.getProductDescription()[0]);
        description2TextView.setText(product.getProductDescription()[1]);
        description3TextView.setText(product.getProductDescription()[2]);

        return productView;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
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
    }

}
