package com.r3dtech.factory.graphics.loading_timers;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import com.r3dtech.factory.graphics.GenericDrawer;
import com.r3dtech.factory.logic.loading_timers.LoadingTimer;

/**
 * This class is used to draw a loading timer with an icon.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 23/01/2018.
 */

public class LoadingTimerDrawer extends GenericDrawer<LoadingTimer> {
    private static final int STROKE_WIDTH = 5;
    private Drawable icon;

    private Paint barPaint = new Paint();
    private Paint progressPaint = new Paint();
    private Paint outlinePaint = new Paint();

    public LoadingTimerDrawer(Drawable icon) {
        this.icon = icon;
        barPaint.setColor(Color.WHITE);
        progressPaint.setColor(Color.GREEN);
        outlinePaint.setColor(Color.BLACK);
        outlinePaint.setStyle(Paint.Style.STROKE);
        outlinePaint.setStrokeWidth(STROKE_WIDTH);
    }

    @Override
    public void draw(Canvas canvas, LoadingTimer object) {
        // draw icon
        Rect iconBounds = new Rect(bounds.left, bounds.top, bounds.left + bounds.height(),
                bounds.bottom);
        icon.setBounds(iconBounds);
        icon.draw(canvas);

        // draw bar
        int barWidth = bounds.width() - bounds.height();
        Rect barBounds = new Rect(iconBounds.right, bounds.top, bounds.left + barWidth,
                bounds.bottom);
        canvas.drawRect(barBounds, barPaint);

        // draw progress
        Rect progressBounds = new Rect(barBounds.left, barBounds.top,
                barBounds.left +  (int ) (object.progress()*barBounds.width()), barBounds.bottom);
        canvas.drawRect(progressBounds, progressPaint);

        // draw outline
        canvas.drawRect(barBounds, outlinePaint);
    }
}
