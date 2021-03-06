package com.r3dtech.factory.graphics.crafting;

import com.r3dtech.factory.MyGameImplementation;
import com.r3dtech.factory.graphics.GenericDrawer;
import com.r3dtech.factory.graphics.inventory.InventoryMenuScreen;
import com.r3dtech.factory.graphics.slots.Slot;
import com.r3dtech.factory.graphics.slots.SlotBundle;
import com.r3dtech.factory.graphics.slots.SlotDrawer;
import com.r3dtech.factory.logic.crafting.CraftingManager;
import com.r3dtech.factory.logic.inventory.ItemStack;

/**
 * This is the main crafting screen.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 23/01/2018.
 */

public class CraftingScreen extends InventoryMenuScreen {
    private CraftingManager craftingManager;
    private GenericDrawer<SlotBundle> slotDrawer;

    public CraftingScreen(MyGameImplementation game) {
        super(game);

        craftingManager = game.getCraftingManager();
        slotDrawer = new SlotDrawer(game.getAssets());
    }

    private Slot[] generateSlots() {
        Slot[] slots;
        slots = Slot.generateSlots(craftingManager.getRecipes().length, firstSlot);
        for (int i = 0; i < slots.length; i++) {
            slots[i].setItemStack(new ItemStack(craftingManager.getRecipe(i).getProduct(), 1));
        }
        return slots;
    }
    @Override
    public void paint() {
        super.paint();

        Slot[] slots = generateSlots();

        int availableRecipesLength = craftingManager.getAvailableRecipes().length;
        for (int i = 0; i < slots.length; i++) {
            slotDrawer.draw(canvas, new SlotBundle(slots[i], i < availableRecipesLength,
                    false, SlotBundle.TextOrNum.TEXT));
        }
    }

    @Override
    public void onClick(int x, int y) {
        super.onClick(x, y);
        Slot[] slots = generateSlots();
        for(Slot slot: slots) {
            if(slot.getBounds().contains(x, y)) {
                game.setRecipeScreen(craftingManager.getRecipe(slot.getItemStack().getItem()));
            }
        }
    }

    @Override
    public void onScale(float scale) {

    }

    @Override
    public void onScroll(float dx, float dy) {

    }

    @Override
    protected Tab getTab() {
        return Tab.CRAFTING;
    }
}
