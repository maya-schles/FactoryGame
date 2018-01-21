package com.r3dtech.factory.framework;

import android.graphics.Bitmap;

/**
 * This interface represents a game and binds everything together.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 18/01/2018.
 */

public interface Game {
    void update(float deltaTime);
    void setScreen(GameScreen screen);
    void setScreenOverlay(ScreenOverlay screenOverlay);
    GameScreen getCurrentScreen();
    ScreenOverlay getCurrentScreenOverlay();
    GameScreen getInitScreen();
    ScreenOverlay getInitScreenOverlay();
    GameInput getInput();
    Bitmap getFrameBuffer();
    ScaleCallback getScaleCallback();
    ScrollCallback getScrollCalback();
    ClickCallback getClickCallback();
    FileIO getFileIO();
    void setMainScreen();
    void setInventoryScreen();
    void setCraftingScreen();
}
