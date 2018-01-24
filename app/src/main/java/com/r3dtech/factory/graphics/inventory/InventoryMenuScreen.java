package com.r3dtech.factory.graphics.inventory;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import com.r3dtech.factory.MyGame;
import com.r3dtech.factory.framework.GameScreen;


/**
 * This is a master class for all screens that are inventory menus.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 23/01/2018.
 */

public class InventoryMenuScreen implements GameScreen{
    private static final int BUTTON_WIDTH = 384;
    private static final int BUTTON_HEIGHT = 96;

    private static final int MAP_BUTTON_WIDTH = 128;
    private static final int MAP_BUTTON_HEIGHT = 128;
    private static final int MAP_BUTTON_RIGHT_DIST = 16;
    private static final String MAP_BUTTON_FILE = "/res/drawable/map_icon.jpg";

    private static final int FIRST_SLOT_TOP_OFFSET = 64;


    protected Slot firstSlot;


    public enum Tab {
        INVENTORY,
        CRAFTING
    }

    protected Canvas canvas;
    protected MyGame game;

    private Drawable inventoryButton;
    private Drawable craftingButton;

    private Drawable mapButton;

    public InventoryMenuScreen(MyGame game, Tab tab) {
        this.game = game;
        canvas = new Canvas(game.getFrameBuffer());

        mapButton = Drawable.createFromStream(this.getClass().getResourceAsStream(MAP_BUTTON_FILE), "src");
        mapButton.setBounds(canvas.getWidth() - MAP_BUTTON_WIDTH - MAP_BUTTON_RIGHT_DIST,
                0, canvas.getWidth() - MAP_BUTTON_RIGHT_DIST, MAP_BUTTON_HEIGHT);

        switch (tab) {
            case INVENTORY:
                inventoryButton = new MenuButton("Inventory", true, game.getAssets());
                craftingButton = new MenuButton("Crafting", false, game.getAssets());
                break;
            case CRAFTING:
                inventoryButton = new MenuButton("Inventory", false, game.getAssets());
                craftingButton = new MenuButton("Crafting", true, game.getAssets());
                break;
        }

        Rect buttonBounds = new Rect(0, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
        inventoryButton.setBounds(buttonBounds);
        buttonBounds.offset(BUTTON_WIDTH, 0);
        craftingButton.setBounds(buttonBounds);

        firstSlot = Slot.getDefaultSlot();
        firstSlot.offset(0, FIRST_SLOT_TOP_OFFSET);
    }

    @Override
    public void paint() {
        canvas.drawColor(Color.GRAY);
        inventoryButton.draw(canvas);
        craftingButton.draw(canvas);
        mapButton.draw(canvas);
    }

    @Override
    public void onClick(int x, int y) {
        if (mapButton.getBounds().contains(x, y)) {
            game.setMapScreen();
        }
        if (inventoryButton.getBounds().contains(x,y)) {
            game.setInventoryScreen();
        }
        if (craftingButton.getBounds().contains(x,y)) {
            game.setCraftingScreen();
        }
    }

    @Override
    public void onScale(float scale) {

    }

    @Override
    public void onScroll(float dx, float dy) {

    }
}
