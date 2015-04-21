package com.wordpress.elektroniknu.elektroniknu;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class HtmlParserElgigantenIn {

    public static void main(String[] args) {

    }

    public static List<Product> products(){
        Document[] Docs = null;
        List<Product> listOfAllProducts = new ArrayList<Product>();
        String[] listofCategories = HtmlParserElgigantenOut.getCatergoryList();

        for (int i = 0; i < HtmlParserElgigantenOut.getCatergoryList().length; i++) {  //for every catergory we have, get the products inside.
            try {
                Docs[i] = Jsoup.connect(listofCategories[i]).get(); // HTML file from their website
            } catch (IOException ex) { // Catch exception
                Logger.getLogger(HtmlParserElgigantenIn.class.getName()).log(Level.SEVERE, null, ex); // Print out at log
            }

            if (Docs[i] != null) {

                org.jsoup.select.Elements links = Docs[i].select("div.info h2 a");

                Product[] Products = new Product[links.size()];
                for (int j = 0; j < links.size(); i++) {
                    Products[j] = new Product();
                }

                int m = 0;
                for (Element e : links) {
                    Products[m].setUrl(e.attr("abs:href"));
                    Products[m].setProductName(e.ownText());
                    m++;
                }

                links = Docs[i].select("div.eproduct-box-pric");
                m = 0;
                for (Element e : links) {
                    Products[m].setProductPrice(e.ownText().replace(" ", ""));
                    m++;
                }

                links = Docs[i].select("img.js-responsive-image");
                m = 0;
                for (Element e : links) {
                    Products[m].setProductImageUrl(e.attr("data-src").replace("&amp", "&") + "&width=200");
                    m++;
                }

                for (int j = 0; j < links.size(); i++) {
                    listOfAllProducts.add(Products[j]);
                }
            } else {
                return null;
            }
        }
        return listOfAllProducts;
    }
}

