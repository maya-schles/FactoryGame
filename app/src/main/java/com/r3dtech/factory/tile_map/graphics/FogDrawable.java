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

    public FogDrawable(Integer ver) {
        super(vers[ver], colors);
    }
}
