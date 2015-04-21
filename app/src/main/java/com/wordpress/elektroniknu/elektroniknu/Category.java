package com.wordpress.elektroniknu.elektroniknu;

/**
 * Created by Aylin on 2015-04-21.
 */
public class Category {
    private String categoryUrl;
    private String categoryName;
    private Product[] productList;

    public void setCategoryUrl(String categoryUrl){
        this.categoryUrl = categoryUrl;
    }
    public void setCategoryName(String categoryName){
        this.categoryName = categoryName;
    }
    public void addProduct(Product product, int i){
        this.productList[i] = product;
    }

    public Category(){}
}
