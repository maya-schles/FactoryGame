package com.r3dtech.factory.graphics.inventory;

import com.r3dtech.factory.MyGameImplementation;
import com.r3dtech.factory.graphics.GenericDrawer;
import com.r3dtech.factory.logic.inventory.GameItem;
import com.r3dtech.factory.logic.inventory.Inventory;
import com.r3dtech.factory.logic.inventory.ItemStack;

/**
 * This is the inventory screen.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 23/01/2018.
 */

public class InventoryScreen extends InventoryMenuScreen {

    private Inventory inventory;
    private GenericDrawer<SlotBundle> slotDrawer;

    public InventoryScreen(MyGameImplementation game) {
        super(game, Tab.INVENTORY);

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
}
