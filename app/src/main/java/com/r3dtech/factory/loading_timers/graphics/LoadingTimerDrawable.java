package com.r3dtech.factory.loading_timers.graphics;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.r3dtech.factory.loading_timers.LoadingTimer;

/**
 * This class is a drawable loading timer.
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 19/01/2018.
 */

public class LoadingTimerDrawable extends Drawable implements LoadingTimer {
    private static final int STROKE_WIDTH = 5;
    private LoadingTimer timer;
    private Drawable icon;
    private Rect bounds;
    private Paint barPaint = new Paint();
    private Paint progressPaint = new Paint();
    private Paint outlinePaint = new Paint();

    public LoadingTimerDrawable(Drawable icon, LoadingTimer timer) {
        super();
        this.timer = timer;
        this.icon = icon; // icon must be square!
        barPaint.setColor(Color.WHITE);
        progressPaint.setColor(Color.GREEN);
        outlinePaint.setColor(Color.BLACK);
        outlinePaint.setStyle(Paint.Style.STROKE);
        outlinePaint.setStrokeWidth(STROKE_WIDTH);
    }
    @Override
    public void draw(@NonNull Canvas canvas) {
        Rect newIconBounds = new Rect(bounds.left, bounds.top, bounds.left + bounds.height(),
                bounds.bottom);
        icon.setBounds(newIconBounds);
        icon.draw(canvas);
        int barWidth = bounds.width() - bounds.height();
        Rect barBounds = new Rect(newIconBounds.right, bounds.top, bounds.left + barWidth,
                bounds.bottom);
        canvas.drawRect(barBounds, barPaint);
        Rect progressBounds = new Rect(barBounds.left, barBounds.top,
                barBounds.left +  (int ) (progress()*barBounds.width()), barBounds.bottom);
        canvas.drawRect(progressBounds, progressPaint);
        canvas.drawRect(barBounds, outlinePaint);
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
    public void update(float deltaTime) {
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
