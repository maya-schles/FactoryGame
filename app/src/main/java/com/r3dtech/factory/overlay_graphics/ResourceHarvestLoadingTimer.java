package com.r3dtech.factory.overlay_graphics;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;

import com.r3dtech.factory.inventory.GameItem;
import com.r3dtech.factory.inventory.GameItemDrawableCache;

/**
 * This class is a loading timer for a manual resource harvest.
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 19/01/2018.
 */

public class ResourceHarvestLoadingTimer extends LoadingTimerDrawable {
    static GameItemDrawableCache drawableCache = new GameItemDrawableCache();
    private int resourceNum = 0;
    private Paint numPaint = new Paint(Paint.FAKE_BOLD_TEXT_FLAG);
    private static int goalTime = 300;

    public ResourceHarvestLoadingTimer(GameItem item) {
        super(goalTime, drawableCache.getDrawable(item.toInt()));
        numPaint.setColor(Color.BLACK);
    }

    @Override
    public void update(int deltaTime) {
        super.update(deltaTime);
        if (isDone()) {
            resourceNum = Math.max(resourceNum -1, 0);
            reset();
        }
        if (resourceNum == 0) {
            reset();
        }
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        if (resourceNum > 0) {
            super.draw(canvas);
            Rect bounds = getBounds();
            numPaint.setTextSize(bounds.height()*3/4);
            canvas.drawText(Integer.toString(resourceNum), bounds.left, bounds.bottom, numPaint);
        }
    }

    public int getResourceNum() {
        return resourceNum;
    }

    public void add() {
        resourceNum++;
    }
}
