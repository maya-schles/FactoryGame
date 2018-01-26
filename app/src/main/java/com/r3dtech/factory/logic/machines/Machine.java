package com.r3dtech.factory.logic.machines;

import com.r3dtech.factory.logic.inventory.GameItem;
import com.r3dtech.factory.logic.inventory.ItemStack;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * This interface represents a machine.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 23/01/2018.
 */

public abstract class Machine {
    private static Map<GameItem, MachineType> machineItems = getMachineItems();

    private static Map<GameItem, MachineType> getMachineItems() {
        Map<GameItem, MachineType> res = new HashMap<>();
        res.put(GameItem.STONE_FURNACE, MachineType.STONE_FURNACE);
        res.put(GameItem.BURNER_HARVESTER, MachineType.BURNER_HARVESTER);
        return res;
    }

    private MachineState state = MachineState.NORMAL;
    private OutputDirection direction = OutputDirection.DOWN;

    public abstract MachineType getType();
    public abstract void update(float deltaTime);
    public abstract ItemStack[] getItems();

    public OutputDirection getOutputDirection() {
        return direction;
    }

    public void rotate() {
        direction = direction.rotate();
    }

    private void setOutputDirection(OutputDirection direction) {
        this.direction = direction;
    }

    public void setState(MachineState state) {
        this.state = state;
    }
    public MachineState getState() {
        return state;
    }

    public static Machine createMachine(MachineType type) {
        try {
            Constructor<Machine> constructor = type.getMachineClass().getConstructor();
            return constructor.newInstance();
        } catch (Exception e) {
            return null;
        }
    }

    private static String saveItemsToString(ItemStack[] stacks) {
        StringBuilder res = new StringBuilder();
        for (ItemStack stack: stacks) {
            res.append(ItemStack.descString(stack));
            res.append(" ");
        }
        return res.toString();
    }

    public String saveToString() {
        String res = Integer.toString(getType().toInt());
        res += " ";
        res += getOutputDirection().toInt();
        res += " ";
        res += saveItemsToString(getItems());
        return res;
    }

    public static Machine loadFromString(String string) {
        Scanner scanner = new Scanner(string);
        MachineType type = MachineType.fromInt(scanner.nextInt());
        try {
            OutputDirection outputDirection = OutputDirection.fromInt(scanner.nextInt());
            List<ItemStack> itemStackList = new ArrayList<>();
            while (scanner.hasNext()) {
                itemStackList.add(ItemStack.fromString(scanner.next()));
            }
            scanner.close();

            Constructor<Machine> constructor = type.getMachineClass().getConstructor(ItemStack[].class);
            ItemStack[] items = new ItemStack[itemStackList.size()];
            itemStackList.toArray(items);

            Machine res = constructor.newInstance(new Object[] {items});
            res.setOutputDirection(outputDirection);
            return res;
        } catch (Exception e) {
            return null;
        }
    }

    public static MachineType getMachine(GameItem item) {
        return machineItems.get(item);
    }

    public static GameItem getItem(MachineType machineType) {
        for (Map.Entry<GameItem, MachineType> entry : machineItems.entrySet()) {
            if (entry.getValue() == machineType) {
                return entry.getKey();
            }
        }
        return null;
    }
}
