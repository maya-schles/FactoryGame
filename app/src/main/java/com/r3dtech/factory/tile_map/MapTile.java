package com.r3dtech.factory.tile_map;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * A tile interface for a tiled map game.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 13/01/2018.
 */

public interface MapTile {
    public TileType tileType();
    public void setTileType(TileType tileType);
    public int getVer();
}
