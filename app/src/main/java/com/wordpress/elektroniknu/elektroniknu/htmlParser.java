package com.wordpress.elektroniknu.elektroniknu;

import java.util.List;

interface HtmlParser {
    public List<Product> getProducts();
    public Product[] getProductArray();
    public void startParser();
}
