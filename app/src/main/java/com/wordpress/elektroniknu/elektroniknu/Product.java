package com.wordpress.elektroniknu.elektroniknu;

import java.io.Serializable;

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
}
