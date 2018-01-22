package com.r3dtech.factory.Machines;


import android.util.Pair;

import com.r3dtech.factory.Machines.implementation.StoneFurnace;
import com.r3dtech.factory.inventory.GameItem;
import com.r3dtech.factory.tile_map.implementation.Constants;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This interface represents a machine.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 22/01/2018.
 */

public interface Machine extends Serializable{
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
    void input(Pair<GameItem, Integer> input);
    Pair<GameItem, Integer> output();

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
