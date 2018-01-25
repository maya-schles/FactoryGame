package com.r3dtech.factory.graphics;

import android.graphics.Canvas;

import com.r3dtech.factory.MyGame;
import com.r3dtech.factory.framework.GameScreen;

/**
 * This class is a master class to all screens with buttons.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 25/01/2018.
 */

public abstract class ScreenWithButtons implements GameScreen {
    protected MyGame game;
    protected Canvas canvas;

    private DrawableButton[] buttons;

    protected ScreenWithButtons(MyGame game) {
        this.game = game;
        this.canvas = new Canvas(game.getFrameBuffer());
        buttons = getButtons();
    }

    protected void drawButtons() {
        for (DrawableButton button : buttons) {
            button.draw(canvas);
        }
    }

    @Override
    public void onClick(int x, int y) {
        for (DrawableButton button: buttons) {
            if (button.getBounds().contains(x, y)) {
                button.onPress();
            }
        }
    }

    protected abstract DrawableButton[] getButtons();
}
