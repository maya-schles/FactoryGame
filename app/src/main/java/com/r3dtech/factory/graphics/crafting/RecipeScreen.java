package com.r3dtech.factory.graphics.crafting;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.r3dtech.factory.MyGame;
import com.r3dtech.factory.graphics.GenericDrawer;
import com.r3dtech.factory.graphics.inventory.InventoryMenuScreen;
import com.r3dtech.factory.graphics.inventory.Slot;
import com.r3dtech.factory.graphics.inventory.SlotBundle;
import com.r3dtech.factory.graphics.inventory.SlotDrawer;
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
    private Slot[] slots;
    private Inventory inventory;

    private GenericDrawer<SlotBundle> slotDrawer;

    private Paint titlePaint = new Paint(Paint.FAKE_BOLD_TEXT_FLAG);

    public RecipeScreen(MyGame game, Recipe recipe) {
        super(game, Tab.CRAFTING);

        this.craftingManager = game.getCraftingManager();
        this.recipe = recipe;
        this.inventory = game.getInventory();

        productSlot = new Slot(firstSlot);
        Slot firstComponentSlot = new Slot(productSlot);
        firstComponentSlot.offset(0, COMPONENT_DIST_UP + Slot.SLOT_SIZE);
        slots = Slot.generateSlots(recipe.getComponents().length, firstComponentSlot);

        slotDrawer = new SlotDrawer(game.getAssets());

        titlePaint.setColor(Color.BLACK);
        titlePaint.setTextSize(TITLE_SIZE);
        titlePaint.setTextAlign(Paint.Align.CENTER);
        Typeface textFont = Typeface.createFromAsset(game.getAssets(), "text_font.ttf");
        titlePaint.setTypeface(textFont);
    }

    @Override
    public void paint() {
        super.paint();

        slotDrawer.draw(canvas, new SlotBundle(new ItemStack(recipe.getProduct(), 1), productSlot,
                craftingManager.isAvailable(recipe), false, SlotBundle.TextOrNum.TEXT));

        canvas.drawText("Components: ",
                productSlot.centerX(), productSlot.bottom() + COMPONENT_DIST_UP-TITLE_DIST, titlePaint);
        for (int i = 0; i < recipe.getComponents().length; i++) {
            ItemStack componenet = recipe.getComponents()[i];
            slotDrawer.draw(canvas, new SlotBundle(componenet, slots[i],
                    inventory.contains(componenet.getItem(), componenet.getAmount()), false,
                    SlotBundle.TextOrNum.NUM));
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
