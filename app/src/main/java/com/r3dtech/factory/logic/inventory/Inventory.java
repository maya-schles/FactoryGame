package com.r3dtech.factory.logic.inventory;


import com.r3dtech.factory.framework.FileIO;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * This class is an implementation of a game inventory.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 23/01/2018.
 */

public class Inventory {
    private static final int SLOT_MAX = 20;
    private static final String INVENTORY_FILE = "inventory.txt";
    private Map<GameItem, Integer> inventory = new HashMap<>(GameItem.values().length);

    public Inventory() {
        for (GameItem item: GameItem.values()) {
            inventory.put(item, 0);
        }
    }

    public void loadFromFile(FileIO fileIO) throws IOException {
        InputStream in = fileIO.readFile(INVENTORY_FILE);
        Scanner scanner = new Scanner(in);
        scanner.useDelimiter("[:\n]");
        for (int i = 0; i < GameItem.values().length; i++) {
            GameItem item = GameItem.values()[scanner.nextInt()];
            int amount = scanner.nextInt();
            inventory.put(item, amount);
        }
        scanner.close();
    }

    public void saveToFile(FileIO fileIO) throws IOException {
        OutputStream out = fileIO.writeFile(INVENTORY_FILE);
        PrintWriter writer = new PrintWriter(out);
        for(int i = 0; i< GameItem.values().length; i++) {
            writer.write(Integer.toString(i));
            writer.write(":");
            writer.write(Integer.toString(inventory.get(GameItem.values()[i])));
            writer.write("\n");
        }
        writer.close();
    }

    public int getAmount(GameItem item) {
        return inventory.get(item);
    }

    private void setAmount(GameItem item, int amount) {
        if (inventory.get(item) != 0 || getItemNum() < SLOT_MAX) {
            int newAmount = Math.max(0, Math.min(ItemStack.STACK_LIMIT, amount));
            inventory.put(item, amount);
        }
    }

    public void increaseAmount(GameItem item, int amountToAdd) {
        setAmount(item, getAmount(item)+amountToAdd);
    }

    public void decreaseAmount(GameItem item, int amountToSub) {
        setAmount(item, getAmount(item)-amountToSub);
    }

    public int getItemNum() {
        int cnt = 0;
        for (int num : inventory.values()) {
            if (num > 0) {
                cnt++;
            }
        }
        return cnt;
    }

    public GameItem getItem(int n) {
        int cnt = 0;
        for (GameItem item : GameItem.values()) {
            if (inventory.get(item) > 0) {
                if (cnt == n) {
                    return item;
                }
                cnt++;
            }
        }
        return null;
    }

    public boolean contains(GameItem item, Integer amount) {
        return inventory.get(item) >= amount;
    }

    public void clear() {
        for (GameItem item: GameItem.values()) {
            setAmount(item, 0);
        }
    }

    public int getSlotsMax() {
        return SLOT_MAX;
    }

    public ItemStack getItemStack(int i) {
        GameItem item = getItem(i);
        if (item == null) {
            return new ItemStack();
        }
        return new ItemStack(item, getAmount(item));
    }

    public void setItemStack(ItemStack stack) {
        setAmount(stack.getItem(), stack.getAmount());
    }
}
