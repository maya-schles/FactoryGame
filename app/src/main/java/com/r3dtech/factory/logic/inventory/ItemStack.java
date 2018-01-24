package com.r3dtech.factory.logic.inventory;

/**
 * This class represents a stack of items.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 23/01/2018.
 */

public class ItemStack {
    private static final int STACK_LIMIT = 128;
    private GameItem item;
    private int amount;

    public ItemStack(GameItem item, int amount) {
        this.item = item;
        setAmount(amount);
    }

    private void setAmount(int amount) {
        this.amount = Math.max(0, Math.min(amount, STACK_LIMIT));
    }

    public GameItem getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }

    public boolean decreaseAmount(int amountToDecrease) {
        if (amountToDecrease < amount) {
            setAmount(amount-amountToDecrease);
            return true;
        }
        return false;
    }

    public boolean increaseAmount(int amountToIncrease) {
        if (amountToIncrease < STACK_LIMIT - amount) {
            setAmount(amount+amountToIncrease);
            return true;
        }
        return false;
    }
}
