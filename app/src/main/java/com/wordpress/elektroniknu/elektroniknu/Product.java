package com.wordpress.elektroniknu.elektroniknu;

/**
 * Skapar objekt med dom variabler
 */
public class Product {

    private String url;
    private String productName;
    private String productPrice;

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

    private String productImageUrl;

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

    //konstruktor
    public Product(String url, String productName, String productPrice, String productImageUrl) {
        this.url = url;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productImageUrl = productImageUrl;

    }

    //konstruktor
    public Product() {

    }


}
