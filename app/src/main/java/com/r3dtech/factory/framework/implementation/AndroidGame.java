package com.r3dtech.factory.framework.implementation;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.r3dtech.factory.framework.Game;
import com.r3dtech.factory.framework.GameInput;
import com.r3dtech.factory.framework.GameScreen;

/**
 * This class is used to manage an android game.
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 18/01/2018.
 */

public abstract class AndroidGame extends AppCompatActivity implements Game{
    protected AndroidFastRenderView renderView;
    private GameScreen screen;
    private Bitmap frameBuffer;
    private GameInput input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        frameBuffer = Bitmap.createBitmap(1080, 1920, Bitmap.Config.RGB_565);
        screen = getInitScreen();
        renderView = new AndroidFastRenderView(this);
        setContentView(renderView);
        input = new AndroidInput(this, renderView);
        input.setClickCallback(getClickCallback());
        input.setScaleCallback(getScaleCallback());
        input.setScrollCallback(getScrollCalback());
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

    @Override
    public GameInput getInput() {
        return input;
    }

    @Override
    protected void onResume() {
        super.onResume();
        renderView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        renderView.pause();
    }
}
