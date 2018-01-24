package com.r3dtech.factory.graphics.inventory;

import com.r3dtech.factory.logic.inventory.ItemStack;

/**
 * This class is a bundle that holds everything to describe a slot.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 23/01/2018.
 */

public class SlotBundle {
    public enum TextOrNum {
        TEXT,
        NUM
    }

    Slot slot;
    boolean isFull;
    boolean isSelected;
    TextOrNum textOrNum;

    public SlotBundle(Slot slot, boolean isFull, boolean isSelected, TextOrNum textOrNum) {
        this.slot = slot;
        this.isFull = isFull;
        this.isSelected = isSelected;
        this.textOrNum = textOrNum;
    }
    public SlotBundle(Slot slot) {
        this.slot = slot;
        this.isFull = !slot.isEmpty();
        this.isSelected = false;
        this.textOrNum = TextOrNum.NUM;
    }
}
