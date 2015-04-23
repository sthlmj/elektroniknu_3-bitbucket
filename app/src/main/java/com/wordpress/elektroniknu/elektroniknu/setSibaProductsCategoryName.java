package com.wordpress.elektroniknu.elektroniknu;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class setSibaProductsCategoryName {
    public static void setProductsCategoryName(Product[] products) {
        ExecutorService executor = Executors.newFixedThreadPool(8);
        for (Product p : products) {
            MyRunnable newThread = new MyRunnable(p);
            executor.execute(newThread);
        }
        executor.shutdown();
        while(!executor.isTerminated());

    }
    public static class MyRunnable implements Runnable{
        private final Product p;
        MyRunnable(Product p){
            this.p = p;
        }
        public void run() {
            Document doc = null;   //get productName
            try {
                doc = Jsoup.connect("http://www.siba.se/searchresults?query=" + p.getProductName().replace(" ", "+")).get();
                Element e = doc.select("div.links li").first();                             //get products first category

                try {
                    p.setCategoryName(e.text());
                } catch (NullPointerException exception) {
                    p.setCategoryName("Annat");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
