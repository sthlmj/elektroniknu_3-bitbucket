package com.wordpress.elektroniknu.elektroniknu;

import java.util.List;

/**
 * Created by Aylin on 2015-04-21.
 */
public class Category {
    private String categoryName;
    private List<Product> productList;

    public void setCategoryName(String categoryName){
        this.categoryName = categoryName;
    }
    public void addProduct(Product product){
        productList.add(product);
    }

    public Category(){}
}
