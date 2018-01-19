package com.r3dtech.factory.inventory;



import com.r3dtech.factory.framework.FileIO;
import com.r3dtech.factory.tile_map.TileType;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * This class represemts the player's inventory.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 18/01/2018.
 */

public class Inventory {
    private static final String INVENTORY_FILE = "inventory.txt";
    private HashMap<GameItem, Integer> inventory = new HashMap<>(GameItem.values().length);

    public Inventory() {
        for (GameItem item: GameItem.values()) {
            inventory.put(item, 0);
        }
    }

    public void loadFromFile(FileIO fileIO) throws IOException {
        InputStream in = fileIO.readFile(INVENTORY_FILE);
        Scanner scanner = new Scanner(in);
        scanner.useDelimiter("[ :]");
        for (int i = 0; i < GameItem.values().length; i++) {
            GameItem item = GameItem.values()[scanner.nextInt()];
            int amount = scanner.nextInt();
            inventory.put(item, amount);
        }
        scanner.close();
    }

    public void loadToFIle(FileIO fileIO) throws IOException {
        OutputStream out = fileIO.writeFile(INVENTORY_FILE);
        PrintWriter writer = new PrintWriter(out);
        for(int i = 0; i< GameItem.values().length; i++) {
            writer.write(Integer.toString(i));
            writer.write(":");
            writer.write(Integer.toString(inventory.get(TileType.values()[i])));
        }
    }

    public int getAmount(GameItem item) {
        return inventory.get(item);
    }

    private void setAmount(GameItem item, int amount) {
        inventory.put(item, amount);
    }

    public void increaseAmount(GameItem item, int amountToAdd) {
        setAmount(item, getAmount(item)+amountToAdd);
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
        for (Map.Entry<GameItem, Integer> entry: inventory.entrySet()) {
            if (entry.getValue() > 0) {
                if (cnt == n) {
                    return entry.getKey();
                }
                cnt++;
            }
        }
        return null;
    }
}
