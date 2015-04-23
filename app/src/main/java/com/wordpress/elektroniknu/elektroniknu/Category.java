package com.wordpress.elektroniknu.elektroniknu;

import java.util.LinkedList;
import java.util.List;

public class Category {

    //PROPERTIES OF A CATEGORY
    private String categoryName;
    private List<Product> productList;

    //CATEGORY CONSTRUCTOR - creates a product list for the category, need no input
    public Category(){
        this.productList = new LinkedList<Product>();
    }

    //ADD A PRODUCT INTO PRODUCT LIST
    public void addProduct(Product product){
        productList.add(product);
        product.setCategoryName(categoryName);
    }

    //SET NAME OF CATEGORY
    public void setCategoryName(String categoryName){
        this.categoryName = categoryName;
    }
    //GET NAME OF CATEGORY
    public String getCategoryName() {
        return categoryName;
    }
    //GET THE LIST OF ALL PRODUCTS
    public List<Product> getProductList() {
        return productList;
    }
}
