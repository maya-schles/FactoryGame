package com.r3dtech.factory.logic.machines;


import com.r3dtech.factory.logic.inventory.GameItem;
import com.r3dtech.factory.logic.inventory.ItemStack;

/**
 * This class is a stone furnace machine.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 22/01/2018.
 */

public class StoneFurnace extends FueledProcessingMachine {
    private static final GameItem[] smeltResults = {GameItem.STONE_BRICK, null, null, null, null, GameItem.IRON_PLATE, null};
    private static final MachineType TYPE = MachineType.STONE_FURNACE;

    private ItemStack smeltable = new ItemStack();
    private ItemStack output = new ItemStack();

    StoneFurnace(){
        super();
    }

    StoneFurnace(ItemStack[] itemStacks) {
        super();
        smeltable = itemStacks[0];
        fuel = itemStacks[1];
        output = itemStacks[2];
    }

    private GameItem getSmeltRes(GameItem smeltable) {
        return smeltResults[smeltable.toInt()];
    }

    @Override
    boolean canProcess() {
        if ((!isSmeltable(smeltable.getItem()) || smeltable.isEmpty())) {
            return false;
        }
        if (!output.isEmpty() && output.getItem() != getSmeltRes(smeltable.getItem())) {
            return false;
        }
        return (!output.isFull());
    }

    private boolean isSmeltable(GameItem item) {
        return getSmeltRes(item) != null;
    }

    @Override
    void process() {
         smeltable.decreaseAmount(1);
         output.setItem(getSmeltRes(smeltable.getItem()));
         output.increaseAmount(1);
    }


    @Override
    public MachineType getType() {
        return TYPE;
    }

    public ItemStack getSmeltable() {
        return smeltable;
    }

    public ItemStack getOutput() {
        return output;
    }

    @Override
    public ItemStack[] getItems() {
        return new ItemStack[] {smeltable, fuel, output};
    }
}