package com.r3dtech.factory.tile_map.graphics;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.renderscript.ScriptGroup;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
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
    private static final int DEFAULT_ALPHA = 255;
    private int[] colors;
    private int[][] bitmap;
    private Paint[] paints;
    private int size;

    public SimpleBitmapDrawable(int[][] bitmap, int[] colors, int alpha) {
        this.bitmap = bitmap;
        size = bitmap.length;
        paints = new Paint[colors.length];
        this.colors = colors;
        for (int i = 0; i < paints.length; i++) {
            paints[i] = new Paint();
            paints[i].setColor(colors[i]);
            paints[i].setAlpha(alpha);
        }
    }

    public SimpleBitmapDrawable(int[][] bitmap, int[] colors) {
        this(bitmap, colors, DEFAULT_ALPHA);
    }

    public SimpleBitmapDrawable(String bitmapFilename) {
        InputStream stream = this.getClass().getResourceAsStream(bitmapFilename);
        /*for (int i = 0; i < 64*64; i++) {
            try {
                Log.d("PIXEL " + i, Integer.toString(stream.read()));
            } catch (IOException e) {
                Log.d("PIXEL " + i, "shit.");
            }
        }*/
        Bitmap fileBitmap = BitmapFactory.decodeStream(stream);
        size = fileBitmap.getWidth();
        bitmap = new int[size][size];
        List<Integer> colorList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int currColor = fileBitmap.getPixel(j, i);
                if(!colorList.contains(currColor)) {
                    colorList.add(currColor);
                }
                bitmap[i][j] = colorList.indexOf(currColor);
            }
        }


        colors = colorListToArray(colorList);
        for (int[] line : bitmap) {
            Log.d("BITMAP", Arrays.toString(line));
        }
        Log.d("COLORS", Arrays.toString(colors));
        paints = new Paint[colors.length];
        for (int i = 0; i < paints.length; i++) {
            paints[i] = new Paint();
            paints[i].setColor(colors[i]);
        }
    }

    private static int[] colorListToArray(List<Integer> list) {
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        Rect bounds = getBounds(); // has to be a square.
        int rect_size = bounds.height()/size;
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

    public static SimpleBitmapDrawable add(SimpleBitmapDrawable a, SimpleBitmapDrawable b, int alpha) {
        int size = a.bitmap[0].length;
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
