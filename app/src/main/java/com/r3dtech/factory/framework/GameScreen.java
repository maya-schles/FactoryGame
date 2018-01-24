package com.r3dtech.factory.framework;

/**
 * This interface represents a screen of the game.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 18/01/2018.
 */

public interface GameScreen {
    void paint();
    void onClick(int x, int y);
    void onScale(float scale);
    void onScroll(float dx, float dy);
}
