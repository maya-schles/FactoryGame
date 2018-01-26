package com.r3dtech.factory.logic.machines;

import com.r3dtech.factory.logic.inventory.GameItem;
import com.r3dtech.factory.logic.inventory.ItemStack;
import com.r3dtech.factory.logic.loading_timers.LoadingTimer;
import com.r3dtech.factory.logic.loading_timers.TimerCallback;

/**
 * This is a master class for all fuel-based machines
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 26/01/2018.
 */

public abstract class FueledProcessingMachine extends Machine {
    public static final int MAX_FUEL = 3000;

    public FueledProcessingMachine() {
        timer.reset();
    }
    private TimerCallback callback = (timer) -> {
        timer.reset();
        process();
    };
    private LoadingTimer timer = new LoadingTimer(300, callback);

    protected int currFuel = 0;
    protected ItemStack fuel = new ItemStack();

    abstract void process();

    abstract boolean canProcess();

    private boolean isFuel(GameItem item) {
        return item.getFuelLevel() > 0;
    }

    private void fuelUp() {
        if(isFuel(fuel.getItem())&&!fuel.isEmpty()) {
            currFuel += fuel.getItem().getFuelLevel();
            fuel.decreaseAmount(1);
        }
    }

    @Override
    public void update(float deltaTime) {
        if (currFuel <= 0) {
            fuelUp();
        }
        if (!canProcess()) {
            timer.reset();
        }
        if (canProcess() && currFuel > 0) {
            timer.update(deltaTime);
            currFuel -= deltaTime;
        }
    }


    public LoadingTimer getTimer() {
        return timer;
    }

    public ItemStack getFuel() {
        return fuel;
    }

    public int getCurrFuel() {
        return currFuel;
    }
}
