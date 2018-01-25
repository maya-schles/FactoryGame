package com.r3dtech.factory.logic.machines;

import java.util.Arrays;

/**
 * This enum is the different possible output directions for a machine.
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 25/01/2018.
 */
public enum OutputDirection {
    UP,
    RIGHT,
    DOWN,
    LEFT,
    NONE;

    public static OutputDirection fromInt(int i) {
        return values()[i];
    }

    public int toInt() {
        return Arrays.asList(values()).indexOf(this);
    }

    public OutputDirection rotate() {
        return values()[(toInt() + 1) % 4];
    }
}
