package com.r3dtech.factory.graphics.machines;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import com.r3dtech.factory.graphics.GenericDrawer;
import com.r3dtech.factory.logic.machines.Machine;

/**
 * This class is used to draw the output direction arrow.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 25/01/2018.
 */

public class DirectionDrawer extends GenericDrawer<Machine.OutputDirection> {
    @Override
    public void draw(Canvas canvas, Machine.OutputDirection object) {
        Drawable drawable = new ArrowBitmapDrawable(object);
        drawable.setBounds(bounds);
        drawable.draw(canvas);
    }
}
