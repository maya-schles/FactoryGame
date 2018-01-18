package com.r3dtech.factory.framework.implementation;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.r3dtech.factory.framework.Game;
import com.r3dtech.factory.framework.GameScreen;

/**
 * This class is used to manage an android game.
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 18/01/2018.
 */

public abstract class AndroidGame extends AppCompatActivity implements Game{
    private GameScreen screen;
    private Bitmap frameBuffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        frameBuffer = Bitmap.createBitmap(1080, 1920, Bitmap.Config.RGB_565);
        screen = getInitScreen();

    }

    @Override
    public void setScreen(GameScreen screen) {
        this.screen = screen;
    }

    @Override
    public GameScreen getCurrentScreen() {
        return screen;
    }

    @Override
    public Bitmap getFrameBuffer() {
        return frameBuffer;
    }
}
