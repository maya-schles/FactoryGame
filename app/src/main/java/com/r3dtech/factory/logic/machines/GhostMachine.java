package com.r3dtech.factory.logic.machines;

import com.r3dtech.factory.logic.inventory.ItemStack;

/**
 * This class represents a machine that isn't yet built, and so has almost no functionality.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 24/01/2018.
 */

public class GhostMachine implements Machine {
    private int type;
    public GhostMachine(int type) {
        this.type = type;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public String saveToString() {
        return null;
    }

    @Override
    public void input(ItemStack input) {

    }

    @Override
    public ItemStack output() {
        return null;
    }

    @Override
    public void process(float deltaTime) {

    }

    @Override
    public OutputDirection getOutputDirection() {
        return null;
    }

    @Override
    public void rotate() {

    }
}
