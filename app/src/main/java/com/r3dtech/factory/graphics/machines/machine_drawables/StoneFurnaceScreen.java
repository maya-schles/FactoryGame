package com.r3dtech.factory.graphics.machines.machine_drawables;

import com.r3dtech.factory.MyGame;
import com.r3dtech.factory.graphics.inventory.Slot;
import com.r3dtech.factory.graphics.inventory.SlotBundle;
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
    public void paint() {
        super.paint();
        slotDrawer.draw(canvas, new SlotBundle(furnace.getSmeltable(), smeltable));
        slotDrawer.draw(canvas, new SlotBundle(furnace.getFuel(), fuel));
        slotDrawer.draw(canvas, new SlotBundle(furnace.getOutput(), product));
    }

    @Override
    public void onClick(int x, int y) {
        super.onClick(x, y);
        if (smeltable.getBounds().contains(x, y)) {
            if (selected != -1) {
                ItemStack itemStack = inventory.getItemStack(selected);
                furnace.addSmeltable(itemStack);
                inventory.setItemStack(itemStack);
                selected = -1;
            }
        }
    }
}
