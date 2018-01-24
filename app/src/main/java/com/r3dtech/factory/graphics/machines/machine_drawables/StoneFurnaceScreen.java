package com.r3dtech.factory.graphics.machines.machine_drawables;

import com.r3dtech.factory.MyGame;
import com.r3dtech.factory.graphics.inventory.Slot;
import com.r3dtech.factory.graphics.machines.MachineScreen;
import com.r3dtech.factory.logic.inventory.ItemStack;
import com.r3dtech.factory.logic.machines.StoneFurnace;

/**
 * This is the stone furnace screen.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 24/01/2018.
 */

public class StoneFurnaceScreen extends MachineScreen {
    private StoneFurnace furnace;

    private Slot smeltable = Slot.getDefaultSlot();
    private Slot fuel = smeltable.adjacentSlot(Slot.AdjacentSlot.BELOW);
    private Slot product = smeltable.adjacentSlot(Slot.AdjacentSlot.RIGHT).adjacentSlot(Slot.AdjacentSlot.RIGHT);

    public StoneFurnaceScreen(MyGame game, StoneFurnace furnace) {
        super(game);
        this.furnace = furnace;
    }

    @Override
    protected Slot[] generateMachineSlots() {
        smeltable.setItemStack(furnace.getSmeltable());
        fuel.setItemStack(furnace.getFuel());
        product.setItemStack(furnace.getOutput());
        Slot[] slots = {smeltable, fuel, product};
        return slots;
    }

}
