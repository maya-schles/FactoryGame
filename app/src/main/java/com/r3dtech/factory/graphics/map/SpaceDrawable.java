package com.r3dtech.factory.graphics.map;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Random;

/**
 * This class is a drawable of space.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 17/01/2018.
 */

public class SpaceDrawable extends Drawable {
    private static final int STARS_NUM = 1000;
    private static final int WIDTH = 1080;
    private static final int HEIGHT = 1920;
    private static final int STAR_RADIUS = 5;

    private static Paint starPaint = new Paint();
    private Point[] starsLocations = new Point[STARS_NUM];


    SpaceDrawable() {
        super();

        starPaint.setColor(Color.WHITE);
        initRandomStarLocs();
    }

    private void initRandomStarLocs() {
        Random random = new Random();
        for (int i = 0; i < starsLocations.length; i++) {
            starsLocations[i] = new Point(random.nextInt(WIDTH), random.nextInt(HEIGHT));
        }
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        Rect bounds = getBounds();

        float widthScale = bounds.width()/ WIDTH;
        float heightScale = bounds.height()/ HEIGHT;

        canvas.save();
        canvas.scale(widthScale, heightScale);
        canvas.drawColor(Color.BLACK);
        drawStars(canvas);
        canvas.restore();
    }

    private void drawStars(Canvas canvas) {
        for (Point star : starsLocations) {
            canvas.drawCircle(star.x, star.y, STAR_RADIUS, starPaint);
        }
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
}
