package com.r3dtech.factory.logic.loading_timers;

/**
 * This interface is for a callback to be called when the timer is done.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 23/01/2018.
 */

public interface TimerCallback {
    void onTimerDone(LoadingTimer timer);
}
