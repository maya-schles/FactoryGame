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
    private static final MachineType TYPE = MachineType.STONE_FURNACE;

    private TimerCallback callback = new TimerCallback() {
        @Override
        public void onTimerDone(LoadingTimer timer) {
            smelt();
        }
    };
    private ItemStack fuel = new ItemStack();
    private ItemStack smeltable = new ItemStack();
    private ItemStack output = new ItemStack();

    private LoadingTimer timer = new LoadingTimer(300, callback);

    private GameItem[] smeltResults = {GameItem.STONE_BRICK, null, null};
    private int[] fuelTimes = {0, 500, 0};

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
        if (currFuel == 0 && fuel.getAmount() == 0) {
            return false;
        }
        if (smeltable.isEmpty()) {
            return false;
        }
        if (output.getItem() != getSmeltRes(smeltable.getItem())) {
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

             if(currFuel == 0) {
                 currFuel += fuelTimes[fuel.getItem().toInt()];
                 fuel.decreaseAmount(1);
             }
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
        fuel.add(stack);
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

    @Override
    public void process(float deltaTime) {
        if(currFuel != 0) {
            timer.update(deltaTime);
            if (timer.isRunning()) {
                currFuel -= deltaTime;
            }
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
}