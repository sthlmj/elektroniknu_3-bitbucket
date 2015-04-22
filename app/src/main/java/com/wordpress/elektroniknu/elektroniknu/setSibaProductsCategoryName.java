package com.wordpress.elektroniknu.elektroniknu;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class setSibaProductsCategoryName {
    public static void setProductsCategoryName(Product[] products){
        try {
            for(Product p: products){
                Document doc = Jsoup.connect("http://www.siba.se/searchresults?query=" + p.getProductName().replace(" ", "+")).get();
                Element e = doc.select("div.links li").first();
                try {
                    p.setCategoryName(e.text());
                    System.out.println(e.text());
                }catch(NullPointerException exception){
                    p.setCategoryName("Annat");
                    System.out.println("Annat");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
