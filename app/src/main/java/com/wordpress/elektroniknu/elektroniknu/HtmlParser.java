package com.wordpress.elektroniknu.elektroniknu;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class HtmlParser {

    public static void main(String[] args){
       getProducts();
    }

    public static Product[] getProducts(){
        Document Doc = null;
        try{
            Doc = Jsoup.connect("http://www.siba.se/aktuella-kampanjer/veckans-erbjudande").get(); // HTML file from their website
        }catch(IOException ex){ // Catch exception
            Logger.getLogger(HtmlParser.class.getName()).log(Level.SEVERE, null, ex); // Print out at log
        }
        if(Doc != null) {
            org.jsoup.select.Elements links = Doc.select("div.info h2 a");
            Product[] Products = new Product[links.size()];
            int i = 0;
            for(Element e: links){
                Products[i].setUrl(e.attr("abs:href"));
                i++;
                System.out.println(e.attr("abs:href"));
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
