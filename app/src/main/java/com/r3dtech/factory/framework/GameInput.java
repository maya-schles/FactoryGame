package com.r3dtech.factory.framework;

/**
 * An interface for the game's user input handling.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 18/01/2018.
 */

public interface GameInput {
    void setScaleCallback(ScaleCallback callback);
    void setScrollCallback(ScrollCallback callback);
    void setClickCallback(ClickCallback callback);
}
