package com.r3dtech.factory.map_graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import com.r3dtech.factory.framework.GameScreen;

/**
 * This class is a GameScreen that relies on a drawable object.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 18/01/2018.
 */

public class DrawableScreen implements GameScreen{
    private Drawable drawable;
    private Canvas canvas;

    public DrawableScreen(Drawable drawable, Bitmap frameBuffer) {
        this.drawable = drawable;
        canvas = new Canvas(frameBuffer);
        drawable.setBounds(canvas.getClipBounds());
    }


    @Override
    public void paint() {
        drawable.draw(canvas);
    }
}
