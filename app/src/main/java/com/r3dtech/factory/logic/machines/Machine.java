package com.r3dtech.factory.logic.machines;

import com.r3dtech.factory.logic.inventory.ItemStack;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This interface represents a machine.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 23/01/2018.
 */

public interface Machine {
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

    MachineType getType();
    String saveToString();
    void input(ItemStack input);
    ItemStack output();

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
}
