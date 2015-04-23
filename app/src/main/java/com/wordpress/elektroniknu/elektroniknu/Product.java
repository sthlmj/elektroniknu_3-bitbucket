package com.wordpress.elektroniknu.elektroniknu;

/**
 * Skapar objekt med dom variabler
 */
public class Product {

    private String url;
    private String productName;
    private String productPrice;
    private String productImageUrl;
    private String[] productDescription;
    private String storeName;
    private String categoryName;
    private String serialNumber;

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

    public Product(String storeName) {
        this.storeName = storeName;
    }

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

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }
}
