package com.wordpress.elektroniknu.elektroniknu;

import android.app.Instrumentation;
import android.content.Intent;
import android.test.TouchUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by chenz_000 on 2015-04-27.
 */
public class MainActivityTest extends
        android.test.ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity activity;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Catalog catalog = new Catalog();
        Intent intent = new Intent(getInstrumentation().getTargetContext(),
                WelcomeActivity.class);
        intent.putExtra("Catalog", (java.io.Serializable) catalog);
        setActivityIntent(intent);
        setActivityInitialTouchMode(false);
        activity = getActivity();
    }

    public void testActivityNotNull() {
        assertNotNull(activity);
    }

    public void testTitleOnStartIsCorrect() {
        TextView textView = (TextView) activity.findViewById(R.id.titleTextView);
        assertEquals("Incorrect label of the button", "Butiker", textView.getText());
    }

    public void testListViewOfButikerIsNotNull() {
        ListView listView = (ListView) activity.findViewById(R.id.theListView);
        assertNotNull(listView);
    }

    public void testListViewOfButikerHasChilds() {
        ListView listView = (ListView) activity.findViewById(R.id.theListView);
        assertTrue(listView.getChildCount() > 0);
    }

    public void testPerformClickStoreName() {
        ListView listView = (ListView) activity.findViewById(R.id.theListView);
        View firstItem = listView.getChildAt(0);
        Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(
                StorePdfActivity.class.getName(), null, false
        );

        TouchUtils.clickView(this, firstItem);

        StorePdfActivity productActivity =
                (StorePdfActivity) monitor.waitForActivityWithTimeout(2000);
        assertNotNull(productActivity);
        assertTrue(getInstrumentation().checkMonitorHit(monitor, 1));
        productActivity.finish();
    }

    public void testPerfomClickNextButton() {
        ImageButton nextButton = (ImageButton) activity.findViewById(R.id.nextImageButton);
        TextView textView = (TextView) activity.findViewById(R.id.titleTextView);

        TouchUtils.clickView(this, nextButton);

        assertEquals("Produkter", textView.getText());
    }

    public void testPerfomClickPreviousButton() {
        ImageButton nextButton = (ImageButton) activity.findViewById(R.id.previosImageButton);
        TextView textView = (TextView) activity.findViewById(R.id.titleTextView);

        TouchUtils.clickView(this, nextButton);

        assertEquals("Produkter", textView.getText());
    }

    public void testListViewOfProdukterIsNotNull() {
        ImageButton nextButton = (ImageButton) activity.findViewById(R.id.nextImageButton);
        TextView textView = (TextView) activity.findViewById(R.id.titleTextView);

        TouchUtils.clickView(this, nextButton);

        ListView listView = (ListView) activity.findViewById(R.id.theListView);
        assertEquals("Produkter", textView.getText());
        assertNotNull(listView);
    }

    public void testListViewOfProdukterHasChilds() {
        ImageButton nextButton = (ImageButton) activity.findViewById(R.id.nextImageButton);
        TextView textView = (TextView) activity.findViewById(R.id.titleTextView);

        TouchUtils.clickView(this, nextButton);

        ListView listView = (ListView) activity.findViewById(R.id.theListView);
        assertEquals("Produkter", textView.getText());
        assertTrue(listView.getChildCount() > 0);
    }

    public void testPerformClickCategory() {
        ImageButton nextButton = (ImageButton) activity.findViewById(R.id.nextImageButton);

        TouchUtils.clickView(this, nextButton);

        ListView listView = (ListView) activity.findViewById(R.id.theListView);
        View firstItem = listView.getChildAt(0);

        Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(
                ProductActivity.class.getName(), null, false
        );

        TouchUtils.clickView(this, firstItem);

        ProductActivity productActivity =
                (ProductActivity) monitor.waitForActivityWithTimeout(2000);
        assertNotNull(productActivity);
        assertTrue(getInstrumentation().checkMonitorHit(monitor, 1));
        productActivity.finish();
    }
}


