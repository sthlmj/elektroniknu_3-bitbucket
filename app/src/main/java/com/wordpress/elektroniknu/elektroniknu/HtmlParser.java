package com.wordpress.elektroniknu.elektroniknu;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class HtmlParser {

    private Document Doc = null;

    public static void main(String[] args){
        HtmlParser myParser = new HtmlParser();
        System.out.println(myParser.getProducts());
    }

    public static void getHtml(){
        try{
            Document Doc = Jsoup.connect("http://www.siba.se/aktuella-kampanjer/veckans-erbjudande").get(); // HTML file from their website
        }catch(IOException ex){ // Catch exception
            Logger.getLogger(HtmlParser.class.getName()).log(Level.SEVERE, null, ex); // Print out at log
        }
    }

    public Product getProducts(){
        if(Doc != null) {
            org.jsoup.select.Elements links = Doc.select("div.product-box-price");
        }else{
            return null;
        }
    }

    public String getPrice(){
        if(Doc != null){
            org.jsoup.select.Elements links = Doc.select("div.info"); // Select tag from HTML text
            for(Element e: links){
                System.out.println(e.attr("div.product-boxprice"));
            }
            return links.toString();
        }else{
            return null;
        }
    }
}
