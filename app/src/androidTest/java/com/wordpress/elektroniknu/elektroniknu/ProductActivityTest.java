package com.wordpress.elektroniknu.elektroniknu;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ActivityUnitTestCase;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ListView;

/**
 * Created by chenz_000 on 2015-04-27.
 */
public class ProductActivityTest extends ActivityUnitTestCase<ProductActivity> {

    private ProductActivity activity;
    private ListView listView;
    private View child;
    private Category category;
    private Product product;
    private Intent intent;

    public ProductActivityTest() {
        super(ProductActivity.class);
    }

    protected void setUp() throws Exception{
        super.setUp();

        ContextThemeWrapper context = new ContextThemeWrapper(getInstrumentation().getTargetContext(), R.style.AppTheme);
        setActivityContext(context);

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
        this.intent = intent;
    }

    public void testActivityNotNull(){
        startActivity(intent, null, null);
        activity = getActivity();
        assertNotNull(activity);
    }

    public void testPerfomItemClick() {
        activity = startActivity(intent, null, null);
        getInstrumentation().callActivityOnStart(activity);
        getInstrumentation().callActivityOnResume(activity);

        ListView listView = (ListView) activity.findViewById(R.id.ProductListView);
        assertNotNull(listView);

        Product[] products = new Product[1];
        products[0] = product;
        productsAdapter myAdapter = new productsAdapter(getInstrumentation().getContext(), products);
        listView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
        assertEquals(1, myAdapter.getCount());
        assertEquals(1, listView.getChildCount());

        View firstItem = listView.getChildAt(0);
        assertNotNull(firstItem);
    }

}
