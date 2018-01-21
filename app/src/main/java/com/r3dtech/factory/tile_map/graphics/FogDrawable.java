package com.r3dtech.factory.tile_map.graphics;

import android.graphics.Color;

/**
 * This class is a drawable for the fog tile.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 21/01/2018.
 */

public class FogDrawable extends SimpleBitmapDrawable {
    private static final int[][] ver0 = {
            {0, 0, 0, 0, 0, 1, 1, 0},
            {1, 0, 0, 0, 0, 0, 1, 1},
            {0, 0, 1, 1, 0, 0, 1, 0},
            {1, 0, 1, 0, 1, 0, 1, 0},
            {1, 0, 1, 0, 0, 1, 1, 0},
            {0, 1, 0, 0, 1, 1, 0, 0},
            {0, 0, 1, 0, 1, 0, 0, 0},
            {0, 1, 0, 0, 1, 1, 1, 1}};
    private static final int[][] ver1 = {
            {0, 0, 0, 1, 1, 0, 0, 0},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 1, 1, 1, 0, 1},
            {0, 0, 1, 0, 1, 0, 1, 0},
            {1, 1, 1, 1, 1, 0, 0, 1},
            {0, 1, 0, 1, 1, 0, 0, 0},
            {1, 1, 0, 0, 1, 1, 0, 0},
            {0, 1, 0, 1, 1, 1, 0, 0}};
    private static final int[][] ver2 = {
            {0, 0, 0, 0, 0, 0, 1, 1},
            {0, 1, 1, 1, 1, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 1, 0, 1, 1, 0, 1, 1},
            {0, 1, 1, 1, 0, 0, 0, 1},
            {1, 1, 0, 0, 0, 0, 0, 0},
            {1, 0, 1, 0, 0, 1, 0, 1},
            {0, 1, 0, 0, 0, 0, 0, 1}};
    private static final int[][][] vers = {ver0, ver1, ver2};
    private static final int[] colors = {Color.rgb(0xdf, 0xdf, 0xdf), Color.rgb(0xd8, 0xd8, 0xd8)};

    public FogDrawable(Integer ver, int groundColor) {
        super(vers[ver], colorsOffset(colors, Color.valueOf(groundColor), 230));
    }

    private static int colorOffset(Color color, Color colorOffset, int alpha) {
        alpha = 255 - alpha;
        int newRed = (int) (alpha*colorOffset.red() + (255-alpha)*(color.red()));
        int newGreen = (int) (alpha*colorOffset.green() + (255-alpha)*(color.green()));
        int newBlue = (int) (alpha*colorOffset.blue() + (255-alpha)*(color.blue()));
        return Color.rgb(newRed, newGreen, newBlue);
    }

    private static int[] colorsOffset(int[] colors, Color colorOffset, int alpha) {
        int[] res = new int[colors.length];
        for (int i = 0; i < colors.length; i++) {
            res[i] = colorOffset(Color.valueOf(colors[i]), colorOffset, alpha);
        }
        return res;
    }
}
