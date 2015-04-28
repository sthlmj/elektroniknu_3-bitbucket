package com.wordpress.elektroniknu.elektroniknu;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.view.ContextThemeWrapper;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by chenz_000 on 2015-04-27.
 */
public class MainActivityTest extends
        android.test.ActivityUnitTestCase<MainActivity> {

    private MainActivity activity;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws  Exception{
        super.setUp();
        ContextThemeWrapper context = new ContextThemeWrapper(getInstrumentation().getTargetContext(), R.style.AppTheme);
        setActivityContext(context);
        Intent intent = new Intent(getInstrumentation().getTargetContext(),
                MainActivity.class);
        startActivity(intent, null, null);
        activity = getActivity();
    }

    public void testLayout() {
        TextView textView = (TextView) activity.findViewById(R.id.titleTextView);
        assertEquals("Incorrect label of the button", "Butiker", textView.getText());
    }
}


