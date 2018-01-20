package com.r3dtech.factory.ResourceHarvesting;


import com.r3dtech.factory.inventory.GameItem;
import com.r3dtech.factory.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

/**
 * This class manages the manual resource harvesting.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 20/01/2018.
 */

public class ManualHarvestingManager {
    private static final int MAX_PER_RESOURCE = 10;
    private Inventory inventory;
    private LoadingTimer[] loadingTimers = new LoadingTimer[GameItem.values().length];
    private int[] timersNum = new int[GameItem.values().length];
    private List<GameItem> itemBuffer = new ArrayList<>();

    public ManualHarvestingManager(Inventory inventory) {
        this.inventory = inventory;
        for (int i = 0; i < loadingTimers.length; i++) {
            final GameItem item = GameItem.fromInt(i);
            loadingTimers[i] = new LoadingTimerImplementation(300, new TimerCallback() {
                @Override
                public void onTimerDone(LoadingTimer timer) {
                    inventory.increaseAmount(item, 1);
                    timersNum[item.toInt()] = Math.max(timersNum[item.toInt()] - 1, 0);
                    if (timersNum[item.toInt()] > 0) {
                        timer.reset();
                    }
                }
            });
        }
    }

    public void addTimer(GameItem item) {
        itemBuffer.add(item);
    }

    public LoadingTimer[] getTimers() {
        return loadingTimers;
    }

    public void update(int deltaTime) {
        for (GameItem item: itemBuffer) {
            int i = item.toInt();
            timersNum[i] = Math.min(MAX_PER_RESOURCE, timersNum[i]+1);
            loadingTimers[i].reset();
        }
        itemBuffer.clear();
        for(LoadingTimer timer: loadingTimers) {
            timer.update(deltaTime);
        }
    }

    public int getTimersNum(GameItem item) {
        return timersNum[item.toInt()];
    }
}
