package com.wordpress.elektroniknu.elektroniknu;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.Arrays;
import java.util.List;


public class HtmlParserForEnCat {
    //get products from one document
        public static List<Product> getProducts(Document Doc) {
            if (Doc != null) {

                Elements links = Doc.select("div.col.col-mini-product.fixed div.mini-product div.mini-left-content div.info span.name a");

                //create products object
                Product[] Products = new Product[links.size()];
                for (int i = 0; i < links.size(); i++) {
                    Products[i] = new Product();
                }
                //get URL and name of products
                int i = 0;
                for (Element e : links) {
                    Products[i].setUrl(e.attr("abs:href"));
                    Products[i].setProductName(e.attr("title"));
                    i++;
                }
                //get price of products
                links = Doc.select("div.col.col-mini-product.fixed div.mini-product div.mini-left-content div.product-price-button span.add-to-basket.normal span");
                i = 0;
                for (Element e : links) {
                    Products[i].setProductPrice(e.ownText());
                    i++;
                }
                //get url of images
                links = Doc.select("div.col.col-mini-product.fixed div.mini-product div.mini-left-content div.visuals a.product-image-link img.product-image");
                i = 0;
                for (Element e : links) {
                    Products[i].setProductImageUrl(e.attr("src").replace("&#47;", "/").replace("?$prod_tnbg$",""));
                    i++;
                }
                //get descriptions
                Elements ul = Doc.select("div.col.col-mini-product.fixed div.mini-product div.more-info ul.specs");
                for(int ulnumber = 0; ulnumber < ul.size(); ulnumber++)
                {
                    Elements lis = ul.get(ulnumber).children();
                    String[] descriptionForOne = new String[3];
                    int j = 0;
                    for(Element li : lis)
                    {
                        descriptionForOne[j] = li.text();
                        j++;
                    }
                    Products[ulnumber].setProductDescription(descriptionForOne);
                }

                //get categoryNames
                /*Elements categoryNames = Doc.select("ol.breadcrumbs.S-1-1 li");
                String Name = categoryNames.get(1).text().replace("Veckans erbjudanden ","");*/

                Element categoryNames = Doc.select("title").first();
                String Name = categoryNames.text();//.replace("Veckans erbjudanden ","");


                //set categorys name to each product
                for(int ulnumber = 0; ulnumber < ul.size(); ulnumber++)
                {
                    Products[ulnumber].setCategoryName(Name);
                }
                return Arrays.asList(Products);
            } else {
                return null;
            }
        }
    }



