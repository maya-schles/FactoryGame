package com.r3dtech.factory.inventory;

import java.util.Arrays;

/**
 * This enum is used for the types of items in the game.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 18/01/2018.
 */

public enum GameItem {
    STONE("stone"),
    WOOD("wood");

    private String name;

    GameItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int toInt() {
        return Arrays.asList(values()).indexOf(this);
    }

    public static GameItem fromInt(int i) {
        return values()[i];
    }
}
