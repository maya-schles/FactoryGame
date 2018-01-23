package com.r3dtech.factory.crafting.graphics;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Rect;

import com.r3dtech.factory.crafting.CraftingManager;
import com.r3dtech.factory.framework.Game;
import com.r3dtech.factory.inventory.GameItem;
import com.r3dtech.factory.inventory.graphics.InventoryMenuScreen;

/**
 * This class is a screen for the crafting menu.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 21/01/2018.
 */

public class CraftingScreen extends InventoryMenuScreen {
    private Rect[][] bounds;
    private CraftingManager craftingManager;


    public CraftingScreen(Bitmap frameBuffer, Game game, AssetManager assets, CraftingManager craftingManager) {
        super(frameBuffer, game, assets, Tab.CRAFTING);
        this.craftingManager = craftingManager;

        bounds = generateBounds(GameItem.values().length);
    }

    @Override
    public void paint() {
        super.paint();

        for (int i = 0; i < craftingManager.getRecipes().length; i++) {
            int row = i/SLOTS_PER_ROW;
            int col = i%SLOTS_PER_ROW;
            int availableRecipesLength = craftingManager.getAvailableRecipes().length;
            GameItem item;

            if (i < availableRecipesLength) {
                item = craftingManager.getAvailableRecipes()[i].getProduct();
            } else {
                item = craftingManager.getUnavailableRecipes()[i-availableRecipesLength].getProduct();
            }

            drawSlot(bounds[row][col], (i < availableRecipesLength), item);
            canvas.drawText(item.getName(),
                    bounds[row][col].centerX(),
                    bounds[row][col].bottom + textPaint.getTextSize(),
                    textPaint);
        }

    }

    @Override
    public void onClick(int x, int y) {
        super.onClick(x, y);
        for (int i = 0; i < craftingManager.getRecipes().length; i++) {
            int row = i/SLOTS_PER_ROW;
            int col = i%SLOTS_PER_ROW;
            int availableRecipesLength = craftingManager.getAvailableRecipes().length;
            if (bounds[row][col].contains(x, y)) {
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
