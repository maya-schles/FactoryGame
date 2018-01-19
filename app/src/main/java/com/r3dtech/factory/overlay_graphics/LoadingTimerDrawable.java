package com.r3dtech.factory.overlay_graphics;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.r3dtech.factory.overlay_utils.LoadingTimer;
import com.r3dtech.factory.overlay_utils.LoadingTimerInterface;

/**
 * This class is a drawable loading timer.
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 19/01/2018.
 */

public class LoadingTimerDrawable extends Drawable implements LoadingTimerInterface {
    private LoadingTimerInterface timer;
    private Drawable icon;
    private Rect bounds;
    private Paint barPaint = new Paint();
    private Paint progressPaint = new Paint();

    public LoadingTimerDrawable(int goalTime, Drawable icon) {
        super();
        timer = new LoadingTimer(goalTime);
        this.icon = icon; // icon must be square!
        barPaint.setColor(Color.WHITE);
        progressPaint.setColor(Color.GREEN);

    }
    @Override
    public void draw(@NonNull Canvas canvas) {
        Rect newIconBounds = new Rect(bounds.left, bounds.top, bounds.left + bounds.height(),
                bounds.bottom);
        icon.setBounds(newIconBounds);
        icon.draw(canvas);
        int barWidth = bounds.width() - bounds.height();
        RectF barBounds = new RectF(newIconBounds.right, bounds.top, bounds.left + barWidth,
                bounds.bottom);
        int rx = bounds.height()/4;
        int ry = rx;
        canvas.drawRoundRect(barBounds, rx, ry, barPaint);
        RectF progressBounds = new RectF(barBounds.left, barBounds.top,
                barBounds.left + progress()*barBounds.width(), barBounds.bottom);
        canvas.drawRoundRect(progressBounds, rx, ry, progressPaint);
    }

    @Override
    public void setAlpha(int i) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        this.bounds = bounds; //bounds width must be greater than bounds height.
    }
    @Override
    public int getGoalTime() {
        return timer.getGoalTime();
    }

    @Override
    public void update(int deltaTime) {
        timer.update(deltaTime);
    }

    @Override
    public float progress() {
        return timer.progress();
    }

    @Override
    public boolean isDone() {
        return timer.isDone();
    }

    @Override
    public void reset() {
        timer.reset();
    }
}
