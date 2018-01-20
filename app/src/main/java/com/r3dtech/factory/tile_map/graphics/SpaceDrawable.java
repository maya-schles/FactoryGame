package com.r3dtech.factory.tile_map.graphics;

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
    private static final int WIDTH_BOUND = 1080;
    private static final int HEIGHT_BOUND = 1920;
    private static final int STAR_RADIUS = 5;
    private static Paint starPaint;
    private Rect bounds;
    private Point[] starsLocations = new Point[STARS_NUM];


    public SpaceDrawable() {
        super();

        starPaint = new Paint();
        starPaint.setColor(Color.WHITE);

        Random random = new Random();
        for (int i = 0; i < starsLocations.length; i++) {
            starsLocations[i] = new Point(random.nextInt(WIDTH_BOUND), random.nextInt(HEIGHT_BOUND));
        }
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        float widthScale = bounds.width()/WIDTH_BOUND;
        float heightScale = bounds.height()/HEIGHT_BOUND;
        canvas.save();
        canvas.scale(widthScale, heightScale);
        canvas.drawColor(Color.BLACK);
        for (Point star : starsLocations) {
            canvas.drawCircle(star.x, star.y, STAR_RADIUS, starPaint);
        }
        canvas.restore();
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
        this.bounds = bounds;
    }
}
