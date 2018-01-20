package com.r3dtech.factory.ResourceHarvesting;

/**
 * This class serves as a timer with progress checks.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 19/01/2018.
 */

public class LoadingTimerImplementation implements LoadingTimer {
    private int goalTime;
    private int currTime = 0;
    private TimerCallback callback;
    private boolean isRunning = true;

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
    public void update(int deltaTime) {
        currTime += deltaTime;
        currTime = Math.min(currTime, goalTime);
        if (isDone() && isRunning) {
            callback.onTimerDone(this);
            isRunning = false;
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
