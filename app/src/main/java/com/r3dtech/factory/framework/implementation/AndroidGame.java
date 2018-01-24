package com.r3dtech.factory.framework.implementation;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;

import com.r3dtech.factory.framework.ClickCallback;
import com.r3dtech.factory.framework.FileIO;
import com.r3dtech.factory.framework.Game;
import com.r3dtech.factory.framework.GameInput;
import com.r3dtech.factory.framework.GameScreen;
import com.r3dtech.factory.framework.ScaleCallback;
import com.r3dtech.factory.framework.ScreenOverlay;
import com.r3dtech.factory.framework.ScrollCallback;

import java.util.ArrayList;
import java.util.List;


/**
 * This class is used to manage an android game.
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 18/01/2018.
 */

public abstract class AndroidGame extends AppCompatActivity implements Game{
    protected AndroidFastRenderView renderView;
    private GameScreen screen;
    private ScreenOverlay screenOverlay;
    private Bitmap frameBuffer;
    private FileIO fileIO;

    private List<Float> scaleEvents = new ArrayList<>();
    private List<Pair<Float, Float>> scrollEvents = new ArrayList<>();
    private List<Point> clickEvents = new ArrayList<>();

    private ScaleCallback scaleCallback = new ScaleCallback() {
        @Override
        public void onScale(float scale) {
            scaleEvents.add(scale);
        }
    };
    private ScrollCallback scrollCallback = new ScrollCallback() {
        @Override
        public void onScroll(float dx, float dy) {
            scrollEvents.add(new Pair<>(dx, dy));
        }
    };
    private ClickCallback clickCallback = new ClickCallback() {
        @Override
        public void onClick(int x, int y) {
            clickEvents.add(new Point(x, y));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fileIO = new AndroidFileIO(this);

        frameBuffer = Bitmap.createBitmap(1080, 1920, Bitmap.Config.RGB_565);

    }

    protected void onPostCreate() {
        screen = getInitScreen();
        screenOverlay = getInitScreenOverlay();
        renderView = new AndroidFastRenderView(this);
        setContentView(renderView);
        hide();
        GameInput input = new AndroidInput(this, renderView);
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

    @Override
    public FileIO getFileIO() {
        return fileIO;
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

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    public void setScreenOverlay(ScreenOverlay screenOverlay) {
        this.screenOverlay = screenOverlay;
    }

    @Override
    public ScreenOverlay getCurrentScreenOverlay() {
        return screenOverlay;
    }

    @Override
    public ScaleCallback getScaleCallback() {
        return scaleCallback;
    }

    @Override
    public ScrollCallback getScrollCalback() {
        return scrollCallback;
    }

    @Override
    public ClickCallback getClickCallback() {
        return clickCallback;
    }

    @Override
    public void update(float deltaTime) {
        List<Float> currScaleEvents = new ArrayList<>(scaleEvents);
        List<Pair<Float, Float>> currScrollEvents = new ArrayList<>(scrollEvents);
        List<Point> currClickEvents = new ArrayList<>(clickEvents);

        for (Float scale: currScaleEvents) {
            screen.onScale(scale);
        }
        scaleEvents.clear();

        for (Pair<Float, Float> deltas : currScrollEvents) {
            screen.onScroll(deltas.first, deltas.second);
        }
        scrollEvents.clear();

        for (Point click: currClickEvents) {
            screen.onClick(click.x, click.y);
        }
        clickEvents.clear();
    }
}
