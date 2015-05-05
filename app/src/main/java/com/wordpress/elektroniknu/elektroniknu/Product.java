package com.wordpress.elektroniknu.elektroniknu;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;

//implements Serializable to be able to send objects between Activities
public class Product implements Serializable{

    //PROPERTIES OF PRODUCTS
    private String url;
    private String productName;
    private String productPrice;
    private String productImageUrl;
    private String[] productDescription;
    private String storeName;
    private String categoryName;
    private String serialNumber;
    private productsAdapter adapter;
    private Bitmap Image;

    public void setAdapter(productsAdapter adapter) {
        this.adapter = adapter;
    }

    public void setImage(Bitmap image) {
        Image = image;
    }

    public productsAdapter getAdapter() {
        return adapter;
    }

    public Bitmap getImage() {
        return Image;
    }

    //CONSTRUCTOR
    public Product(){}
    //CONSTRUCTOR - storeName as input
    public Product(String storeName) {
        this.storeName = storeName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public String getCategoryName() {
        return categoryName;

    }
    public void setStoreName(String store) {
        this.storeName = store;
    }
    public String getStoreName() {
        return storeName;
    }
    public void setProductDescription(String[] productDescription) {
        this.productDescription = productDescription;
    }
    public String[] getProductDescription() {
        return productDescription;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }
    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }
    public String getUrl() {
        return url;
    }
    public String getProductName() {
        return productName;
    }
    public String getProductPrice() {
        return productPrice;
    }
    public String getProductImageUrl() {
        return productImageUrl;
    }
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    public String getSerialNumber() {
        return serialNumber;
    }
    public void loadImage(productsAdapter adapter){
        this.adapter = adapter;
        if(productImageUrl != null){
            new DownloadImage().execute(productImageUrl);
        }
    }

    //toString method for debug
    public String toString(){
        String description = "";
        for(String s: this.getProductDescription()){
            description += s + "\n";
        }
        String wholeDescription = productName + " " + "has URL " + url + "\n" +
                "and price is " + productPrice +"\n"
                + "and imageURl is " + productImageUrl + "\n" + description;

        return wholeDescription;
    }

    private class DownloadImage extends AsyncTask<String, Void, Bitmap>{
        @Override
        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                URL url = new URL(urldisplay);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 4;
                mIcon11 = BitmapFactory.decodeStream(input, null,options);
                input.close();
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if(bitmap != null){
                Image = bitmap;
                if(adapter != null){
                    adapter.notifyDataSetChanged();
                }
            }
        }

        public int calculateInSampleSize(
                BitmapFactory.Options options, int reqWidth, int reqHeight) {
            // Raw height and width of image
            final int height = options.outHeight;
            final int width = options.outWidth;

            int stretch_width = Math.round((float)width / (float)reqWidth);
            int stretch_height = Math.round((float)height / (float)reqHeight);

            if (stretch_width <= stretch_height)
                return stretch_height;
            else
                return stretch_width;
        }
    }
}
