package com.r3dtech.factory.logic.loading_timers;

/**
 * This class is a loading timer with progress checks.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 23/01/2018.
 */

public class LoadingTimer {
    private int goalTime;
    private float currTime = 0;
    private TimerCallback callback;
    private boolean isRunning = false;

    public LoadingTimer(int goalTime, TimerCallback callback) {
        this.goalTime = goalTime;
        this.currTime = goalTime;
        this.callback = callback;
    }

    public int getGoalTime() {
        return goalTime;
    }

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

    public float progress() {
        return currTime/(float) goalTime;
    }

    private boolean isDone() {
        return currTime == goalTime;
    }

    public void reset() {
        currTime = 0;
        isRunning = true;
    }
}
