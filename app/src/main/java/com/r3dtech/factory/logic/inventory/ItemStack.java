package com.r3dtech.factory.logic.inventory;

import java.util.Scanner;

/**
 * This class represents a stack of items.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 23/01/2018.
 */

public class ItemStack {
    static final int STACK_LIMIT = 128;
    private GameItem item;
    private int amount;

    public ItemStack() {
        item = GameItem.values()[0];
        amount = 0;
    }
    public ItemStack(GameItem item, int amount) {
        this.item = item;
        setAmount(amount);
    }

    public static ItemStack fromString(String string) {
        Scanner scanner = new Scanner(string);
        scanner.useDelimiter(":");
        int itemInt = scanner.nextInt();
        if (itemInt == -1) {
            return new ItemStack();
        }
        GameItem item = GameItem.fromInt(itemInt);
        int amount = scanner.nextInt();
        return new ItemStack(item, amount);
    }

    public static String descString(ItemStack stack) {
        if (stack.isEmpty()) {
            return "-1";
        }
        return Integer.toString(stack.item.toInt())+":"+Integer.toString(stack.amount);
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

    public boolean isFull() {
        return amount == STACK_LIMIT;
    }

    public boolean isEmpty() {
        return amount == 0;
    }

    public void setItem(GameItem item) {
        this.item = item;
    }

    public void add(ItemStack stack) {
        if (stack.getItem() == item && !isFull()) {
            amount = Math.min(amount+stack.amount, STACK_LIMIT);
            stack.amount = Math.max(0, stack.amount + amount - STACK_LIMIT);
        }
    }
}
