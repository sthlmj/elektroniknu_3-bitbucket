package com.wordpress.elektroniknu.elektroniknu;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

//htmlparser for the html under categories
public class HtmlParserElgigantenIn {

    public static void main(String[] args) { //main for test
        products();
    }

    public static List<Product> products() {
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
       /*for(int j = 0; j < listOfAllProducts.size(); j++) //for test
        {
            System.out.println(listOfAllProducts.get(j).getProductName());
            System.out.println(listOfAllProducts.get(j).getUrl());
            System.out.println(listOfAllProducts.get(j).getProductImageUrl());
            System.out.println(listOfAllProducts.get(j).getProductPrice());
            System.out.println(listOfAllProducts.get(j).getProductDescription());
            System.out.println("....................................................");
        }*/
        return listOfAllProducts;
    }

}
