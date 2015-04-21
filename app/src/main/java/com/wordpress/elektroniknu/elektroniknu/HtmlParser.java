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
        myParser.getPrice();
    }

    public HtmlParser() {
        Doc = getHtml();
    }

    public Document getHtml(){
        try{
            Document doc = Jsoup.connect("http://www.siba.se/aktuella-kampanjer/veckans-erbjudande").get(); // HTML file from their website
            return doc;
        }catch(IOException ex){ // Catch exception
            Logger.getLogger(HtmlParser.class.getName()).log(Level.SEVERE, null, ex); // Print out at log
        }
        return null;
    }

    public String getProducts(){
        if(Doc != null) {
            org.jsoup.select.Elements links = Doc.select("div.info h2"); // Select tag from HTML text
            return links.toString();
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
