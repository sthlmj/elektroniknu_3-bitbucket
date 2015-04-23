package com.wordpress.elektroniknu.elektroniknu;

import java.util.List;
public class Catalog {
    //START CATALOG ACTIVITY WITH A MAIN CLASS
    public static void main(String[] args){
        /*Product product = new Product();
        product.setCategoryName("spel");
        Product[] products = new Product[1];
        products[0] = product;*/
        long startTime = System.currentTimeMillis();
        //sibaHtmlParser parser = new sibaHtmlParser();
        //parser.startFetch();
        HtmlParserElgigantenIn elgigantenparser = new HtmlParserElgigantenIn();
        elgigantenparser.startFetch();
        long endTime = System.currentTimeMillis();

        //List<Product> productList = parser.getProducts();
        Catalog catalog = new Catalog();
        //catalog.sortProducts(productList.toArray(new Product[productList.size()]));
        List <Product> productList = elgigantenparser.getProducts();
        //catalog.sortProducts(productList.toArray(new Product[productList.size()]));
        System.out.println("That took " + (endTime - startTime) + " milliseconds");
        for(Product p : productList){
            System.out.println(p.getCategoryName());
        }

      /*  for (Category c: catalog.getCategories()){
            System.out.println(c.getCategoryName());
            for (Product p: c.getProductList()){
                System.out.println(p.getProductName());
            }
            System.out.println("/n ");
        }*/
    }

    //PROPERTIES OF OUR CATALOG
    private Category[] categories;

    //CATALOG CONSTRUCTOR - defines every category name in a catalog, needs no input
    public Catalog() {
        categories = new Category[9];                         //Catalog contains 9 categories

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
        categories[8].setCategoryName("Annat");
    }

    public Category[] getCategories(){return categories;}

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
    8:Annat
     */

    //Sorts products into categories
    private void sortProducts(Product[] products){
        String name;

        for(Product p: products){
            name = p.getCategoryName();
            try {                                       //try match a products category name with our categories
                if (contain(name, "Spel")) {
                    categories[6].addProduct(p);
                } else if (contain(name, "Tv")) {
                    categories[7].addProduct(p);
                } else if (contain(name, "Dator")) {
                    categories[3].addProduct(p);
                } else if (contain(name, "Mobil")) {
                    categories[5].addProduct(p);
                } else if (contain(name, "Hus")) {
                    categories[8].addProduct(p);
                } else if (contain(name, "Surfplatta")) {
                    categories[3].addProduct(p);
                } else if (contain(name, "Ljud")) {
                    categories[2].addProduct(p);
                } else if (contain(name, "Hälsa")) {
                    categories[0].addProduct(p);
                } else if (contain(name, "Foto")) {
                    categories[4].addProduct(p);
                } else if (contain(name, "Vitvaror")) {
                    categories[1].addProduct(p);
                } else if (contain(name, "Bild")) {
                    categories[4].addProduct(p);
                } else {
                    categories[8].addProduct(p);
                }
            }catch (NullPointerException e){            //catch a product with no category name
                categories[8].addProduct(p);
            }
        }
    }

    //Changes uppercase letters to lowercase
    private boolean contain(String s1, String s2){
        return s1.toLowerCase().contains(s2.toLowerCase());
    }
}
