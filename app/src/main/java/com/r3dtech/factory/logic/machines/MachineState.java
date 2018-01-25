package com.r3dtech.factory.logic.machines;

import com.r3dtech.factory.graphics.machines.machine_drawables.MachineDrawableCache;

/**
 * This enum is the different types of states a machine can have.
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 25/01/2018.
 */
public enum MachineState {
    NORMAL,
    GREEN,
    RED;

    public int getOffset() {
        switch (this) {
            case GREEN:
                return MachineDrawableCache.GREEN_TYPE_OFFSET;
            case RED:
                return MachineDrawableCache.RED_TYPE_OFFSET;
            default:
                return 0;
        }
    }
}
