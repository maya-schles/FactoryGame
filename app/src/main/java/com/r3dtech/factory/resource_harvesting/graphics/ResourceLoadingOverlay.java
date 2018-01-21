package com.r3dtech.factory.resource_harvesting.graphics;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

import com.r3dtech.factory.resource_harvesting.ManualHarvestingManager;
import com.r3dtech.factory.framework.ScreenOverlay;
import com.r3dtech.factory.inventory.GameItem;
import com.r3dtech.factory.resource_harvesting.LoadingTimer;
import com.r3dtech.factory.inventory.GameItemDrawableCache;


/**
 * This class is a screen overlay for the resource harvest loading.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 19/01/2018.
 */

public class ResourceLoadingOverlay implements ScreenOverlay {
    private static final int TIMER_WIDTH = 384;
    private static final int TIMER_HEIGHT = 96;
    private static final int TIMERS_DIST = 16;
    private GameItemDrawableCache drawableCache = new GameItemDrawableCache();

    private ManualHarvestingManager harvestingManager;
    private Canvas canvas;
    private Paint numPaint = new Paint(Paint.FAKE_BOLD_TEXT_FLAG);

    public ResourceLoadingOverlay(Bitmap frameBuffer, ManualHarvestingManager harvestingManager, AssetManager assets) {
        this.harvestingManager = harvestingManager;
        canvas = new Canvas(frameBuffer);
        drawableCache.load();
        Typeface numFont = Typeface.createFromAsset(assets, "num_font.ttf");
        numPaint.setTypeface(numFont);
        numPaint.setTextSize(TIMER_HEIGHT/2);
    }

    @Override
    public void paint() {
        Rect timerBounds = new Rect(0, TIMERS_DIST, TIMER_WIDTH, TIMER_HEIGHT);
        LoadingTimer[] timers = harvestingManager.getTimers();
        for (int i = 0; i < timers.length; i++) {
            GameItem item = GameItem.fromInt(i);
            if (harvestingManager.getTimersNum(item) > 0) {
                LoadingTimerDrawable timer= new LoadingTimerDrawable(drawableCache.getDrawable(i),
                        timers[i]);
                timer.setBounds(timerBounds);
                timer.draw(canvas);
                canvas.drawText(Integer.toString(harvestingManager.getTimersNum(item)),
                        timerBounds.left, timerBounds.bottom, numPaint);
                timerBounds.offset(0, timerBounds.height()+TIMERS_DIST);
            }
        }
    }
}
