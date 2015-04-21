package com.wordpress.elektroniknu.elektroniknu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

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


        URL newurl = null;
        try {
            newurl = new URL(product.getProductImageUrl());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Bitmap mIcon_val = null;
        try {
            mIcon_val = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        productImageView.setImageBitmap(mIcon_val);

        productNameTextView.setText(product.getProductName());
        storeTextView.setText(product.getStoreName());
        priceTextView.setText(product.getProductPrice());
        description1TextView.setText(product.getProductDescription()[0]);
        description2TextView.setText(product.getProductDescription()[1]);
        description3TextView.setText(product.getProductDescription()[2]);
        return productView;
    }

}
