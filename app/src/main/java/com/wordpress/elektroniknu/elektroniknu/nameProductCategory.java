package com.wordpress.elektroniknu.elektroniknu;

import java.util.List;

/**
 * Created by chenz_000 on 2015-04-22.
 */
public class nameProductCategory {
    public static void main(String[] args){
        sibaHtmlParser parser = new sibaHtmlParser();
        parser.startFetch();
       name(parser.getProducts());
    }
    public static List<Product> name(List<Product> products){
        for(Product p: products){
            switch (p.getProductDescription().toString()){
                default: System.out.println(p.getProductDescription().toString()) ;
            }
        }
        return products;
    }
}
