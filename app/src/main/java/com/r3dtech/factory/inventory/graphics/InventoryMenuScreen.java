package com.r3dtech.factory.inventory.graphics;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import com.r3dtech.factory.framework.Game;

/**
 * This is class is a screen for an inventory menu.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 22/01/2018.
 */

public class InventoryMenuScreen extends ScreenWithSlots{
    private static final int SLOT_BEGIN_TOP = 128;
    private static final int SLOT_BEGIN_LEFT = 64;
    protected static final Rect FIRST_SLOT = new Rect(
            SLOT_BEGIN_LEFT,
            SLOT_BEGIN_TOP,
            SLOT_BEGIN_LEFT+SLOT_SIZE,
            SLOT_BEGIN_TOP+SLOT_SIZE);


    private static final int BUTTON_WIDTH = 384;
    private static final int BUTTON_HEIGHT = 96;
    protected Drawable inventoryButton;
    protected Drawable craftingButton;
    public enum Tab {
        INVENTORY,
        CRAFTING
    }

    public InventoryMenuScreen(Bitmap frameBuffer, Game game, AssetManager assets, Tab tab) {
        super(frameBuffer, game, assets);
        switch (tab) {
            case INVENTORY:
                inventoryButton = new MenuButton("Inventory", true, assets);
                craftingButton = new MenuButton("Crafting", false, assets);
                break;
            case CRAFTING:
                inventoryButton = new MenuButton("Inventory", false, assets);
                craftingButton = new MenuButton("Crafting", true, assets);
                break;
        }

        Rect buttonBounds = new Rect(0, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
        inventoryButton.setBounds(buttonBounds);
        buttonBounds.offset(BUTTON_WIDTH, 0);
        craftingButton.setBounds(buttonBounds);
    }

    @Override
    public void paint() {
        super.paint();
        inventoryButton.draw(canvas);
        craftingButton.draw(canvas);
    }

    @Override
    public void onClick(int x, int y) {
        super.onClick(x, y);
        if (inventoryButton.getBounds().contains(x, y)) {
            game.setInventoryScreen();
        }

        if (craftingButton.getBounds().contains(x, y)) {
            game.setCraftingScreen();
        }
    }

    @Override
    protected Rect getFirstSlot() {
        return FIRST_SLOT;
    }
}
