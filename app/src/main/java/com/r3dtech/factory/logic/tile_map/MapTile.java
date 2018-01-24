package com.r3dtech.factory.logic.tile_map;


import com.r3dtech.factory.logic.machines.Machine;

/**
 * A tile interface for a tiled map game.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 13/01/2018.
 */

public interface MapTile {
    TileType tileType();
    int getVer();
    Machine getMachine();
    void setMachine(Machine machine);
}
