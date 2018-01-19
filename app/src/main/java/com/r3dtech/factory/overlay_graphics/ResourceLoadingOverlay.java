package com.r3dtech.factory.overlay_graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.r3dtech.factory.framework.ScreenOverlay;
import com.r3dtech.factory.inventory.GameItem;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

/**
 * This class is a screen overlay for the resource harvest loading.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 19/01/2018.
 */

public class ResourceLoadingOverlay implements ScreenOverlay {
    private static final int MAX_PER_RESOURCE = 10;
    private static final int TIMER_WIDTH = 384;
    private static final int TIMER_HEIGHT = 96;
    private static final int TIMERS_DIST = 5;

    private Map<GameItem, ResourceHarvestLoadingTimer> loadingTimers = new HashMap<>();
    private List<GameItem> itemsBuffer = new ArrayList<>();
    private Canvas canvas;

    public ResourceLoadingOverlay(Bitmap frameBuffer) {
        canvas = new Canvas(frameBuffer);
        ResourceHarvestLoadingTimer.drawableCache.load();
        for (GameItem item : GameItem.values()) {
            loadingTimers.put(item, new ResourceHarvestLoadingTimer(item));
        }
    }

    public void addTimer(GameItem item) {
        if(loadingTimers.get(item).getResourceNum() < MAX_PER_RESOURCE) {
            itemsBuffer.add(item);
        }
    }

    @Override
    public void paint() {
        Rect timerBounds = new Rect(0, 0, TIMER_WIDTH, TIMER_HEIGHT);
        for (ResourceHarvestLoadingTimer timer : loadingTimers.values()) {
            if (timer.getResourceNum() > 0) {
                timer.setBounds(timerBounds);
                timer.draw(canvas);
                timerBounds.offset(0, timerBounds.height()+TIMERS_DIST);
            }
        }
    }

    @Override
    public void update(int deltaTime) {
        for (GameItem item : itemsBuffer) {
            loadingTimers.get(item).add();
        }
        itemsBuffer.clear();

        for (ResourceHarvestLoadingTimer timer : loadingTimers.values()) {
            timer.update(deltaTime);
        }
    }
}
