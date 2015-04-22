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

    private void sortProducts(Product[] products){
        String name;
        for(Product p: products){
            name = p.getCategoryName();
            if(contain(name, "Spel")){
                p.setCategoryName("Spel och spelkonsol");
            }else if(contain(name, "Tv")){
                p.setCategoryName("Tv och tillbehör");
            }else if(contain(name, "Dator")){
                p.setCategoryName("Dator och surfplattor");
            }else if(contain(name, "Mobil")){
                p.setCategoryName("Mobil och tillbehör");
            }else if(contain(name, "Hus")){
                p.setCategoryName("Annat");
            }else if(contain(name, "Surfplatta")){
                p.setCategoryName("Dator och surfplattor");
            }else if(contain(name, "Ljud")){
                p.setCategoryName("Ljud");
            }else if(contain(name, "Hälsa")){
                p.setCategoryName("Skönhet");
            }else if(contain(name, "Foto")) {
                p.setCategoryName("Bild");
            }else if(contain(name, "Vitvaror")){
                p.setCategoryName("Vitvaror");
            }else if(contain(name, "Bild")){
                p.setCategoryName("Bild");
            }else{
                p.setCategoryName("Annat");
            }
        }
    }

    private boolean contain(String s1, String s2){
        return s1.toUpperCase().contains(s2.toUpperCase());
    }



}
