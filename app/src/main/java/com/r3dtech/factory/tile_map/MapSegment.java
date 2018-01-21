package com.r3dtech.factory.tile_map;

import android.graphics.Point;

/**
 * This represents a small segment of a game map.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 14/01/2018.
 */

public interface MapSegment extends TileMap {
    TileMap getMap();
    void setSizeX(int sizeX);
    void setSizeY(int sizeY);
    Point getPos();
    void setPos(Point pos);
    Point getPosOffset();
    int getLeftDist();
    int getTopDist();
    void movePos(int dx, int dy);
    void update();
    Point topLeftTile();
}
