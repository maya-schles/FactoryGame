package com.r3dtech.factory.tile_map;

import android.graphics.Point;

import com.r3dtech.factory.Machines.Machine;

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
    boolean isDiscovered(int x, int y);
    boolean isLocDiscovered(int x, int y);
    int getSmallDistFromDiscovered(int x, int y);
    Point getTileFromLoc(int x, int y);
    void addMachine(int x, int y, Machine machine);
}
