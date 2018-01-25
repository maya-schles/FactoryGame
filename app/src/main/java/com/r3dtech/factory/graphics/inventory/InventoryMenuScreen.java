package com.r3dtech.factory.graphics.inventory;

import android.graphics.Color;

import com.r3dtech.factory.MyGame;
import com.r3dtech.factory.graphics.ButtonCallback;
import com.r3dtech.factory.graphics.DrawableButton;
import com.r3dtech.factory.graphics.ScreenWithButtons;
import com.r3dtech.factory.graphics.map.MapDrawableButton;
import com.r3dtech.factory.graphics.slots.Slot;


/**
 * This is a master class for all screens that are inventory menus.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 23/01/2018.
 */

public abstract class InventoryMenuScreen extends ScreenWithButtons{
    private static final String MAP_BUTTON_FILE = "/res/drawable/map_icon.jpg";

    private static final int FIRST_SLOT_TOP_OFFSET = 64;

    protected Slot firstSlot;

    public enum Tab {
        INVENTORY,
        CRAFTING
    }

    public InventoryMenuScreen(MyGame game) {
        super(game);
        firstSlot = Slot.getDefaultSlot();
        firstSlot.offset(0, FIRST_SLOT_TOP_OFFSET);
    }

    @Override
    public void paint() {
        canvas.drawColor(Color.GRAY);
        super.drawButtons();
    }


    @Override
    protected DrawableButton[] getButtons() {
        DrawableButton mapButton = new MapDrawableButton(MAP_BUTTON_FILE, game::setMapScreen);
        DrawableButton[] mapButtons = {mapButton};
        MapDrawableButton.setButtonBounds(canvas.getClipBounds(), mapButtons);

        ButtonCallback inventoryButtonCallback = game::setInventoryScreen;
        ButtonCallback craftingButtonCallback = game::setCraftingScreen;

        DrawableButton inventoryButton = null;
        DrawableButton craftingButton = null;
        switch (getTab()) {
            case INVENTORY:
                inventoryButton = new MenuButton(inventoryButtonCallback, "Inventory", true, game.getAssets());
                craftingButton = new MenuButton(craftingButtonCallback, "Crafting", false, game.getAssets());
                break;
            case CRAFTING:
                inventoryButton = new MenuButton(inventoryButtonCallback, "Inventory", false, game.getAssets());
                craftingButton = new MenuButton(craftingButtonCallback, "Crafting", true, game.getAssets());
                break;
        }

        DrawableButton[] menuButtons = {inventoryButton, craftingButton};
        MenuButton.setButtonBounds(menuButtons);

        DrawableButton[] res = {mapButton, inventoryButton, craftingButton};
        return res;
    }

    protected abstract Tab getTab();
}
