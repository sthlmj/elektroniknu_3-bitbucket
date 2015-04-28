package com.wordpress.elektroniknu.elektroniknu;

import java.util.List;

//INTERFACE SETS RULES FOR ALL HTMLPARSERS
interface HtmlParser {
    public List<Product> getProducts();
    public Product[] getProductArray();
    public void startParser();
    public String getParserName();
}
