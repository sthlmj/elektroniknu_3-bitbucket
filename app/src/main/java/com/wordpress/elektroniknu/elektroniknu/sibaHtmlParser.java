package com.wordpress.elektroniknu.elektroniknu;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class sibaHtmlParser implements HtmlParser {

    //PROPERTIES OF PARSER
    private List<Product> sibaProductList;
    private Product[] sibaProductArray;

    //CONSTRUCTOR OF PARSER
    public sibaHtmlParser() {}

    //GET PRODUCTS FROM LIST OF SIBA
    public List<Product> getProducts(){
        return sibaProductList;
    }
    //GET PRODUCTS FROM ARRAY OF SIBA
    public Product[] getProductArray(){
        return sibaProductArray;
    }

    //STARTS FETCHING HTML OF SITE
    public void startParser(){
        Document Doc = null;
        //TRY TO GET HTML CODE WITH URL
        try{
            Doc = Jsoup.connect("http://www.siba.se/aktuella-kampanjer/veckans-erbjudande").get();  // HTML file from their website
        }catch(IOException ex){ // Catch exception
            Logger.getLogger(sibaHtmlParser.class.getName()).log(Level.SEVERE, null, ex);           // Print out at log
        }
        //IF HTML CODE FOUND, START TO FETCH ALL PRODUCTS
        if(Doc != null) {
            //SELECT PRODUCT SERIAL NUMBER
            org.jsoup.select.Elements links = Doc.select("div.info h2 a span");
            //CREATE AND FILL A LIST WITH PRODUCT OBJECTS
            sibaProductArray = new Product[links.size()];
            for (int i = 0; i < links.size(); i++) {
                sibaProductArray[i] = new Product("Siba");
            }
            int i = 0;                                                                              //SERIAL NUMBER
            for (Element e : links) {
                sibaProductArray[i].setSerialNumber(e.ownText());
                i++;
            }
            links = Doc.select("div.info h2 a ");                                                   //URL AND TITLE
            i = 0;
            for (Element e : links) {
                sibaProductArray[i].setUrl(e.attr("abs:href"));
                sibaProductArray[i].setProductName(e.ownText());
                i++;
            }
            links = Doc.select("div.product-box-price");                                            //PRICE
            i = 0;
            for (Element e : links) {
                sibaProductArray[i].setProductPrice(e.ownText().replace(" ", ""));
                i++;
            }
            links = Doc.select("img.js-responsive-image");                                          //IMAGE URL
            i = 0;
            for (Element e : links) {
                sibaProductArray[i].setProductImageUrl(e.attr("data-src").replace("&amp", "&") + "&width=1000");
                i++;
            }

            //SPLIT DESCRIPTION INTO 3 ROWS
            links = Doc.select("div.description li");                                               //DESCRIPTION
            i = 0;
            int number = 0;
            String[] description = new String[3];
            for (Element e : links) {
                description[number] = e.text();
                if (number == 2) {
                    number = 0;
                    sibaProductArray[i].setProductDescription(description);
                    i++;
                    description = new String[3];
                } else {
                    number++;
                }
            }

            setSibaProductsCategoryName.setProductsCategoryName(sibaProductArray);
            sibaProductList = Arrays.asList(sibaProductArray);
        }
    }
}
