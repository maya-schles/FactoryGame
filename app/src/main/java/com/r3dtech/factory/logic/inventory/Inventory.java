package com.r3dtech.factory.logic.inventory;


import com.r3dtech.factory.framework.FileIO;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This class is an implementation of a game inventory.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 23/01/2018.
 */

public class Inventory {
    private static final int SLOT_MAX = 20;
    private static final String INVENTORY_FILE = "inventory.txt";
    private ItemStack[] inventory = new ItemStack[SLOT_MAX];

    public Inventory() {
        for (int i = 0; i < inventory.length; i++) {
            inventory[i] = new ItemStack();
        }
    }

    public void loadFromFile(FileIO fileIO) throws IOException {
        InputStream in = fileIO.readFile(INVENTORY_FILE);
        Scanner scanner = new Scanner(in);
        scanner.useDelimiter("[:\n]");
        for (int i = 0; i < SLOT_MAX && scanner.hasNextLine(); i++) {
            inventory[i] = ItemStack.fromString(scanner.nextLine());
        }
        scanner.close();
    }

    public void saveToFile(FileIO fileIO) throws IOException {
        OutputStream out = fileIO.writeFile(INVENTORY_FILE);
        PrintWriter writer = new PrintWriter(out);
        for(ItemStack stack: inventory) {
            if (!stack.isEmpty()) {
                writer.write(ItemStack.descString(stack));
                writer.write("\n");
            }
        }
        writer.close();
    }

    private int findIndex(GameItem item) {
        for(int i = 0; i < inventory.length; i++) {
            if (inventory[i].getItem() == item) {
                return i;
            }
        }
        return -1;
    }

    private int getFirstEmptySlot() {
        for (int i = 0; i < inventory.length; i++) {
            if(inventory[i].isEmpty()) {
                return i;
            }
        }
        return -1;
    }
    public int getAmount(GameItem item) {
        int i = findIndex(item);
        if ( i == -1) {
            return 0;
        }
        return inventory[i].getAmount();
    }

    private void setAmount(GameItem item, int amount) {
        int i = findIndex(item);
        if ( i == -1) {
            int j = getFirstEmptySlot();
            if (j != -1) {
                inventory[j] = new ItemStack(item, amount);
            }
            return;
        }
        inventory[i].increaseAmount(amount - inventory[i].getAmount());
    }

    public void increaseAmount(GameItem item, int amountToAdd) {
        setAmount(item, getAmount(item)+amountToAdd);
    }

    public void decreaseAmount(GameItem item, int amountToSub) {
        setAmount(item, getAmount(item)-amountToSub);
    }

    public int getItemNum() {
        int cnt = 0;
        for (ItemStack stack: inventory) {
            if (!stack.isEmpty()) {
                cnt++;
            }
        }
        return cnt;
    }


    public boolean contains(ItemStack stack) {
        int i = findIndex(stack.getItem());
        if (i != -1 && inventory[i].getAmount() >= stack.getAmount()) {
            return true;
        }
        return false;
    }

    public void clear() {
        for (int i = 0; i < inventory.length; i++) {
            inventory[i] = new ItemStack();
        }
    }

    public int getSlotsMax() {
        return SLOT_MAX;
    }

    public ItemStack getItemStack(int i) {
        return inventory[i];
    }
}
