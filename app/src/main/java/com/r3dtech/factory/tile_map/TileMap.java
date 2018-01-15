package com.r3dtech.factory.tile_map;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * An interface for a tiled game map.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 13/01/2018.
 */

public interface TileMap {
    public MapTile getTile(int x, int y);
    public int height();
    public int width();
    public int tiledHeight();
    public int tiledWidth();
    public int simulatedHeight();
    public int simulatedWidth();
}
