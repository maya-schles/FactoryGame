package com.r3dtech.factory.tile_map;


import java.util.Arrays;

/**
 * An enum for the possible types of world tiles in the game map.
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 13/01/2018.
 */

public enum TileType {
    FOREST("forest"),
    GRASS("grass"),
    STONE("stone"),
    EMPTY("empty");

    private String name;

    TileType(String name) {
        this.name = name;
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
}
