package com.r3dtech.factory.graphics;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * This class is a drawable button.
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 25/01/2018.
 */

public abstract class DrawableButton extends Drawable {
    private Drawable drawable;
    private ButtonCallback buttonCallback;

    public DrawableButton(Drawable drawable, ButtonCallback buttonCallback) {
        this.drawable = drawable;
        this.buttonCallback = buttonCallback;
    }

    public DrawableButton(String drawableFile, ButtonCallback buttonCallback) {
        drawable = Drawable.createFromStream(getClass().getResourceAsStream(drawableFile), "src");
        this.buttonCallback = buttonCallback;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        drawable.draw(canvas);
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
        super.onBoundsChange(bounds);
        drawable.setBounds(bounds);
    }

    public void onPress() {
        buttonCallback.onPress();
    }
}
