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
