package com.r3dtech.factory.logic.tile_map.implementation;

import android.graphics.Point;

import com.r3dtech.factory.logic.tile_map.MapSegmentPerspective;
import com.r3dtech.factory.logic.tile_map.TileMap;

/**
 * This is an implementation of a map's perspective (with scale and position).
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 23/01/2018.
 */

public class GameMapPerspective extends GameMapSegment implements MapSegmentPerspective {
    private float scale = 2;

    public GameMapPerspective(TileMap map) {
        super(map);
    }

    @Override
    public void movePosFloat(float dx, float dy) {
        movePos((int) (dx/scale), (int) (dy/scale));
    }

    private void setScale(float scale) {
        this.scale = Math.max(1.5f, Math.min(5f,scale));
    }

    @Override
    public void zoom(float zoom) {
        setScale(scale*zoom);
    }

    @Override
    public Point getTileFromLoc(int x, int y) {
        return super.getTileFromLoc((int) (x/scale), (int) (y/scale));
    }

    @Override
    public float getScale() {
        return scale;
    }
}
