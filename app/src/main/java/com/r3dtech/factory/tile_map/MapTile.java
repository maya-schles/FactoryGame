package com.r3dtech.factory.tile_map;


/**
 * A tile interface for a tiled map game.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 13/01/2018.
 */

public interface MapTile {
    TileType tileType();
    void setTileType(TileType tileType);
    int getVer();
}
