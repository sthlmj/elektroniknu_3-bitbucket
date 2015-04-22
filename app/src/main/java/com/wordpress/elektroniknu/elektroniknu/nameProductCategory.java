package com.wordpress.elektroniknu.elektroniknu;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


/**
 * Created by chenz_000 on 2015-04-22.
 */
public class nameProductCategory {
    public static void main(String[] args){
        try {
            sibaHtmlParser parser = new sibaHtmlParser();
            parser.startFetch();
            List<Product> productList = parser.getProducts();
            Document doc = Jsoup.connect("http://www.siba.se/searchresults?query=" + productList.get(0).getProductName().replace(" ", "+")).get();
            Element links = doc.select("div.links li").first();
            System.out.println(links.text());
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*sibaHtmlParser parser = new sibaHtmlParser();
        parser.startFetch();
       name(parser.getProducts());*/

    }
    /*public static List<Product> name(List<Product> products){
        for(Product p: products){
            switch (p.getProductDescription().toString()){
                default: System.out.println(p.getProductDescription().toString()) ;
            }
        }
        return products;
    }*/
}
