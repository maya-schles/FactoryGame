package com.r3dtech.factory.overlay_graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import com.r3dtech.factory.framework.ScreenOverlay;
import com.r3dtech.factory.overlay_utils.LoadingTimerInterface;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class is a screen overlay for the resource harvest loading.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 19/01/2018.
 */

public class ResourceLoadingOverlay implements ScreenOverlay {
    private static final int MAX_TIMERS = 10;
    private static final int TIMER_WIDTH = 256;
    private static final int TIMER_HEIGHT = 64;
    private List<LoadingTimerDrawable> loadingTimers = new ArrayList<>();
    private List<LoadingTimerDrawable> loadingTimersBuffer = new ArrayList<>();
    private Canvas canvas;

    public ResourceLoadingOverlay(Bitmap frameBuffer) {
        canvas = new Canvas(frameBuffer);
    }

    public boolean addTimer(int goalTime, Drawable icon) {
        if (loadingTimers.size() + loadingTimersBuffer.size() <= MAX_TIMERS) {
            loadingTimersBuffer.add(new LoadingTimerDrawable(goalTime, icon));
            return true;
        }
        return false;
    }

    @Override
    public void paint() {
        Rect timerBounds = new Rect(0, 0, TIMER_WIDTH, TIMER_HEIGHT);
        for (LoadingTimerDrawable timer : loadingTimers) {
            timer.setBounds(timerBounds);
            timer.draw(canvas);
            timerBounds.offset(0, timerBounds.height());
        }
    }

    @Override
    public void update(int deltaTime) {
        loadingTimers.addAll(loadingTimersBuffer);
        loadingTimersBuffer.clear();
        for (Iterator<LoadingTimerDrawable> itr = loadingTimers.iterator(); itr.hasNext();) {
            LoadingTimerInterface timer = itr.next();
            timer.update(deltaTime);
            if (timer.isDone()) {
                itr.remove();
            }
        }
    }
}
