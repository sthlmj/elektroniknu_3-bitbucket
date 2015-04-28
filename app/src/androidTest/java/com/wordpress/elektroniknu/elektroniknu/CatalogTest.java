package com.wordpress.elektroniknu.elektroniknu;

import junit.framework.TestCase;

/**
 * Created by chenz_000 on 2015-04-28.
 */
public class CatalogTest extends TestCase {
    private Catalog catalog;

    protected void setUp(){
        catalog = new Catalog();
    }

    public void testCatalogNotNull(){
        assertNotNull(catalog);
    }

    public void testAllCategoriesNamesIsCorrect(){
        assertTrue(getCategoriesName());
    }

    public void testSortProductsIntoCatalog(){
        Product product1 = new Product();
        Product product2 = new Product();
        product1.setCategoryName("Mobil och Gps");
        product2.setCategoryName("BlaBlaBla");
        Product[] products = new Product[2];
        products[0] = product1;
        products[1] = product2;
        catalog.sortProducts(products);
        assertTrue(catalog.getCategories(5).getProductArray()[0].equals(product1));
        assertTrue(catalog.getCategories(9).getProductArray()[0].equals(product2));
    }

    public boolean getCategoriesName(){
        if(!catalog.getCategories(0).getCategoryName().equals("Skönhet")){
            return false;
        }else if(!catalog.getCategories(1).getCategoryName().equals("Vitvaror")){
            return false;
        }else if(!catalog.getCategories(2).getCategoryName().equals("Ljud")){
            return false;
        }else if(!catalog.getCategories(3).getCategoryName().equals("Dator och surfplattor")){
            return false;
        }else if(!catalog.getCategories(4).getCategoryName().equals("Bild")){
            return false;
        }else if(!catalog.getCategories(5).getCategoryName().equals("Mobil och tillbehör")){
            return false;
        }else if(!catalog.getCategories(6).getCategoryName().equals("Spel och spelkonsol")){
            return false;
        }else if(!catalog.getCategories(7).getCategoryName().equals("Tv och tillbehör")){
            return false;
        }else if(!catalog.getCategories(8).getCategoryName().equals("Hemmet")){
            return false;
        }else if(!catalog.getCategories(9).getCategoryName().equals("Annat")){
            return false;
        }
    return true;
    }
}
