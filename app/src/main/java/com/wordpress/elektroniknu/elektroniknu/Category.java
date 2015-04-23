package com.wordpress.elektroniknu.elektroniknu;

import java.util.LinkedList;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void addProduct(Product product){
        productList.add(product);
        product.setCategoryName(categoryName);
    }

    public List<Product> getProductList() {
        return productList;
    }

    public Category(){
        this.productList = new LinkedList<Product>();
    }
}
