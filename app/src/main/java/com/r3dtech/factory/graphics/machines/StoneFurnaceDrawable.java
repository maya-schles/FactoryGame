package com.r3dtech.factory.graphics.machines;

import android.graphics.Color;

import com.r3dtech.factory.graphics.SimpleBitmapDrawable;

/**
 * This class is a drawable of a stone furnace.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 22/01/2018.
 */

public class StoneFurnaceDrawable extends SimpleBitmapDrawable {
    private static final int[][] bitmap = {
            {6, 6, 0, 1, 1, 0, 6, 6},
            {6, 0, 1, 0, 0, 1, 0, 6},
            {0, 1, 1, 2, 2, 1, 1, 0},
            {1, 0, 2, 3, 3, 2, 0, 1},
            {0, 1, 3, 4, 4, 3, 1, 0},
            {0, 1, 4, 5, 5, 4, 1, 0},
            {1, 0, 0, 1, 1, 0, 0, 1},
            {0, 1, 1, 0, 0, 1, 1, 0}};
    private static final int[] colors = {
            Color.rgb(0x66, 0x66, 0x66),
            Color.rgb(0x80, 0x80, 0x80),
            Color.rgb(0x00, 0x00, 0x00),
            Color.rgb(0xe8, 0x99, 0x27),
            Color.rgb(0xe8, 0xbf, 0x27),
            Color.rgb(0xe8, 0xd5, 0x27)};

    public StoneFurnaceDrawable() {
        super(bitmap, colors);
    }
}
