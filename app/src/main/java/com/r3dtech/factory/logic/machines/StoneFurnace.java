package com.r3dtech.factory.logic.machines;


import com.r3dtech.factory.logic.inventory.GameItem;
import com.r3dtech.factory.logic.inventory.ItemStack;
import com.r3dtech.factory.logic.loading_timers.LoadingTimer;
import com.r3dtech.factory.logic.loading_timers.TimerCallback;

import java.util.Scanner;

/**
 * This class is a stone furnace machine.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 22/01/2018.
 */

public class StoneFurnace implements Machine {
    public static final int MAX_FUEL = 3000;
    private static final GameItem[] smeltResults = {GameItem.STONE_BRICK, null, null, null};
    private static final int[] fuelTimes = {0, 300, 0, 0};

    private static final MachineType TYPE = MachineType.STONE_FURNACE;
    private TimerCallback callback = new TimerCallback() {
        @Override
        public void onTimerDone(LoadingTimer timer) {
            smelt();
            timer.reset();
        }
    };
    private ItemStack fuel = new ItemStack();

    private ItemStack smeltable = new ItemStack();

    private ItemStack output = new ItemStack();
    private LoadingTimer timer = new LoadingTimer(300, callback);

    private int currFuel = 0;

    public StoneFurnace(){

    }

    public StoneFurnace(String string) {
        Scanner scanner = new Scanner(string);
        fuel = ItemStack.fromString(scanner.next());
        smeltable = ItemStack.fromString(scanner.next());
        output = ItemStack.fromString(scanner.next());
    }

    private GameItem getSmeltRes(GameItem smeltable) {
        return smeltResults[smeltable.toInt()];
    }

    private boolean canSmelt() {
        if ((!isSmeltable(smeltable.getItem()) || smeltable.isEmpty())) {
            return false;
        }
        if (!output.isEmpty() && output.getItem() != getSmeltRes(smeltable.getItem())) {
            return false;
        }
        if (output.isFull()) {
            return false;
        }
        return true;
    }

    private boolean isSmeltable(GameItem item) {
        return getSmeltRes(item) != null;
    }

    private boolean isFuel(GameItem item) {
        return fuelTimes[item.toInt()] > 0;
    }

    private void smelt() {
         if (canSmelt()) {
             smeltable.decreaseAmount(1);

             output.setItem(getSmeltRes(smeltable.getItem()));
             output.increaseAmount(1);
         }
    }

    public void addSmeltable(ItemStack stack) {
        if (!isSmeltable(stack.getItem())) {
            return;
        }
        if (smeltable.isEmpty() && !stack.isEmpty()) {
            timer.reset();
        }
        smeltable.add(stack);
    }

    public void addFuel(ItemStack stack) {
        if (isFuel(stack.getItem())) {
            fuel.add(stack);
        }
    }


    @Override
    public String saveToString() {
        return Integer.toString(TYPE.toInt())+" "
                +ItemStack.descString(fuel)+" "
                +ItemStack.descString(smeltable) + " "
                +ItemStack.descString(output);
    }

    @Override
    public MachineType getType() {
        return TYPE;
    }

    @Override
    public void input(ItemStack input) {

    }

    @Override
    public ItemStack output() {
        return null;
    }

    private void fuelUp() {
        if(isFuel(fuel.getItem())&&!fuel.isEmpty()) {
            currFuel += fuelTimes[fuel.getItem().toInt()];
            fuel.decreaseAmount(1);
        }
    }
    @Override
    public void process(float deltaTime) {
        if (currFuel <= 0) {
            fuelUp();
        }

        if (!canSmelt()) {
            timer.reset();
        }

        if(canSmelt() && currFuel > 0) {
            timer.update(deltaTime);
            currFuel -= deltaTime;
        }
    }

    public ItemStack getFuel() {
        return fuel;
    }

    public ItemStack getSmeltable() {
        return smeltable;
    }

    public ItemStack getOutput() {
        return output;
    }

    public LoadingTimer getTimer() {
        return timer;
    }

    public int getCurrFuel() {
        return currFuel;
    }
}