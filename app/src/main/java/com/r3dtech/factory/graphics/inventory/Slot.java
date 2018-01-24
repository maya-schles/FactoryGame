package com.r3dtech.factory.graphics.inventory;

import android.graphics.Rect;

import com.r3dtech.factory.logic.inventory.ItemStack;

/**
 * This class represents manages everything to do with slot locations.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 23/01/2018.
 */

public class Slot{
    public static final int SLOT_SIZE = 160;
    private static final int SLOT_BEGIN_LEFT = 64;
    private static final int SLOT_BEGIN_TOP = 64;
    private static int SLOTS_PER_ROW = 5;
    protected static final int TOP_DIST = 64;
    protected static final int LEFT_DIST = 32;

    public enum AdjacentSlot {
        ABOVE,
        BELOW,
        LEFT,
        RIGHT
    }
    private Rect bounds;
    private ItemStack itemStack;

    public Slot(int left, int top) {
        bounds = new Rect(left, top, left+SLOT_SIZE, top+SLOT_SIZE);
    }

    public Slot(Slot slot) {
        this.bounds = new Rect(slot.bounds);
    }

    public static Slot getDefaultSlot() {
        return new Slot(SLOT_BEGIN_LEFT, SLOT_BEGIN_TOP);
    }

    Slot nextSlot(int leftDistStart) {
        int col = Math.floorDiv(bounds.left-leftDistStart, SLOT_SIZE+LEFT_DIST);
        int newLeft = bounds.left;
        int newTop = bounds.top;
        if (col < SLOTS_PER_ROW - 1) {
            newLeft += SLOT_SIZE+LEFT_DIST;
        } else {
            newLeft = leftDistStart;
            newTop += SLOT_SIZE+TOP_DIST;
        }
        return new Slot(newLeft, newTop);
    }

    public static Slot[] generateSlots(int slotsNum, Slot firstSlot) {
        Slot[] slots = new Slot[slotsNum];
        Slot slot = new Slot(firstSlot);

        for (int i = 0; i < slotsNum; i++) {
            slots[i] = new Slot(slot);
            slot = slot.nextSlot(firstSlot.bounds.left);
        }
        return slots;
    }

    public Rect getBounds() {
        return bounds;
    }

    public int centerX() {
        return bounds.centerX();
    }

    public int bottom() {
        return bounds.bottom;
    }

    public void offset(int dx, int dy) {
        bounds.offset(dx, dy);
    }

    public Slot adjacentSlot(AdjacentSlot slot) {
        Slot res = new Slot(this);
        switch (slot) {
            case LEFT:
                res.offset(-(SLOT_SIZE+LEFT_DIST), 0);
                break;
            case ABOVE:
                res.offset(0, -(SLOT_SIZE+TOP_DIST));
                break;
            case RIGHT:
                res.offset(SLOT_SIZE+LEFT_DIST, 0);
                break;
            case BELOW:
                res.offset(0, SLOT_SIZE+TOP_DIST);
                break;
        }
        return res;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public boolean isEmpty() {
        return itemStack.isEmpty();
    }
}
