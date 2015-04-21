package com.wordpress.elektroniknu.elektroniknu;

/**
 * Created by joehulden on 21/04/15.
 */
public class Product {

    private final String url;
    private final String productName;
    private final String productPrice;
    private final String productImageUrl;

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

    public Product(String url, String productName, String productPrice, String productImageUrl) {
        this.url = url;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productImageUrl = productImageUrl;

    }


}
