package com.r3dtech.factory.graphics.tile_map.tile_drawables;


import android.graphics.Color;

import com.r3dtech.factory.graphics.SimpleBitmapDrawable;

/**
 * This class is a drawable of the forest tile.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 21/01/2018.
 */

public class ForestDrawable extends SimpleBitmapDrawable {
    private static final int[][] ver0 = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 7, 1, 1, 0, 0, 0},
            {0, 7, 0, 2, 2, 0, 0, 0},
            {0, 0, 2, 2, 2, 2, 0, 0},
            {0, 0, 2, 2, 2, 2, 0, 0},
            {0, 0, 3, 3, 3, 3, 0, 0},
            {0, 0, 7, 4, 4, 0, 7, 0},
            {0, 0, 0, 6, 5, 0, 0, 0}};
    private static final int[][] ver1 = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 7, 0, 0},
            {0, 0, 0, 1, 2, 2, 7, 0},
            {0, 0, 1, 2, 2, 3, 0, 0},
            {0, 7, 1, 2, 3, 4, 0, 0},
            {0, 0, 3, 3, 4, 4, 0, 0},
            {0, 0, 0, 4, 4, 0, 0, 0},
            {0, 0, 0, 5, 5, 0, 0, 0}};
    private static final int[][] ver2 = {
            {0, 0, 2, 3, 3, 2, 0, 0},
            {0, 2, 3, 4, 4, 3, 2, 0},
            {1, 2, 0, 5, 5, 2, 4, 0},
            {2, 3, 0, 5, 0, 3, 1, 0},
            {3, 4, 5, 5, 5, 4, 3, 2},
            {7, 0, 3, 5, 5, 5, 4, 0},
            {0, 0, 0, 5, 5, 0, 0, 0},
            {0, 0, 0, 5, 5, 0, 0, 0}};

    private static final int[][][] vers = {ver0, ver1, ver2};
    private static final int[] colors = {
            Color.rgb(0x45, 0xb5, 0x4f),
            Color.rgb(0x9c, 0xcd, 0x23),
            Color.rgb(0x7a, 0xb4, 0x21),
            Color.rgb(0x5d, 0x9a, 0x1b),
            Color.rgb(0x42, 0x81, 0x18),
            Color.rgb(0x9b, 0x65, 0x29),
            Color.rgb(0xcd, 0x86, 0x36),
            Color.rgb(0x72, 0xc2, 0x7b)};

    public ForestDrawable(Integer ver) {
        super(vers[ver], colors);
    }
}
