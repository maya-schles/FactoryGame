package com.r3dtech.factory.graphics.machines;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import com.r3dtech.factory.MyGame;
import com.r3dtech.factory.Utils;
import com.r3dtech.factory.framework.GameScreen;
import com.r3dtech.factory.graphics.GenericDrawer;
import com.r3dtech.factory.graphics.inventory.Slot;
import com.r3dtech.factory.graphics.inventory.SlotBundle;
import com.r3dtech.factory.graphics.inventory.SlotDrawer;
import com.r3dtech.factory.logic.inventory.Inventory;

/**
 * This class is a general machine's screen.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 24/01/2018.
 */

public abstract class MachineScreen implements GameScreen {
    private static final int MAP_BUTTON_WIDTH = 128;
    private static final int MAP_BUTTON_HEIGHT = 128;
    private static final int MAP_BUTTON_RIGHT_DIST = 16;
    private static final String MAP_BUTTON_FILE = "/res/drawable/map_icon.jpg";

    protected Canvas canvas;
    protected MyGame game;
    private Drawable mapButton;

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

        slotDrawer = new SlotDrawer(game.getAssets());
    }

    private Slot[] generateInvSlots() {
        Slot[] slots;

        Slot firstSlot = Slot.getDefaultSlot();
        firstSlot.offset(0, canvas.getHeight()/2);

        slots = Slot.generateSlots(inventory.getSlotsMax(), firstSlot);
        for (int i = 0; i < slots.length; i++) {
            slots[i].setItemStack(inventory.getItemStack(i));
        }
        return slots;
    }

    abstract protected Slot[] generateMachineSlots();

    private Slot[] generateSlots() {
        return Utils.concat(generateMachineSlots(), generateInvSlots());
    }
    @Override
    public void paint() {
        canvas.drawColor(Color.GRAY);

        Slot[] slots = generateSlots();
        for (int i = 0; i < slots.length; i++) {
            slotDrawer.draw(canvas, new SlotBundle(slots[i], !slots[i].isEmpty(),
                    i == selected, SlotBundle.TextOrNum.NUM));
        }

        mapButton.draw(canvas);
    }

    @Override
    public void onClick(int x, int y) {
        if (mapButton.getBounds().contains(x, y)) {
            game.setMapScreen();
            return;
        }

        Slot[] slots = generateSlots();

        for (int i = 0; i < slots.length; i++) {
            if (slots[i].getBounds().contains(x, y)) {
                if (i == selected) {
                    selected = -1;
                    return;
                }
                if (selected != -1 && (slots[i].isEmpty() ||
                        slots[i].getItemStack().getItem() == slots[selected].getItemStack().getItem())) {
                    slots[i].getItemStack().add(slots[selected].getItemStack());
                    selected = -1;
                    return;
                }
                if (!slots[i].isEmpty()) {
                    selected = i;
                }
                return;
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
