package com.r3dtech.factory.resource_harvesting;


/**
 * This interface is used as a callback for when the timer is done.
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 19/01/2018.
 */

public interface TimerCallback {
    void onTimerDone(LoadingTimer timer);
}
