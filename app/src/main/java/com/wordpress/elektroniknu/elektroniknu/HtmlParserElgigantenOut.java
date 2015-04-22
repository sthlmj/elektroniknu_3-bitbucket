package com.wordpress.elektroniknu.elektroniknu;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HtmlParserElgigantenOut {

    public static void main(String[] args) {
        getCatergoryList();
    }

    public static String[] getCatergoryList() {
        Document Doc = null;
        try {
            Doc = Jsoup.connect("http://www.elgiganten.se/cms/veckans-annons/veckans-erbjudanden").get(); // HTML file from their website
        } catch (IOException ex) { // Catch exception
            Logger.getLogger(HtmlParserElgigantenOut.class.getName()).log(Level.SEVERE, null, ex); // Print out at log
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
           /* for(int j = 0; j < links.size(); j++ )
            {
                System.out.println(categoryList[j]);
            }*/
            return categoryList;
        }
        else
        {
            return null;
        }
    }
}



