package com.wordpress.elektroniknu.elektroniknu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class storesAdapter extends  ArrayAdapter<String>{
    storesAdapter(Context context, String[] electronicSupplier) {
        super(context, R.layout.store_row, electronicSupplier);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater myInflater = LayoutInflater.from(getContext());
        View storeView = myInflater.inflate(R.layout.store_row, parent, false);

        String name = getItem(position);

        TextView productNameTextView = (TextView) storeView.findViewById(R.id.productNameTextView);
        ImageView goToImageView = (ImageView) storeView.findViewById(R.id.goToImageView);

        productNameTextView.setText(name);
        goToImageView.setImageResource(R.drawable.ic_action_next_item);
        storeView.setBackgroundResource(R.drawable.cumstomshape);

        return storeView;
    }


}
