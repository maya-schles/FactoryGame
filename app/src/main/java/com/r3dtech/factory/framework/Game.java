package com.r3dtech.factory.framework;

import android.graphics.Bitmap;

/**
 * This interface represents a game and binds everything together.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 18/01/2018.
 */

public interface Game {

    public void update(float deltaTime);

    public void setScreen(GameScreen screen);

    public GameScreen getCurrentScreen();

    public GameScreen getInitScreen();

    public Bitmap getFrameBuffer();
}
