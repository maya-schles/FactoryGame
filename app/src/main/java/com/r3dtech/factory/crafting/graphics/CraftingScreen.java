package com.r3dtech.factory.crafting.graphics;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import com.r3dtech.factory.framework.Game;
import com.r3dtech.factory.framework.GameScreen;
import com.r3dtech.factory.inventory.Inventory;
import com.r3dtech.factory.overlay_graphics.MenuButton;

/**
 * This class is a screen for the crafting menu.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 21/01/2018.
 */

public class CraftingScreen implements GameScreen {
    private static final int BUTTON_WIDTH = 384;
    private static final int BUTTON_HEIGHT = 96;
    private static final int MAP_BUTTON_WIDTH = 128;
    private static final int MAP_BUTTON_HEIGHT = 128;
    private static final int MAP_BUTTON_RIGHT_DIST = 16;

    private Drawable inventoryButton;
    private Drawable craftingButton;
    private Inventory inventory;
    private Game game;
    private Canvas canvas;
    private Drawable mapButtom;

    public CraftingScreen(Bitmap frameBuffer, Inventory inventory, Game game, AssetManager assets) {
        this.game = game;
        inventoryButton = new MenuButton("Inventory", false, assets);
        Rect buttonBounds = new Rect(0, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
        inventoryButton.setBounds(buttonBounds);
        craftingButton = new MenuButton("Crafting", true, assets);
        buttonBounds.offset(BUTTON_WIDTH, 0);
        craftingButton.setBounds(buttonBounds);
        this.inventory = inventory;
        canvas = new Canvas(frameBuffer);

        mapButtom = Drawable.createFromStream(this.getClass().
                getResourceAsStream("/res/drawable/map_icon.jpg"), "src");
        mapButtom.setBounds(canvas.getWidth()-MAP_BUTTON_WIDTH-MAP_BUTTON_RIGHT_DIST,
                0, canvas.getWidth()-MAP_BUTTON_RIGHT_DIST, MAP_BUTTON_HEIGHT);
    }
    @Override
    public void paint() {
        canvas.drawColor(Color.GRAY);
        inventoryButton.draw(canvas);
        craftingButton.draw(canvas);
        mapButtom.draw(canvas);
    }

    @Override
    public void onClick(int x, int y) {
        if (mapButtom.getBounds().contains(x, y)) {
            game.setMainScreen();
        }
        if (inventoryButton.getBounds().contains(x, y)) {
            game.setInventoryScreen();
        }
    }
}
