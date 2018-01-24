package com.r3dtech.factory.logic.machines;

import com.r3dtech.factory.logic.inventory.GameItem;
import com.r3dtech.factory.logic.inventory.ItemStack;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * This interface represents a machine.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 23/01/2018.
 */

public interface Machine {
    static Map<GameItem, MachineType> machineItems = getMachineItems();

    static Map<GameItem, MachineType> getMachineItems() {
        Map<GameItem, MachineType> res = new HashMap<>();
        res.put(GameItem.STONE_FURNACE, MachineType.STONE_FURNACE);
        return res;
    }
    enum MachineType {
        STONE_FURNACE(StoneFurnace.class);

        private Class machineClass;

        MachineType(Class machineClass) {
            this.machineClass = machineClass;
        }

        public Class getMachineClass() {
            return machineClass;
        }
        public int toInt() {
            return Arrays.asList(values()).indexOf(this);
        }

        public static MachineType fromInt(int i) {
            return values()[i];
        }
    }

    int getType();
    String saveToString();
    void input(ItemStack input);
    ItemStack output();
    void process(float deltaTime);

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
}
