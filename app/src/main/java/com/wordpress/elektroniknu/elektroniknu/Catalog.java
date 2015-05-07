package com.wordpress.elektroniknu.elektroniknu;

import java.io.Serializable;
import java.util.List;

public class Catalog implements Serializable{
    //PROPERTIES OF OUR CATALOG
    private Category[] categories;

    //CATALOG CONSTRUCTOR - defines every category name in a catalog, needs no input
    public Catalog() {
        //Catalog contains 10 categories
        categories = new Category[10];

        //Creates 1 Category Object for every index in Catalog
        for(int i = 0; i < categories.length; i++){
            categories[i] = new Category();
        }

        //Sets name of categories
        categories[0].setCategoryName("Dator och surfplattor");
        categories[1].setCategoryName("Mobil och tillbehör");
        categories[2].setCategoryName("Tv och tillbehör");
        categories[3].setCategoryName("Spel och spelkonsol");
        categories[4].setCategoryName("Ljud");
        categories[5].setCategoryName("Bild");
        categories[6].setCategoryName("Vitvaror");
        categories[7].setCategoryName("Hemmet");
        categories[8].setCategoryName("Skönhet");
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
    0:Dator och surfplattor
    1:Mobil och tillbehör
    2:Tv och tillbehör
    3:Spel och spelkonsol
    4:Ljud
    5:Bild
    6:Vitvaror
    7:Hemmet
    8:Skönhet
    9:Annat
    */
    //ADD LIST OF PRODUCTS TO CATALOG
    public void addToCatalog(List<Product> products){
        sortProducts(products.toArray(new Product[products.size()]));
    }
    //ADD ARRAY OF PRODUCTS TO CATALOG
    public void addToCatalog(Product[] products){
        sortProducts(products);
    }

    //Sorts products into categories
    public void sortProducts(Product[] products){
        String name;
        for(Product p: products){
            name = p.getCategoryName();
            try {                                       //try match a products category name with our categories
                if (contain(name, "Spel")) {
                    categories[3].addProduct(p);
                } else if (contain(name, "Vitvaror")) {
                    categories[6].addProduct(p);
                } else if (contain(name, "Tv")) {
                    categories[2].addProduct(p);
                } else if (contain(name, "Hem")) {
                    categories[7].addProduct(p);
                }else if (contain(name, "Dator")) {
                    categories[0].addProduct(p);
                } else if (contain(name, "Mobil")) {
                    categories[1].addProduct(p);
                } else if (contain(name, "Surfplatta")) {
                    categories[0].addProduct(p);
                } else if (contain(name, "Ljud")) {
                    categories[4].addProduct(p);
                } else if (contain(name, "Hälsa")) {
                    categories[8].addProduct(p);
                } else if (contain(name, "Personvård")) {
                    categories[8].addProduct(p);
                } else if (contain(name, "Foto")) {
                    categories[5].addProduct(p);
                } else if (contain(name, "Bild")) {
                    categories[5].addProduct(p);
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
