package com.r3dtech.factory.tile_map;

import android.graphics.Point;

/**
 * This represents a small segment of a game map.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 14/01/2018.
 */

public interface MapSegment extends TileMap {
    public TileMap getMap();
    public void setSizeX(int sizeX);
    public void setSizeY(int sizeY);
    public Point getPos();
    public void setPos(Point pos);
    public Point getPosOffset();
    public int getLeftDist();
    public int getTopDist();
    public void movePos(int dx, int dy);
    public void updatePos();
}
