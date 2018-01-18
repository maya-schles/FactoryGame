package com.r3dtech.factory.framework.implementation;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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
        hide();
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
        hide();
    }

    @Override
    protected void onPause() {
        super.onPause();
        renderView.pause();
    }


    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        renderView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

}
