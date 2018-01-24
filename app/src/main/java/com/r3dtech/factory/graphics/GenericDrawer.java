package com.r3dtech.factory.graphics;

import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * This interface is for a generic object drawer.
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 23/01/2018.
 */

public abstract class GenericDrawer<T> {
    protected Rect bounds;

    public void setBounds(Rect bounds){
        this.bounds = new Rect(bounds);
    }

    public abstract void draw(Canvas canvas, T object);
}
