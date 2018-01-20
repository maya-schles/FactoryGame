package com.r3dtech.factory.tile_map.graphics;

import android.graphics.Color;

/**
 * This class is a drawable for the grass tile.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 20/01/2018.
 */

public class GrassDrawable extends SimpleBitmapDrawable {
    private static final int[][] ver0 = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 0},
            {0, 1, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 1, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}};
    private static final int[][] ver1 = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 1, 0, 0, 0, 0, 0},
            {0, 1, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}};
    private static final int[][] ver2 = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 0, 0, 0},
            {1, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}};
    private static final int[][][] vers = {ver0, ver1, ver2};
    private static final int[] colors = {Color.rgb(0x45, 0xb5, 0x4f), Color.rgb(0x72, 0xc2, 0x7b)};
    public GrassDrawable(int ver) {
        super(vers[ver], colors);
    }
}
