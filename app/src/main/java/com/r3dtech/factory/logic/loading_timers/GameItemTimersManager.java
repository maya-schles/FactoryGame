package com.r3dtech.factory.logic.loading_timers;

import com.r3dtech.factory.logic.inventory.GameItem;

import java.util.ArrayList;
import java.util.List;


/**
 * This class is a TimersManager where the timers correspond to the creation of GameItems.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 23/01/2018.
 */

public class GameItemTimersManager implements TimersManager {
    protected static final int MAX_PER_ITEM = 10;

    protected LoadingTimer[] loadingTimers = new LoadingTimer[GameItem.values().length];
    protected int[] timersNum = new int[GameItem.values().length];
    protected List<GameItem> itemBuffer = new ArrayList<>();

    public interface GameItemTimerCallback {
        void onTimerDone(GameItem item);
    }
    public GameItemTimersManager(GameItemTimerCallback callback) {
        for (int i = 0; i < loadingTimers.length; i++) {
            final GameItem item = GameItem.fromInt(i);
            loadingTimers[i] = new LoadingTimer(300, new TimerCallback() {
                @Override
                public void onTimerDone(LoadingTimer timer) {
                    callback.onTimerDone(item);
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
