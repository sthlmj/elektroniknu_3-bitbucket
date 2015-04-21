package com.wordpress.elektroniknu.elektroniknu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class productsAdapter extends ArrayAdapter<Product>{

    productsAdapter(Context context, Product[] products) {
        super(context, R.layout.product_row, products);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
