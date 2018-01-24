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
    private Slot[] slots;
    private Inventory inventory;
    private GenericDrawer<SlotBundle> slotDrawer;

    public InventoryScreen(MyGameImplementation game) {
        super(game, Tab.INVENTORY);

        inventory = game.getInventory();
        slots = Slot.generateSlots(inventory.getSlotsMax(), firstSlot);
        slotDrawer = new SlotDrawer(game.getAssets());
    }

    @Override
    public void paint() {
        super.paint();
        for (int i = 0; i < inventory.getSlotsMax(); i++) {
            GameItem item = inventory.getItem(i);
            int amount = (item == null)?0 : inventory.getAmount(item);
            ItemStack itemStack = new ItemStack(item, amount);
            slotDrawer.draw(canvas, new SlotBundle(itemStack, slots[i],
                    item != null, false, SlotBundle.TextOrNum.NUM));
        }
    }
}
