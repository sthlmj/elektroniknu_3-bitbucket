package com.wordpress.elektroniknu.elektroniknu;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

//htmlparser for the html under categories
public class HtmlParserElgigantenIn implements HtmlParser {
    private List<Product> products;
    private Product[] productArray;

    public HtmlParserElgigantenIn(){

    }

    public List<Product> getProducts(){
        return products;
    }

    public Product[] getProductArray(){
        return productArray;
    }

    public void startParser(){
        fetchProducts();
    }

    public void fetchProducts() {
        String[] listofCategories = HtmlParserElgigantenOut.getCategoryList();// get list of categories
        List<Document> Docs = new ArrayList<Document>();// for record list of documents
        List<Product> listOfAllProducts = new ArrayList<Product>(); // for save all the products

        for (int i = 0; i < HtmlParserElgigantenOut.getCategoryList().length; i++) {  //for every category we have, get the products inside.
            try {
                Docs.add(Jsoup.connect(listofCategories[i]).get()); // HTML file from their website
            } catch (IOException ex) { // Catch exception
                Logger.getLogger(HtmlParserElgigantenIn.class.getName()).log(Level.SEVERE, null, ex); // Print out at log
            }
        }
        for(int j = 0; j < Docs.size(); j++)
        {
            listOfAllProducts.addAll(HtmlParserForEnCat.getProducts(Docs.get(j))); //get products from every category and combine them into one list
        }
        for(Product p: listOfAllProducts){
            p.setStoreName("Elgiganten");
        }
        products = listOfAllProducts;
        productArray = products.toArray(new Product[products.size()]);
    }

}
