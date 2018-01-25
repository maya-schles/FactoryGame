package com.r3dtech.factory.graphics.inventory;

import com.r3dtech.factory.MyGameImplementation;
import com.r3dtech.factory.graphics.GenericDrawer;
import com.r3dtech.factory.graphics.slots.Slot;
import com.r3dtech.factory.graphics.slots.SlotBundle;
import com.r3dtech.factory.graphics.slots.SlotDrawer;
import com.r3dtech.factory.logic.inventory.Inventory;
import com.r3dtech.factory.logic.machines.Machine;
import com.r3dtech.factory.logic.machines.MachineType;

/**
 * This is the inventory screen.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 23/01/2018.
 */

public class InventoryScreen extends InventoryMenuScreen {
    private Inventory inventory;
    private GenericDrawer<SlotBundle> slotDrawer;

    public InventoryScreen(MyGameImplementation game) {
        super(game);

        inventory = game.getInventory();
        slotDrawer = new SlotDrawer(game.getAssets());
    }

    private Slot[] generateSlots() {
        Slot[] slots;
        slots = Slot.generateSlots(inventory.getSlotsMax(), firstSlot);
        for (int i = 0; i < slots.length; i++) {
            slots[i].setItemStack(inventory.getItemStack(i));
        }
        return slots;
    }

    @Override
    public void paint() {
        super.paint();
        Slot[] slots  = generateSlots();
        for (Slot slot: slots) {
            slotDrawer.draw(canvas, new SlotBundle(slot));
        }
    }

    @Override
    public void onClick(int x, int y) {
        super.onClick(x, y);
        Slot[] slots = generateSlots();
        for (Slot slot: slots) {
            if (slot.getBounds().contains(x, y)) {
                MachineType type = Machine.getMachine(slot.getItemStack().getItem());
                if (type != null) {
                    game.setMachinePlaceScreen(type);
                }
                return;
            }
        }
    }

    @Override
    protected Tab getTab() {
        return Tab.INVENTORY;
    }

    @Override
    public void onScale(float scale) {

    }

    @Override
    public void onScroll(float dx, float dy) {

    }
}
