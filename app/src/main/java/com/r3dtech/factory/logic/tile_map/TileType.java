package com.r3dtech.factory.logic.tile_map;


import com.r3dtech.factory.logic.inventory.GameItem;

import java.util.Arrays;

/**
 * An enum for the possible types of world tiles in the game map.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 13/01/2018.
 */

public enum TileType {
    FOREST("forest", GameItem.WOOD),
    GRASS("grass", null),
    STONE("stone", GameItem.STONE),
    COAL_ORE("coal_ore", GameItem.COAL),
    IRON_ORE("iron_ore", GameItem.IRON);

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

    public GameItem getResource() {
        return resource;
    }

    public int toInt() {
        return Arrays.asList(values()).indexOf(this);
    }
}
