package com.r3dtech.factory.crafting.graphics;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Rect;

import com.r3dtech.factory.crafting.CraftingManager;
import com.r3dtech.factory.crafting.Recipe;
import com.r3dtech.factory.framework.Game;
import com.r3dtech.factory.inventory.GameItem;
import com.r3dtech.factory.inventory.graphics.InventoryMenuScreen;

/**
 * This is class is a screen for a recipe menu.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 22/01/2018.
 */

public class RecipeScreen extends InventoryMenuScreen {
    private static final int COMPONENT_DIST_UP = 256;
    private static final int TITLE_DIST = 32;
    private CraftingManager craftingManager;

    private Recipe recipe;
    private Rect productSlot;
    private Rect[][] bounds;

    public RecipeScreen(Bitmap frameBuffer, Game game, AssetManager assets,
                        CraftingManager craftingManager, Recipe recipe) {
        super(frameBuffer, game, assets, Tab.CRAFTING);

        this.craftingManager = craftingManager;
        this.recipe = recipe;
        productSlot = new Rect(FIRST_SLOT);
        Rect firstComponentSlot = new Rect(productSlot);
        firstComponentSlot.offset(0, COMPONENT_DIST_UP+SLOT_SIZE);
        bounds = generateBounds(firstComponentSlot, recipe.getComponents().length);
    }

    @Override
    public void paint() {
        super.paint();

        drawSlot(productSlot, craftingManager.isAvailable(recipe), recipe.getProduct());
        canvas.drawText(recipe.getProduct().getName(),
                productSlot.centerX(),
                productSlot.bottom + textPaint.getTextSize(),
                textPaint);

        canvas.drawText("Components: ",
                productSlot.centerX(),
                productSlot.bottom + COMPONENT_DIST_UP-TITLE_DIST, textPaint);
        for (int i = 0; i < recipe.getComponents().length; i++) {
            int row = i/SLOTS_PER_ROW;
            int col = i%SLOTS_PER_ROW;
            GameItem item = recipe.getComponents()[i].first;
            drawSlot(bounds[row][col], true, item);
            canvas.drawText(recipe.getComponents()[i].second.toString(),
                    bounds[row][col].centerX(),
                    bounds[row][col].bottom + textPaint.getTextSize(),
                    textPaint);
        }
    }

    @Override
    public void onClick(int x, int y) {
        super.onClick(x, y);
        if (productSlot.contains(x, y)) {
            craftingManager.addTimer(recipe.getProduct());
        }
    }
}
