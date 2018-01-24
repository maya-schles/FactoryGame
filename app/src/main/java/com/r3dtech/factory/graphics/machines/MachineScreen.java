package com.r3dtech.factory.graphics.machines;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;

import com.r3dtech.factory.MyGame;
import com.r3dtech.factory.Utils;
import com.r3dtech.factory.framework.GameScreen;
import com.r3dtech.factory.graphics.GenericDrawer;
import com.r3dtech.factory.graphics.slots.Slot;
import com.r3dtech.factory.graphics.slots.SlotBundle;
import com.r3dtech.factory.graphics.slots.SlotDrawer;
import com.r3dtech.factory.logic.inventory.Inventory;

/**
 * This class is a general machine's screen.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 24/01/2018.
 */

public abstract class MachineScreen implements GameScreen {
    private static final int SLOT_TOP_OFFSET = 48;
    private static final int MAP_BUTTON_WIDTH = 128;
    private static final int MAP_BUTTON_HEIGHT = 128;
    private static final int MAP_BUTTON_RIGHT_DIST = 16;
    private static final int TEXT_SIZE = 32;
    private static final int TEXT_BOTTOM_DIST = 16;

    private static final String MAP_BUTTON_FILE = "/res/drawable/map_icon.jpg";

    protected Canvas canvas;
    protected MyGame game;
    private Drawable mapButton;
    private Slot firstInventorySlot = Slot.getDefaultSlot();

    protected Inventory inventory;
    private GenericDrawer<SlotBundle> slotDrawer;
    private int selected = -1;
    private Paint textPaint = new Paint(Paint.FAKE_BOLD_TEXT_FLAG);

    public MachineScreen(MyGame game) {
        this.game = game;
        canvas = new Canvas(game.getFrameBuffer());
        inventory = game.getInventory();

        mapButton = Drawable.createFromStream(this.getClass().getResourceAsStream(MAP_BUTTON_FILE), "src");
        mapButton.setBounds(canvas.getWidth() - MAP_BUTTON_WIDTH - MAP_BUTTON_RIGHT_DIST,
                0, canvas.getWidth() - MAP_BUTTON_RIGHT_DIST, MAP_BUTTON_HEIGHT);

        slotDrawer = new SlotDrawer(game.getAssets());
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(TEXT_SIZE);
        Typeface textFont = Typeface.createFromAsset(game.getAssets(), "text_font.ttf");
        textPaint.setTypeface(textFont);
        firstInventorySlot.offset(0, SLOT_TOP_OFFSET);
        firstInventorySlot.offset(0, canvas.getHeight()/2);
    }

    private Slot[] generateInvSlots() {
        Slot[] slots;
        slots = Slot.generateSlots(inventory.getSlotsMax(), firstInventorySlot);
        for (int i = 0; i < slots.length; i++) {
            slots[i].setItemStack(inventory.getItemStack(i));
        }
        return slots;
    }

    abstract protected Slot[] generateMachineSlots();

    abstract protected String getMachineName();

    private Slot[] generateSlots() {
        return Utils.concat(generateMachineSlots(), generateInvSlots());
    }

    @Override
    public void paint() {
        canvas.drawColor(Color.GRAY);

        canvas.drawText("Inventory:", firstInventorySlot.getBounds().left,
                firstInventorySlot.getBounds().top - TEXT_BOTTOM_DIST, textPaint);

        Slot[] slots = generateSlots();
        for (int i = 0; i < slots.length; i++) {
            slotDrawer.draw(canvas, new SlotBundle(slots[i], !slots[i].isEmpty(),
                    i == selected, SlotBundle.TextOrNum.NUM));
        }

        canvas.drawText(getMachineName(), slots[0].getBounds().left,
                slots[0].getBounds().top - TEXT_BOTTOM_DIST, textPaint);

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
