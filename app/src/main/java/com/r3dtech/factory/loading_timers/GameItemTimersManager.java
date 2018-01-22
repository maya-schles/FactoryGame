package com.r3dtech.factory.loading_timers;

import com.r3dtech.factory.inventory.GameItem;
import com.r3dtech.factory.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a TimersManager when the timers correspond to the creation of GameItems.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 22/01/2018.
 */

public class GameItemTimersManager implements TimersManager{
    private static final int MAX_PER_ITEM = 10;
    protected Inventory inventory;
    protected LoadingTimer[] loadingTimers = new LoadingTimer[GameItem.values().length];
    protected int[] timersNum = new int[GameItem.values().length];
    protected List<GameItem> itemBuffer = new ArrayList<>();

    public GameItemTimersManager(Inventory inventory) {
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

    @Override
    public void addTimer(GameItem item) {
        itemBuffer.add(item);
    }

    @Override
    public LoadingTimer[] getTimers() {
        return loadingTimers;
    }

    @Override
    public void update(float deltaTime) {
        for (GameItem item: itemBuffer) {
            int i = item.toInt();
            if (timersNum[i] == 0) {
                loadingTimers[i].reset();
            }
            timersNum[i] = Math.min(MAX_PER_ITEM, timersNum[i]+1);
        }
        itemBuffer.clear();

        for(LoadingTimer timer: loadingTimers) {
            timer.update(deltaTime);
        }
    }

    @Override
    public int getTimersNum(GameItem item) {
        return timersNum[item.toInt()];
    }
}
