package com.r3dtech.factory.logic.loading_timers;

import com.r3dtech.factory.logic.inventory.GameItem;

/**
 * This class manages several timers that correspond to GameItems.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 22/01/2018.
 */

public interface TimersManager {
    LoadingTimer[] getTimers();
    void update(float deltaTime);
    int getTimersNum(GameItem recipe);
    void addTimer(GameItem recipe);
}

