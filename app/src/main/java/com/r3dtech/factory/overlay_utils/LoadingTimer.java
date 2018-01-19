package com.r3dtech.factory.overlay_utils;

/**
 * This class serves as a timer with progress checks.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 19/01/2018.
 */

public class LoadingTimer implements LoadingTimerInterface{
    private int goalTime;
    private int currTime = 0;

    public LoadingTimer(int goalTime) {
        this.goalTime = goalTime;
    }

    @Override
    public int getGoalTime() {
        return goalTime;
    }

    @Override
    public void update(int deltaTime) {
        currTime += deltaTime;
    }

    @Override
    public float progress() {
        return currTime/goalTime;
    }
}
