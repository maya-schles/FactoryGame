package com.r3dtech.factory.graphics.inventory;

import android.content.res.AssetManager;
import android.graphics.Rect;

import com.r3dtech.factory.graphics.ButtonCallback;
import com.r3dtech.factory.graphics.DrawableButton;

/**
 * This class is a menu button.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 25/01/2018.
 */

public class MenuButton extends DrawableButton {
    private static final int BUTTON_WIDTH = 384;
    private static final int BUTTON_HEIGHT = 96;

    public MenuButton(ButtonCallback callback, String text, boolean isSelected, AssetManager assets) {
        super(new MenuButtonDrawable(text, isSelected, assets), callback);
    }

    public static void setButtonBounds(DrawableButton[] buttons) {
        Rect buttonBounds = new Rect(0, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
        for (DrawableButton button : buttons) {
            button.setBounds(buttonBounds);
            buttonBounds.offset(BUTTON_WIDTH, 0);
        }
    }
}
