package com.r3dtech.factory.graphics.crafting;

import com.r3dtech.factory.MyGameImplementation;
import com.r3dtech.factory.graphics.GenericDrawer;
import com.r3dtech.factory.graphics.inventory.InventoryMenuScreen;
import com.r3dtech.factory.graphics.inventory.Slot;
import com.r3dtech.factory.graphics.inventory.SlotBundle;
import com.r3dtech.factory.graphics.inventory.SlotDrawer;
import com.r3dtech.factory.logic.crafting.CraftingManager;
import com.r3dtech.factory.logic.inventory.GameItem;
import com.r3dtech.factory.logic.inventory.ItemStack;

/**
 * This is the main crafting screen.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 23/01/2018.
 */

public class CraftingScreen extends InventoryMenuScreen {
    private Slot[] slots;
    private CraftingManager craftingManager;
    private GenericDrawer<SlotBundle> slotDrawer;

    public CraftingScreen(MyGameImplementation game) {
        super(game, Tab.CRAFTING);

        craftingManager = game.getCraftingManager();
        slots = Slot.generateSlots(GameItem.values().length, firstSlot);
        slotDrawer = new SlotDrawer(game.getAssets());
    }

    @Override
    public void paint() {
        super.paint();

        for (int i = 0; i < craftingManager.getRecipes().length; i++) {
            int availableRecipesLength = craftingManager.getAvailableRecipes().length;
            GameItem item;

            if (i < availableRecipesLength) {
                item = craftingManager.getAvailableRecipes()[i].getProduct();
            } else {
                item = craftingManager.getUnavailableRecipes()[i - availableRecipesLength].getProduct();
            }

            slotDrawer.draw(canvas, new SlotBundle(new ItemStack(item, 1), slots[i],
                    i < availableRecipesLength, false, SlotBundle.TextOrNum.TEXT));
        }
    }

    @Override
    public void onClick(int x, int y) {
        super.onClick(x, y);
        for (int i = 0; i < craftingManager.getRecipes().length; i++) {
            int availableRecipesLength = craftingManager.getAvailableRecipes().length;
            if (slots[i].getBounds().contains(x, y)) {
                GameItem item;
                if (i < availableRecipesLength) {
                    item = craftingManager.getAvailableRecipes()[i].getProduct();
                } else {
                    item = craftingManager.getUnavailableRecipes()[i-availableRecipesLength].getProduct();
                }
                game.setRecipeScreen(craftingManager.getRecipe(item));
            }
        }
    }
}
