package com.wordpress.elektroniknu.elektroniknu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenz_000 on 2015-04-22.
 */
public class Catalog {
    public static void main(String[] args){
        /*Product product = new Product();
        product.setCategoryName("spel");
        Product[] products = new Product[1];
        products[0] = product;*/
        long startTime = System.currentTimeMillis();
        sibaHtmlParser parser = new sibaHtmlParser();
        parser.startFetch();
        List<Product> productList = parser.getProducts();
        Catalog catalog = new Catalog(productList.toArray(new Product[productList.size()]));
        long endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");
    }
    Category[] categories;

    public Catalog(Product[] products) {
        categories = new Category[9];
        for(int i = 0; i < categories.length; i++){
            categories[i] = new Category();
        }
        categories[0].setCategoryName("Skönhet");
        categories[1].setCategoryName("Vitvaror");
        categories[2].setCategoryName("Ljud");
        categories[3].setCategoryName("Dator och surfplattor");
        categories[4].setCategoryName("Bild");
        categories[5].setCategoryName("Mobil och tillbehör");
        categories[6].setCategoryName("Spel och spelkonsol");
        categories[7].setCategoryName("Tv och tillbehör");
        categories[8].setCategoryName("Annat");
        sortProducts(products);
    }

    /*
    categories:
    0:Skönhet
    1:Vitvaror
    2:Ljud
    3:Dator och surfplattor
    4:Bild
    5:Mobil och tillbehör
    6:Spel och spelkonsol
    7:Tv och tillbehör
    8:Annat
     */

    private void sortProducts(Product[] products){
        String name;
        for(Product p: products){
            name = p.getCategoryName();
            if(contain(name, "Spel")){
                categories[6].addProduct(p);
            }else if(contain(name, "Tv")){
                categories[7].addProduct(p);
            }else if(contain(name, "Dator")){
                categories[3].addProduct(p);
            }else if(contain(name, "Mobil")){
                categories[5].addProduct(p);
            }else if(contain(name, "Hus")){
                categories[8].addProduct(p);
            }else if(contain(name, "Surfplatta")){
                categories[3].addProduct(p);
            }else if(contain(name, "Ljud")){
                categories[2].addProduct(p);
            }else if(contain(name, "Hälsa")){
                categories[0].addProduct(p);
            }else if(contain(name, "Foto")) {
                categories[4].addProduct(p);
            }else if(contain(name, "Vitvaror")){
                categories[1].addProduct(p);
            }else if(contain(name, "Bild")){
                categories[4].addProduct(p);
            }else{
                categories[8].addProduct(p);
            }
        }
    }

    private boolean contain(String s1, String s2){
        return s1.toUpperCase().contains(s2.toUpperCase());
    }



}
