package com.r3dtech.factory.graphics.machines;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import com.r3dtech.factory.MyGame;
import com.r3dtech.factory.framework.GameScreen;
import com.r3dtech.factory.graphics.GenericDrawer;
import com.r3dtech.factory.graphics.inventory.Slot;
import com.r3dtech.factory.graphics.inventory.SlotBundle;
import com.r3dtech.factory.graphics.inventory.SlotDrawer;
import com.r3dtech.factory.logic.inventory.GameItem;
import com.r3dtech.factory.logic.inventory.Inventory;
import com.r3dtech.factory.logic.inventory.ItemStack;

/**
 * This class is a general machine's screen.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 24/01/2018.
 */

public class MachineScreen implements GameScreen {
    private static final int MAP_BUTTON_WIDTH = 128;
    private static final int MAP_BUTTON_HEIGHT = 128;
    private static final int MAP_BUTTON_RIGHT_DIST = 16;
    private static final String MAP_BUTTON_FILE = "/res/drawable/map_icon.jpg";

    protected Canvas canvas;
    protected MyGame game;
    private Drawable mapButton;

    private Slot[] slots;
    protected Inventory inventory;
    protected GenericDrawer<SlotBundle> slotDrawer;
    protected int selected = -1;

    public MachineScreen(MyGame game) {
        this.game = game;
        canvas = new Canvas(game.getFrameBuffer());
        inventory = game.getInventory();

        mapButton = Drawable.createFromStream(this.getClass().getResourceAsStream(MAP_BUTTON_FILE), "src");
        mapButton.setBounds(canvas.getWidth() - MAP_BUTTON_WIDTH - MAP_BUTTON_RIGHT_DIST,
                0, canvas.getWidth() - MAP_BUTTON_RIGHT_DIST, MAP_BUTTON_HEIGHT);

        Slot firstSlot = Slot.getDefaultSlot();
        firstSlot.offset(0, canvas.getHeight()/2);

        slots = Slot.generateSlots(inventory.getSlotsMax(), firstSlot);
        slotDrawer = new SlotDrawer(game.getAssets());
    }

    @Override
    public void paint() {
        canvas.drawColor(Color.GRAY);

        for (int i = 0; i < inventory.getSlotsMax(); i++) {
            GameItem item = inventory.getItem(i);
            slotDrawer.draw(canvas, new SlotBundle(inventory.getItemStack(i), slots[i],
                    !inventory.getItemStack(i).isEmpty(), i == selected, SlotBundle.TextOrNum.NUM));
        }
        mapButton.draw(canvas);
    }

    @Override
    public void onClick(int x, int y) {
        if (mapButton.getBounds().contains(x, y)) {
            game.setMapScreen();
        }
        for (int i = 0; i < inventory.getItemNum(); i++) {
            if (slots[i].getBounds().contains(x, y)) {
                selected = (selected == i) ? -1 : i;
            }
        }
    }

    @Override
    public void onScale(float scale) {

    }

    @Override
    public void onScroll(float dx, float dy) {

    }
}
