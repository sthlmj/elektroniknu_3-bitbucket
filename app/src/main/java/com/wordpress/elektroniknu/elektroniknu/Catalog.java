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
