package com.r3dtech.factory.Machines.implementation;

import com.r3dtech.factory.Machines.Machine;

/**
 * This class is a stone furnace machine.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 22/01/2018.
 */

public class StoneFurnace implements Machine {
    private static final MachineType TYPE = MachineType.STONE_FURNACE;

    public StoneFurnace(){

    }

    public StoneFurnace(String string) {

    }

    @Override
    public String saveToString() {
        return Integer.toString(TYPE.toInt())+" ";
    }

    @Override
    public MachineType getType() {
        return TYPE;
    }
}
