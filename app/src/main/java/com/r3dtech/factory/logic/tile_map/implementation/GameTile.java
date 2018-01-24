package com.r3dtech.factory.logic.tile_map.implementation;


import com.r3dtech.factory.logic.machines.Machine;
import com.r3dtech.factory.logic.tile_map.MapTile;
import com.r3dtech.factory.logic.tile_map.TileType;

/**
 * This class represents a single tile on the map.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 12/01/2018.
 */

public class GameTile implements MapTile {
    private TileType tileType;
    private int ver;
    private Machine machine = null;

    GameTile(TileType tileType, int ver) {
        super();
        this.tileType = tileType;
        this.ver = ver;
    }

    public TileType tileType() {
        return tileType;
    }


    public String toString() {
        return Integer.toString(tileType.toInt());
    }

    @Override
    public int getVer() {
        return ver;
    }

    @Override
    public Machine getMachine() {
        return machine;
    }

    @Override
    public void setMachine(Machine machine) {
        this.machine = machine;
    }
}
