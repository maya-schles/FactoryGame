package com.r3dtech.factory.tile_map.graphics;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * This class is a simple bitmap drawable, it receives a 2d array and an array of colors, and
 * draws that.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 20/01/2018.
 */

public class SimpleBitmapDrawable extends Drawable{
    private int[][] bitmap;
    private Paint[] paints;
    private int size;

    public SimpleBitmapDrawable(int[][] bitmap, int[] colors) {
        this.bitmap = bitmap;
        size = bitmap.length;
        paints = new Paint[colors.length];
        for (int i = 0; i < paints.length; i++) {
            paints[i] = new Paint();
            paints[i].setColor(colors[i]);
        }
    }
    @Override
    public void draw(@NonNull Canvas canvas) {
        Rect bounds = getBounds(); // has to be a square.
        int rect_size = bounds.height()/8;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Rect rect = new Rect(i*rect_size, j*rect_size, (i+1)*rect_size, (j+1)*rect_size);
                rect.offset(bounds.left, bounds.top);
                canvas.drawRect(rect, paints[bitmap[j][i]]);
            }
        }
    }

    @Override
    public void setAlpha(int i) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }
}
