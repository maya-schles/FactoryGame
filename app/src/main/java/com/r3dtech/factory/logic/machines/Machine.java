package com.r3dtech.factory.logic.machines;

import com.r3dtech.factory.logic.inventory.GameItem;
import com.r3dtech.factory.logic.inventory.ItemStack;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * This interface represents a machine.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 23/01/2018.
 */

public interface Machine {
    Map<GameItem, MachineType> machineItems = getMachineItems();

    static Map<GameItem, MachineType> getMachineItems() {
        Map<GameItem, MachineType> res = new HashMap<>();
        res.put(GameItem.STONE_FURNACE, MachineType.STONE_FURNACE);
        return res;
    }

    MachineType getType();
    String saveToString();
    void process(float deltaTime);
    OutputDirection getOutputDirection();
    void rotate();
    void setState(MachineState state);
    MachineState getState();
    ItemStack[] getItems();

    static Machine createMachine(MachineType type) {
        try {
            Constructor<Machine> constructor = type.getMachineClass().getConstructor();
            return constructor.newInstance();
        } catch (Exception e) {
            return null;
        }
    }

    static Machine loadFromString(String string) {
        Scanner scanner = new Scanner(string);
        int typeInt = scanner.nextInt();
        if (typeInt == -1) {
            return null;
        }
        MachineType type = MachineType.fromInt(typeInt);
        try {
            Constructor<Machine> constructor = type.getMachineClass().getConstructor(String.class);
            Machine res = constructor.newInstance(scanner.nextLine());
            scanner.close();
            return res;
        } catch (Exception e) {
            return null;
        }
    }

    static MachineType getMachine(GameItem item) {
        return machineItems.get(item);
    }

    static GameItem getItem(MachineType machineType) {
        for (Map.Entry<GameItem, MachineType> entry : machineItems.entrySet()) {
            if (entry.getValue() == machineType) {
                return entry.getKey();
            }
        }
        return null;
    }
}
