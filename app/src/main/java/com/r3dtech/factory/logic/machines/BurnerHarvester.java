package com.r3dtech.factory.logic.machines;

import com.r3dtech.factory.logic.inventory.GameItem;
import com.r3dtech.factory.logic.inventory.ItemStack;

/**
 * This class is the burner harvester machine.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 26/01/2018.
 */

public class BurnerHarvester extends FueledProcessingMachine {
    private static final MachineType type = MachineType.BURNER_HARVESTER;

    private ItemStack output = new ItemStack();
    private GameItem resource;

    BurnerHarvester(){
        super();
    }

    BurnerHarvester(ItemStack[] itemStacks) {
        super();
        fuel = itemStacks[0];
        output = itemStacks[1];
    }

    @Override
    boolean canProcess() {
        return (output.isEmpty() || (!output.isFull() && output.getItem() == resource));
    }

    @Override
    void process() {
        output.setItem(resource);
        output.increaseAmount(1);
    }

    @Override
    public MachineType getType() {
        return type;
    }

    @Override
    public ItemStack[] getItems() {
        return new ItemStack[]{fuel, output};
    }

    public ItemStack getOutput() {
        return output;
    }

    public void setResource(GameItem resource) {
        this.resource = resource;
    }
}
