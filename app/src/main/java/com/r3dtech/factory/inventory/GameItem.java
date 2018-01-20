package com.r3dtech.factory.inventory;

import java.util.Arrays;

/**
 * This enum is used for the types of items in the game.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 18/01/2018.
 */

public enum GameItem {
    STONE("stone"),
    WOOD("wood"),
    PLACEHOLDER0("placeholder"),
    PLACEHOLDER1("placeholder"),
    PLACEHOLDER2("placeholder"),
    PLACEHOLDER3("placeholder"),
    PLACEHOLDER4("placeholder"),
    PLACEHOLDER5("placeholder"),;

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
