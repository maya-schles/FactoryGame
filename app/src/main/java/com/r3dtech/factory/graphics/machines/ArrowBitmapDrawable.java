package com.r3dtech.factory.graphics.machines;

import android.graphics.Color;

import com.r3dtech.factory.graphics.SimpleBitmapDrawable;
import com.r3dtech.factory.logic.machines.Machine;

/**
 * This class is an arrow drawable.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 25/01/2018.
 */

public class ArrowBitmapDrawable extends SimpleBitmapDrawable {
    private static final int[][] UpArrow = {
            {1, 1, 1, 0, 0, 1, 1, 1},
            {1, 1, 0, 0, 0, 0, 1, 1},
            {1, 0, 1, 0, 0, 1, 0, 1},
            {0, 1, 1, 0, 0, 1, 1, 0},
            {1, 1, 1, 0, 0, 1, 1, 1},
            {1, 1, 1, 0, 0, 1, 1, 1},
            {1, 1, 1, 0, 0, 1, 1, 1},
            {1, 1, 1, 0, 0, 1, 1, 1}};
    private static final int[][] DownArrow = {
            {1, 1, 1, 0, 0, 1, 1, 1},
            {1, 1, 1, 0, 0, 1, 1, 1},
            {1, 1, 1, 0, 0, 1, 1, 1},
            {1, 1, 1, 0, 0, 1, 1, 1},
            {0, 1, 1, 0, 0, 1, 1, 0},
            {1, 0, 1, 0, 0, 1, 0, 1},
            {1, 1, 0, 0, 0, 0, 1, 1},
            {1, 1, 1, 0, 0, 1, 1, 1}};
    private static final int[][] LeftArrow = {
            {1, 1, 1, 0, 1, 1, 1, 1},
            {1, 1, 0, 1, 1, 1, 1, 1},
            {1, 0, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {1, 0, 1, 1, 1, 1, 1, 1},
            {1, 1, 0, 1, 1, 1, 1, 1},
            {1, 1, 1, 0, 1, 1, 1, 1}};
    private static final int[][] RightArrow = {
            {1, 1, 1, 1, 0, 1, 1, 1},
            {1, 1, 1, 1, 1, 0, 1, 1},
            {1, 1, 1, 1, 1, 1, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 0, 1},
            {1, 1, 1, 1, 1, 0, 1, 1},
            {1, 1, 1, 1, 0, 1, 1, 1}};

    private static final int[][][] vers = {UpArrow, RightArrow, DownArrow, LeftArrow};
    private static final int[] colors = {Color.RED};

    public ArrowBitmapDrawable(Machine.OutputDirection direction) {
        super(vers[direction.toInt()], colors);
    }
}
