package com.r3dtech.factory.Machines.implementation;

import android.util.Pair;

import com.r3dtech.factory.Machines.Machine;
import com.r3dtech.factory.inventory.GameItem;

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

    @Override
    public void input(Pair<GameItem, Integer> input) {

    }

    @Override
    public Pair<GameItem, Integer> output() {
        return null;
    }
}
