package com.wordpress.elektroniknu.elektroniknu;

import java.io.Serializable;
import java.util.List;

public class Catalog implements Serializable{
    //START CATALOG ACTIVITY WITH A MAIN CLASS
    public static void main(String[] args){
        Catalog catalog = new Catalog();                    //create Object Catalog
        long startTime = System.currentTimeMillis();        //start time-stamp
        sibaHtmlParser sibaparser = new sibaHtmlParser();   //create Object sibaHtmlParser
        sibaparser.startParser();                           //start Siba's parser
        HtmlParserElgigantenIn elgigantenparser = new HtmlParserElgigantenIn(); //create Object elgigantenHtmlParser
        elgigantenparser.startParser();                     //start Elgiganten's parser
        long endTime = System.currentTimeMillis();          //end time-stamp
        System.out.println("That took " + (endTime - startTime) + " milliseconds");

        Product[] productArray = sibaparser.getProductArray();        //get productList of Siba
        catalog.sortProducts(productArray);                           //sort Siba's products in categories
        List<Product> productList = elgigantenparser.getProducts();   //get productList of Elgiganten
        catalog.sortProducts(productList.toArray(new Product[productList.size()])); //sort Elgiganten's products in categories

        //print categories with containing products
        for (Category c: catalog.getCategories()){
            System.out.println(c.getCategoryName());
            for (Product p: c.getProductList()){
                System.out.println(p.getProductName());
            }
            System.out.println("\n ");
        }
    }

    //PROPERTIES OF OUR CATALOG
    private Category[] categories;

    //CATALOG CONSTRUCTOR - defines every category name in a catalog, needs no input
    public Catalog() {
        categories = new Category[10];                         //Catalog contains 10 categories

        //Creates 1 Category Object for every index in Catalog
        for(int i = 0; i < categories.length; i++){
            categories[i] = new Category();
        }

        //Sets name of categories
        categories[0].setCategoryName("Skönhet");
        categories[1].setCategoryName("Vitvaror");
        categories[2].setCategoryName("Ljud");
        categories[3].setCategoryName("Dator och surfplattor");
        categories[4].setCategoryName("Bild");
        categories[5].setCategoryName("Mobil och tillbehör");
        categories[6].setCategoryName("Spel och spelkonsol");
        categories[7].setCategoryName("Tv och tillbehör");
        categories[8].setCategoryName("Hemmet");
        categories[9].setCategoryName("Annat");
    }

    public Category[] getCategories(){
        return categories;
    }

    public Category getCategories(int i){
        return categories[i];
    }

    /*
    Categories:
    0:Skönhet
    1:Vitvaror
    2:Ljud
    3:Dator och surfplattor
    4:Bild
    5:Mobil och tillbehör
    6:Spel och spelkonsol
    7:Tv och tillbehör
    8:Hemmet
    9:Annat
    */

    //Sorts products into categories
    public void sortProducts(Product[] products){
        String name;
        for(Product p: products){
            name = p.getCategoryName();
            try {                                       //try match a products category name with our categories
                if (contain(name, "Spel")) {
                    categories[6].addProduct(p);
                } else if (contain(name, "Vitvaror")) {
                    categories[1].addProduct(p);
                } else if (contain(name, "Tv")) {
                    categories[7].addProduct(p);
                } else if (contain(name, "Hem")) {
                    categories[8].addProduct(p);
                }else if (contain(name, "Dator")) {
                    categories[3].addProduct(p);
                } else if (contain(name, "Mobil")) {
                    categories[5].addProduct(p);
                } else if (contain(name, "Surfplatta")) {
                    categories[3].addProduct(p);
                } else if (contain(name, "Ljud")) {
                    categories[2].addProduct(p);
                } else if (contain(name, "Hälsa")) {
                    categories[0].addProduct(p);
                } else if (contain(name, "Personvård")) {
                        categories[0].addProduct(p);
                } else if (contain(name, "Foto")) {
                    categories[4].addProduct(p);
                } else if (contain(name, "Bild")) {
                    categories[4].addProduct(p);
                } else {
                    categories[9].addProduct(p);
                }
            }catch (NullPointerException e){            //catch a product with no category name
                categories[9].addProduct(p);
            }
        }
    }

    //Changes uppercase letters to lowercase
    private boolean contain(String s1, String s2){
        return s1.toLowerCase().contains(s2.toLowerCase());
    }
}
