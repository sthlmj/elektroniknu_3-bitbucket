package com.wordpress.elektroniknu.elektroniknu;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class HtmlParserForEnCat {


        public static void main(String[] args) {
            getProducts();

        }

        public static Product[] getProducts() {
            Document Doc = null;
            try {
                Doc = Jsoup.connect("http://www.elgiganten.se/cms/ljud/veckans-erbjudanden-ljud/?scid=INT_BANNER_BRUNT_ANNONSLJUD").get(); // HTML file from their website
            } catch (IOException ex) { // Catch exception
                Logger.getLogger(sibaHtmlParser.class.getName()).log(Level.SEVERE, null, ex); // Print out at log
            }
            if (Doc != null) {

                org.jsoup.select.Elements links = Doc.select("div.col.col-mini-product.fixed div.mini-product div.mini-left-content div.info span.name a");

                Product[] Products = new Product[links.size()];
                for (int i = 0; i < links.size(); i++) {
                    Products[i] = new Product();
                }

                int i = 0;
                for (Element e : links) {
                    Products[i].setUrl(e.attr("abs:href"));
                    Products[i].setProductName(e.attr("title"));
                    //System.out.println(e.attr("abs:href"));
                    //System.out.println(e.attr("title"));
                    i++;
                }

                links = Doc.select("div.col.col-mini-product.fixed div.mini-product div.mini-left-content div.product-price-button span.add-to-basket.normal span");
                i = 0;
                for (Element e : links) {
                    Products[i].setProductPrice(e.ownText());
                   // System.out.println(e.ownText());
                    i++;
                }

                links = Doc.select("div.col.col-mini-product.fixed div.mini-product div.mini-left-content div.visuals a.product-image-link img.product-image");
                i = 0;
                for (Element e : links) {
                    Products[i].setProductImageUrl(e.attr("src").replace("&#47;", "/").replace("?$prod_tnbg$",""));
                    //System.out.println(Products[i].getProductImageUrl());
                    i++;
                }

                links = Doc.select("??");
                i = 0;
                int number = 0;
                String[] description = new String[3];
                for (Element e : links) {
                    description[number] = e.text();
                    if (number == 2) {
                        number = 0;
                        Products[i].setProductDescription(description);
                        i++;
                        description = new String[3];
                    } else {
                        number++;
                    }
                }
                for(int x = 0; x < 3; x++)
                {
                    System.out.println(description[x]);
                }
                return Products;
            } else {
                return null;
            }
        }
    }



