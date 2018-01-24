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

    ItemStack itemStack;
    Slot slot;
    boolean isFull;
    boolean isSelected;
    TextOrNum textOrNum;

    public SlotBundle(ItemStack itemStack, Slot slot, boolean isFull, boolean isSelected, TextOrNum textOrNum) {
        this.itemStack = itemStack;
        this.slot = slot;
        this.isFull = isFull;
        this.isSelected = isSelected;
        this.textOrNum = textOrNum;
    }
}
