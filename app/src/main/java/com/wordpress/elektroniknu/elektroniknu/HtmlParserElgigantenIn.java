package com.wordpress.elektroniknu.elektroniknu;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class HtmlParserElgigantenIn {

    public static void main(String[] args){

    }

    public static Product[] getProducts(){
        Document Doc = null;
        try{
            String[] listofCategories = HtmlParserElgigantenOut.getCatergoryList();
            for(int i = 0; i < HtmlParserElgigantenOut.getCatergoryList().length; i++){

            }
            Doc = Jsoup.connect("http://www.siba.se/aktuella-kampanjer/veckans-erbjudande").get(); // HTML file from their website
        }catch(IOException ex){ // Catch exception
            Logger.getLogger(HtmlParserElgigantenIn.class.getName()).log(Level.SEVERE, null, ex); // Print out at log
        }
        if(Doc != null) {
            org.jsoup.select.Elements links = Doc.select("div.info h2 a");
            Product[] Products = new Product[links.size()];
            for(int i = 0; i < links.size(); i++){
                Products[i] = new Product();
            }
            int i = 0;
            for(Element e: links){
                Products[i].setUrl(e.attr("abs:href"));
                Products[i].setProductName(e.ownText());
                i++;
            }
            links = Doc.select("div.product-box-price");
            i = 0;
            for(Element e: links){
                Products[i].setProductPrice(e.ownText().replace(" ", ""));
                System.out.println(e.ownText().replace(" ", ""));
            }
            return Products;
        }else{
            return null;
        }
    }

    /*public String getPrice(){
        if(Doc != null){
            org.jsoup.select.Elements links = Doc.select("div.info"); // Select tag from HTML text
            for(Element e: links){
                System.out.println(e.attr("div.product-boxprice"));
            }
            return links.toString();
        }else{
            return null;
        }
    }*/
}
