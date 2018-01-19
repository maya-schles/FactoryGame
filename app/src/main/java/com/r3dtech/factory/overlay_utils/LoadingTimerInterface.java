package com.r3dtech.factory.overlay_utils;

/**
 * This interface is for a timer with progress checks.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 19/01/2018.
 */

public interface LoadingTimerInterface {
    int getGoalTime();
    void update(int deltaTime);
    float progress();
    boolean isDone();
    void reset();
}
