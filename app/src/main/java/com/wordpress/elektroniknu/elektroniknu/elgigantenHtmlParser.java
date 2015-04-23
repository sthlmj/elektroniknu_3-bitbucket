package com.wordpress.elektroniknu.elektroniknu;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

//htmlparser for the html under categories
public class elgigantenHtmlParser implements HtmlParser {
    //PROPERTIES OF PARSER
    private List<Product> products;

    //CONSTRUCTOR OF PARSER
    public elgigantenHtmlParser(){}
    //GET PRODUCTS WITH LIST
    public List<Product> getProducts(){
        return products;
    }
    //START FETCHING HTML OF SITE
    public void startParser(){
        String[] listofCategories = getCategoryList();                                              //get list of Elgiganten's categories
        List<Document> Docs = new ArrayList<Document>();                                            //for record list of documents
        List<Product> listOfAllProducts = new ArrayList<Product>();                                 //for save all the products

                                                                                                    //For every category we have..
        for (int i = 0; i < getCategoryList().length; i++) {                                         //..get the products inside.
            try {
                Docs.add(Jsoup.connect(listofCategories[i]).get());                                 //HTML file from their website
            } catch (IOException ex) { // Catch exception
                Logger.getLogger(elgigantenHtmlParser.class.getName()).log(Level.SEVERE, null, ex); // Print out at log
            }
        }
        for(int j = 0; j < Docs.size(); j++)
        {
            listOfAllProducts.addAll(getProducts(Docs.get(j))); //get products from every category and combine them into one list
        }
        products = listOfAllProducts;
    }



//htmlparser of the categories from the site
    private static String[] getCategoryList() {
        Document Doc = null;
        try {
            Doc = Jsoup.connect("http://www.elgiganten.se/cms/veckans-annons/veckans-erbjudanden").get(); // HTML file from their website
        } catch (IOException ex) { // Catch exception
            Logger.getLogger(elgigantenHtmlParser.class.getName()).log(Level.SEVERE, null, ex); // Print out at log
        }
        if (Doc != null)
        {
            org.jsoup.select.Elements links = Doc.select("div.article-text.M-1-1.S-1-1 h2 a");
            String[] categoryList = new String[links.size()];
            int i = 0;
            for (Element e : links)
            {
                categoryList[i] = e.attr("abs:href");
                i++;
            }
            return categoryList;
        }
        else
        {
            return null;
        }
    }



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
            Elements img = Doc.select("div.col.col-mini-product.fixed div.mini-product div.mini-left-content div.visuals a.product-image-link img.product-image");
            i = 0;
            for (Element e : img) {
                Products[i].setProductImageUrl(e.attr("src").replace("&#47;", "/").replace("?$prod_tnbg$", ""));
                i++;
            }
            //get descriptions
            Elements ul = Doc.select("div.col.col-mini-product.fixed div.mini-product div.more-info ul.specs");
            for (int ulnumber = 0; ulnumber < ul.size(); ulnumber++) {
                Elements lis = ul.get(ulnumber).children();
                String[] descriptionForOne = new String[3];
                int j = 0;
                for (Element li : lis) {
                    descriptionForOne[j] = li.text();
                    j++;
                }
                Products[ulnumber].setProductDescription(descriptionForOne);
            }

            //get categoryNames
                /*Elements categoryNames = Doc.select("ol.breadcrumbs.S-1-1 li");
                String Name = categoryNames.get(1).text().replace("Veckans erbjudanden ","");*/

            Element categoryNames = Doc.select("title").first();
            String Name = categoryNames.text().replace("Veckans erbjudanden ", "");


            //set categorys name to each product
            for (int ulnumber = 0; ulnumber < img.size(); ulnumber++) {
                Products[ulnumber].setCategoryName(Name);
            }
            return Arrays.asList(Products);
        } else {
            return null;
        }
    }
}