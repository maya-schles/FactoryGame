package com.r3dtech.factory.graphics.machines;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.r3dtech.factory.graphics.GenericDrawer;

/**
 * This class is used to draw a bar of any color.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 24/01/2018.
 */

public class BarDrawer extends GenericDrawer<Float> {
    private static final int STROKE_WIDTH = 5;

    private Paint barPaint = new Paint();
    private Paint progressPaint = new Paint();
    private Paint outlinePaint = new Paint();

    public BarDrawer(int color) {
        barPaint.setColor(Color.WHITE);
        progressPaint.setColor(color);
        outlinePaint.setColor(Color.BLACK);
        outlinePaint.setStyle(Paint.Style.STROKE);
        outlinePaint.setStrokeWidth(STROKE_WIDTH);
    }

    @Override
    public void draw(Canvas canvas, Float object) {
        canvas.drawRect(bounds, barPaint);

        // draw progress
        Rect progressBounds = new Rect(bounds.left, bounds.top,
                bounds.left +  (int) (object*bounds.width()), bounds.bottom);
        canvas.drawRect(progressBounds, progressPaint);

        // draw outline
        canvas.drawRect(bounds, outlinePaint);
    }
}
