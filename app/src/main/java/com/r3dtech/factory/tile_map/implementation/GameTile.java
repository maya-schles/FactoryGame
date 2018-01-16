package com.r3dtech.factory.tile_map.implementation;


import com.r3dtech.factory.tile_map.MapTile;
import com.r3dtech.factory.tile_map.TileType;

/**
 * This class represents a single tile on the map.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 12/01/2018.
 */

public class GameTile implements MapTile {
    private TileType tileType;
    private int ver;

    public GameTile(TileType tileType, int ver) {
        super();
        this.tileType = tileType;
        this.ver = ver;
    }

    public static GameTile[] fromArr(TileType[] tileTypes, int[] vers) {
        GameTile[] res = new GameTile[tileTypes.length];
        for(int i = 0; i < tileTypes.length; i++) {
            res[i] = new GameTile(tileTypes[i], vers[i]);
        }
        return res;
    }

    public static GameTile[][] from2dArr(TileType[][] tileTypes, int[][] vers) {
        GameTile[][] res = new GameTile[tileTypes.length][tileTypes[0].length];
        for(int i = 0; i < tileTypes.length; i++) {
            res[i] = fromArr(tileTypes[i], vers[i]);
        }
        return res;
    }
    public TileType tileType() {
        return tileType;
    }

    public void setTileType(TileType tileType) {
        this.tileType = tileType;
    }

    public String toString() {
        return Integer.toString(tileType.toInt());
    }

    @Override
    public int getVer() {
        return ver;
    }
}
