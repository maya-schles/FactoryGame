package com.r3dtech.factory.graphics;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.r3dtech.factory.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class is a simple bitmap drawable, it receives a 2d array and an array of colors, and
 * draws that.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 20/01/2018.
 */

public class SimpleBitmapDrawable extends Drawable{
    private static final String TAG = "SIMPLE_BITMAP_DRAWABLE";
    private static final int DEFAULT_ALPHA = 255;

    private int alpha = DEFAULT_ALPHA;
    private int[] colors;
    private int[][] bitmap;
    private Paint[] paints;
    private int size;


    SimpleBitmapDrawable(int[][] bitmap, int[] colors, int alpha) {
        this.bitmap = bitmap;
        size = bitmap.length;
        this.colors = colors;
        this.alpha = alpha;
        initPaints();
    }

    public SimpleBitmapDrawable(int[][] bitmap, int[] colors) {
        this(bitmap, colors, DEFAULT_ALPHA);
    }

    SimpleBitmapDrawable(String bitmapFilename) {
        loadFromBitmap(BitmapFactory.decodeStream(this.getClass().getResourceAsStream(bitmapFilename)));
    }

    private void initPaints() {
        paints = new Paint[colors.length];
        for (int i = 0; i < paints.length; i++) {
            paints[i] = new Paint();
            paints[i].setColor(colors[i]);
            paints[i].setAlpha(alpha);
        }
    }

    private void loadFromBitmap(Bitmap btmp) {
        size = btmp.getWidth();
        bitmap = new int[size][size];
        List<Integer> colorList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int currColor = btmp.getPixel(j, i);
                if(!colorList.contains(currColor)) {
                    colorList.add(currColor);
                }
                bitmap[i][j] = colorList.indexOf(currColor);
            }
        }


        colors = Utils.IntegerListToIntArray(colorList);
        for (int[] line : bitmap) {
            Log.d(TAG + " BITMAP", Arrays.toString(line));
        }
        Log.d(TAG + " COLORS", Arrays.toString(colors));

        initPaints();
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        Rect bounds = getBounds(); // has to be a square.
        int rect_size = bounds.height()/size;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(bitmap[j][i] < paints.length) {
                    Rect rect = new Rect(i * rect_size, j * rect_size,
                            (i + 1) * rect_size, (j + 1) * rect_size);
                    rect.offset(bounds.left, bounds.top);
                    canvas.drawRect(rect, paints[bitmap[j][i]]);
                }
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

    public static SimpleBitmapDrawable add(SimpleBitmapDrawable a, SimpleBitmapDrawable b, int alpha) {
        int size = a.size;
        int bColors = b.colors.length;

        int[][] resBitmap = new int[size][size];
        int[] resColors = new int[bColors*a.colors.length];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                resBitmap[i][j] = a.bitmap[i][j]*bColors+b.bitmap[i][j];
                resColors[resBitmap[i][j]] = colorOffset(b.colors[b.bitmap[i][j]],
                        a.colors[a.bitmap[i][j]], alpha);
            }
        }

        return new SimpleBitmapDrawable(resBitmap, resColors);
    }

    private static int colorOffset(int color, int colorOffset, int alpha) {
        Color color1 = Color.valueOf(color);
        Color colorOffset1 = Color.valueOf(colorOffset);

        int newRed = (int) (alpha*colorOffset1.red() + (255-alpha)*(color1.red()));
        int newGreen = (int) (alpha*colorOffset1.green() + (255-alpha)*(color1.green()));
        int newBlue = (int) (alpha*colorOffset1.blue() + (255-alpha)*(color1.blue()));

        return Color.rgb(newRed, newGreen, newBlue);
    }
}
