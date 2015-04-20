package com.wordpress.elektroniknu.elektroniknu;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class HtmlParser {

    public static void main(String[] args){
        try{
            Document doc = Jsoup.connect("http://www.siba.se/aktuella-kampanjer/veckans-erbjudande").get();
            org.jsoup.select.Elements links = doc.select("img");
            for(Element e: links){
                System.out.println(e.attr("abs:src"));
            }
        }catch(IOException ex){
            Logger.getLogger(HtmlParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
