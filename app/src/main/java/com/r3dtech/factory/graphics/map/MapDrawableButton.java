package com.r3dtech.factory.graphics.map;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import com.r3dtech.factory.graphics.ButtonCallback;
import com.r3dtech.factory.graphics.DrawableButton;

/**
 * This class is a DrawableButton for a map screen.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 25/01/2018.
 */

public class MapDrawableButton extends DrawableButton {
    private static final int BUTTON_WIDTH = 128;
    private static final int BUTTON_HEIGHT = 128;

    public MapDrawableButton(Drawable drawable, ButtonCallback buttonCallback) {
        super(drawable, buttonCallback);
    }

    public MapDrawableButton(String drawableFile, ButtonCallback buttonCallback) {
        super(drawableFile, buttonCallback);
    }

    public static void setButtonBounds(Rect canvasBounds, DrawableButton[] buttons) {
        Rect buttonBounds = new Rect(canvasBounds.right-BUTTON_WIDTH,
                0, canvasBounds.right, BUTTON_HEIGHT);
        for (DrawableButton button : buttons) {
            button.setBounds(buttonBounds);
            buttonBounds.offset(-BUTTON_WIDTH, 0);
        }
    }
}
