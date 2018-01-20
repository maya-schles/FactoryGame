package com.r3dtech.factory.resource_harvesting;

/**
 * This interface is for a timer with progress checks.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 19/01/2018.
 */

public interface LoadingTimer {
    int getGoalTime();
    void update(float deltaTime);
    float progress();
    boolean isDone();
    void reset();
}
