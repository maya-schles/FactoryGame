package com.r3dtech.factory.logic.machines;

import java.util.Arrays;

/**
 * This enum is the different types of machines.
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 25/01/2018.
 */
public enum MachineType {
    STONE_FURNACE(StoneFurnace.class);

    private Class machineClass;

    MachineType(Class machineClass) {
        this.machineClass = machineClass;
    }

    public Class getMachineClass() {
        return machineClass;
    }

    public int toInt() {
        return Arrays.asList(values()).indexOf(this);
    }

    public static MachineType fromInt(int i) {
        return values()[i];
    }
}
