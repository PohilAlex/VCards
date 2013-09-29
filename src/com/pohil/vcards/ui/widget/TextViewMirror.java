package com.pohil.vcards.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

public class TextViewMirror extends TextView {

    //The below two constructors appear to be required
    public TextViewMirror(Context context) {
        super(context);
    }

    public TextViewMirror(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    public void onDraw(Canvas canvas) {
        //This saves off the matrix that the canvas applies to draws, so it can be restored later.
        canvas.save();

        //now we change the matrix
        //We need to rotate around the center of our text
        //Otherwise it rotates around the origin, and that's bad.
        canvas.translate(getWidth(), 0);
        canvas.scale(-1, 1);
        //draw the text with the matrix applied.
        super.onDraw(canvas);

        //restore the old matrix.
        canvas.restore();
    }
}