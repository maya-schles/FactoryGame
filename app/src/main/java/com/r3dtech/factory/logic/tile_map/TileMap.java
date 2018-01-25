package com.r3dtech.factory.logic.tile_map;

import android.graphics.Point;

import com.r3dtech.factory.logic.machines.Machine;


/**
 * An interface for a tiled game map.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 13/01/2018.
 */

public interface TileMap {
    int SMALL_DISCOVERED_DIST = 5;

    MapTile getTile(int x, int y);

    int height();
    int width();
    int tiledHeight();
    int tiledWidth();

    boolean isDiscovered(int x, int y);
    int getSmallDistFromDiscovered(int x, int y);

    Point getTileFromLoc(int x, int y);

    void addMachine(int x, int y, Machine machine);
    Machine[] getMachines();
}
