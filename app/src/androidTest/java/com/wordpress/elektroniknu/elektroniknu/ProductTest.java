package com.wordpress.elektroniknu.elektroniknu;

import android.app.Instrumentation;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ActivityUnitTestCase;
import android.test.TouchUtils;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ListView;

/**
 * Created by chenz_000 on 2015-04-27.
 */
public class ProductTest extends ActivityInstrumentationTestCase2<ProductActivity> {

    private ProductActivity activity;
    private Category category;
    private Product product;

    public ProductTest() {
        super(ProductActivity.class);
    }

    protected void setUp() throws Exception{
        super.setUp();

        product = new Product();
        product.setStoreName("Elgiganten");
        product.setProductName("Samsung 50\" 3D Smart LED-TV UE50H6275XXE");
        product.setProductPrice("6990");
        String[] description = new String[3];
        description[0] = "Smart HUB 2014";
        description[1] = "Prisvärd enligt HemmaBio och Watt 2014";
        description[2] = "Energiklass: A+";
        product.setProductDescription(description);
        product.setProductImageUrl("http://tubby.scene7.com/is/image/tubby/UE50H6275XXE?$prod$");
        product.setUrl("http://www.elgiganten.se/product/ljud-bild/tv/UE50H6275XXE/samsung-50-3d-smart-led-tv-ue50h6275xxe");

        category = new Category();
        category.setCategoryName("Bild");
        category.addProduct(product);

        Intent intent = new Intent(getInstrumentation().getTargetContext(),
                MainActivity.class);
        intent.putExtra("Category", (java.io.Serializable) category);
        setActivityIntent(intent);
        activity = getActivity();
    }

    public void testActivityNotNull(){
        assertNotNull(activity);
    }

    public void testPerfomItemClick() {
        ListView listView = (ListView) activity.findViewById(R.id.ProductListView);
        assertNotNull(listView);
        assertEquals(1, listView.getChildCount());

        View firstItem = listView.getChildAt(0);
        assertNotNull(firstItem);
        
    }
}
