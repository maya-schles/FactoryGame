package com.r3dtech.factory.tile_map.graphics;

import android.graphics.Color;

/**
 * This class is a drawable for the stone tile.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 20/01/2018.
 */

public class StoneDrawable extends SimpleBitmapDrawable {
    private static final int[][] ver0 = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 0, 0, 0, 1, 0},
            {0, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 1, 1, 0, 0, 0},
            {0, 0, 0, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}};
    private static final int[][] ver1 = {
            {0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 0},
            {0, 0, 1, 1, 0, 1, 1, 0},
            {0, 1, 1, 1, 0, 0, 0, 0},
            {0, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0}};
    private static final int[][] ver2 = {
            {0, 0, 4, 4, 4, 0, 0, 0},
            {0, 4, 2, 2, 2, 4, 4, 0},
            {0, 4, 1, 1, 2, 2, 2, 4},
            {4, 3, 1, 1, 1, 2, 2, 4},
            {4, 3, 1, 1, 1, 1, 2, 4},
            {4, 3, 3, 1, 1, 1, 2, 4},
            {0, 4, 3, 3, 3, 3, 4, 0},
            {0, 0, 4, 4, 4, 4, 0, 0}};
    private static final int[][][] vers = {ver0, ver1, ver2};
    private static final int[] colors = {
            Color.rgb(0x81, 0x81, 0x81),
            Color.rgb( 0x91, 0x91, 0x91),
            Color.rgb(0xb1, 0xb1, 0xb1),
            Color.rgb( 0x51, 0x51, 0x51),
            Color.rgb( 0x31, 0x31, 0x31)};

    public StoneDrawable(Integer ver) {
        super(vers[ver], colors);
    }

    public static int getMainColor() {
        return colors[0];
    }
}
