package com.wordpress.elektroniknu.elektroniknu;

import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by LERA on 28-Apr-15.
 */

// highlights buttons onClick
public class ButtonHighlighter implements View.OnTouchListener {

    final ImageButton imageButton;

    public ButtonHighlighter (final ImageButton imageButton) {
        super();
        this.imageButton = imageButton;
    }

    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            //grey color filter, you can change the color as you like
            imageButton.setColorFilter(Color.argb(225, 185, 185, 185));
        } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            imageButton.setColorFilter(Color.argb(0, 185, 185, 185));
        }
        return false;
    }

}

