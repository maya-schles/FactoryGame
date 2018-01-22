package com.r3dtech.factory.tile_map;

import com.r3dtech.factory.inventory.GameItem;

import java.util.Arrays;

/**
 * An enum for the possible types of world tiles in the game map.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 13/01/2018.
 */

public enum TileType {
    FOREST("forest", GameItem.WOOD),
    GRASS("grass", null),
    STONE("stone", GameItem.STONE);

    private String name;
    private GameItem resource;

    TileType(String name, GameItem resource) {
        this.name = name;
        this.resource = resource;
    }

    public String getName() {
        return name;
    }

    public static TileType fromInt(int i) {
        return values()[i];
    }

    public static TileType[] fromInt(int[] arr) {
        TileType[] res = new TileType[arr.length];
        for (int i = 0; i< arr.length; i++) {
            res[i] = fromInt(arr[i]);
        }
        return res;
    }

    public static TileType[][] fromInt(int[][] arr) {
        TileType[][] res = new TileType[arr.length][arr[0].length];
        for (int i = 0; i< arr.length; i++) {
            res[i] = fromInt(arr[i]);
        }
        return res;
    }

    public int toInt() {
        return Arrays.asList(values()).indexOf(this);
    }

    public GameItem getResource() {
        return resource;
    }
}
