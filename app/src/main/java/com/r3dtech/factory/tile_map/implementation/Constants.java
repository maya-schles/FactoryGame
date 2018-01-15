package com.r3dtech.factory.tile_map.implementation;

import android.graphics.Point;

/**
 * This class contains all the constants for the map.
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 12/01/2018.
 */

public class Constants {
    public static final int MAP_WIDTH = 128;
    public static final int MAP_HEIGHT = 128;
    public static final int TILE_SIZE = 32;
    public static final int DEFAULT_SEG_WIDTH = 32*TILE_SIZE;
    public static final int DEFAULT_SEG_HEIGHT =32*TILE_SIZE;
    public static final Point DEFAULT_SEG_POS = new Point(MAP_WIDTH*TILE_SIZE/2,
            MAP_HEIGHT*TILE_SIZE/2);
}
