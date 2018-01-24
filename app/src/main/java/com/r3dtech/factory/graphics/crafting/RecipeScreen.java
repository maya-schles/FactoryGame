package com.r3dtech.factory.graphics.crafting;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.r3dtech.factory.MyGameImplementation;
import com.r3dtech.factory.graphics.GenericDrawer;
import com.r3dtech.factory.graphics.inventory.InventoryMenuScreen;
import com.r3dtech.factory.graphics.slots.Slot;
import com.r3dtech.factory.graphics.slots.SlotBundle;
import com.r3dtech.factory.graphics.slots.SlotDrawer;
import com.r3dtech.factory.logic.crafting.CraftingManager;
import com.r3dtech.factory.logic.crafting.Recipe;
import com.r3dtech.factory.logic.inventory.Inventory;
import com.r3dtech.factory.logic.inventory.ItemStack;

/**
 * This is the recipe crafting screen.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 23/01/2018.
 */

public class RecipeScreen extends InventoryMenuScreen {
    private static final int TITLE_SIZE = 32;
    private static final int COMPONENT_DIST_UP = 256;
    private static final int TITLE_DIST = 32;
    private CraftingManager craftingManager;

    private Recipe recipe;
    private Slot productSlot;
    private Inventory inventory;

    private GenericDrawer<SlotBundle> slotDrawer;

    private Paint titlePaint = new Paint(Paint.FAKE_BOLD_TEXT_FLAG);

    public RecipeScreen(MyGameImplementation game, Recipe recipe) {
        super(game, Tab.CRAFTING);

        this.craftingManager = game.getCraftingManager();
        this.recipe = recipe;
        this.inventory = game.getInventory();

        productSlot = new Slot(firstSlot);
        productSlot.setItemStack(new ItemStack(recipe.getProduct(), 1));

        slotDrawer = new SlotDrawer(game.getAssets());

        titlePaint.setColor(Color.BLACK);
        titlePaint.setTextSize(TITLE_SIZE);
        titlePaint.setTextAlign(Paint.Align.CENTER);
        Typeface textFont = Typeface.createFromAsset(game.getAssets(), "text_font.ttf");
        titlePaint.setTypeface(textFont);
    }

    private Slot[] generateSlots() {
        Slot[] slots;
        Slot firstComponentSlot = productSlot.adjacentSlot(Slot.AdjacentSlot.BELOW);
        firstComponentSlot.offset(0, COMPONENT_DIST_UP);
        slots = Slot.generateSlots(recipe.getComponents().length, firstComponentSlot);
        for (int i = 0; i < slots.length; i++) {
            slots[i].setItemStack(recipe.getComponents()[i]);
        }
        return slots;
    }

    @Override
    public void paint() {
        super.paint();

        Slot[] slots = generateSlots();
        slotDrawer.draw(canvas, new SlotBundle(productSlot, craftingManager.isAvailable(recipe),
                false, SlotBundle.TextOrNum.TEXT));

        canvas.drawText("Components: ",
                productSlot.centerX(), productSlot.bottom() + COMPONENT_DIST_UP-TITLE_DIST, titlePaint);

        for (Slot slot: slots) {
            slotDrawer.draw(canvas, new SlotBundle(slot, inventory.contains(slot.getItemStack()),
                    false, SlotBundle.TextOrNum.NUM));
        }
    }

    @Override
    public void onClick(int x, int y) {
        super.onClick(x, y);
        if (productSlot.getBounds().contains(x, y)) {
            craftingManager.addTimer(recipe.getProduct());
        }
    }

}
