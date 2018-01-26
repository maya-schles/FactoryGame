package com.r3dtech.factory.graphics.machines.machine_drawables;

import android.graphics.Color;

import com.r3dtech.factory.graphics.SimpleBitmapDrawable;

/**
 * This class is a drawable for the burner harvester machine.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 26/01/2018.
 */

public class BurnerHarvesterDrawable extends SimpleBitmapDrawable {
    private static final int[][] bitmap = {
            {3, 0, 3, 3, 3, 3, 0, 3},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {3, 0, 1, 1, 1, 0, 0, 3},
            {3, 0, 0, 0, 2, 1, 0, 3},
            {3, 0, 0, 2, 0, 1, 0, 3},
            {3, 0, 2, 0, 0, 1, 0, 3},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {3, 0, 3, 3, 3, 3, 0, 3}};
    private static final int[] colors = {
            Color.rgb(0x99, 0x99, 0x99),
            Color.rgb(0x66, 0x66, 0x66),
            Color.rgb(0x83, 0x45, 0x2a)};

    public BurnerHarvesterDrawable(Integer direction) {
        super(bitmap, colors);
    }
}
