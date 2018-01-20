package com.r3dtech.factory.resource_harvesting;

/**
 * This class serves as a timer with progress checks.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 19/01/2018.
 */

public class LoadingTimerImplementation implements LoadingTimer {
    private int goalTime;
    private float currTime = 0;
    private TimerCallback callback;
    private boolean isRunning = false;

    public LoadingTimerImplementation(int goalTime, TimerCallback callback) {
        this.goalTime = goalTime;
        this.currTime = goalTime;
        this.callback = callback;
    }

    @Override
    public int getGoalTime() {
        return goalTime;
    }

    @Override
    public void update(float deltaTime) {
        if (isRunning) {
            currTime += deltaTime;
            currTime = Math.min(currTime, goalTime);
            if (isDone()) {
                isRunning = false;
                callback.onTimerDone(this);
            }
        }
    }

    @Override
    public float progress() {
        return currTime/(float) goalTime;
    }

    @Override
    public boolean isDone() {
        return currTime == goalTime;
    }

    @Override
    public void reset() {
        currTime = 0;
        isRunning = true;
    }

}
