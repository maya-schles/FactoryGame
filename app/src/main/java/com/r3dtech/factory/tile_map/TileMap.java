package com.r3dtech.factory.tile_map;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * An interface for a tiled game map.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 13/01/2018.
 */

public interface TileMap {
    MapTile getTile(int x, int y);
    int height();
    int width();
    int tiledHeight();
    int tiledWidth();
    int simulatedHeight();
    int simulatedWidth();
    public MapTile getTileFromLoc(int x, int y);
}
