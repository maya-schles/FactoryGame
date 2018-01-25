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

    private MachineState state = MachineState.NORMAL;

    private TimerCallback callback = (timer) -> {
        timer.reset();
        smelt();
    };
    private ItemStack fuel = new ItemStack();

    private ItemStack smeltable = new ItemStack();

    private ItemStack output = new ItemStack();
    private LoadingTimer timer = new LoadingTimer(300, callback);

    private int currFuel = 0;

    private OutputDirection outputDirection = OutputDirection.DOWN;

    StoneFurnace(){

    }

    StoneFurnace(String string) {
        Scanner scanner = new Scanner(string);
        outputDirection = OutputDirection.fromInt(scanner.nextInt());
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
        return (!output.isFull());
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


    @Override
    public String saveToString() {
        return Integer.toString(TYPE.toInt())+" "
                +outputDirection.toInt()+" "
                +ItemStack.descString(fuel)+" "
                +ItemStack.descString(smeltable) + " "
                +ItemStack.descString(output);
    }

    @Override
    public MachineType getType() {
        return TYPE;
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

    @Override
    public OutputDirection getOutputDirection() {
        return outputDirection;
    }

    @Override
    public void rotate() {
        outputDirection = outputDirection.rotate();
    }

    @Override
    public void setState(MachineState state) {
        this.state = state;
    }

    @Override
    public MachineState getState() {
        return state;
    }

    @Override
    public ItemStack[] getItems() {
        ItemStack[] res = {smeltable, fuel, output};
        return res;
    }
}