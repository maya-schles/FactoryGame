package com.r3dtech.factory.tile_map;

import android.graphics.Point;

/**
 * This class is for Drawing the map on a screen with a bird's eye perspective,
 * given scale and position.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 14/01/2018.
 */

public interface Perspective {
    public float getScale();
    public void setScale(float scale);
    public void zoom(float scale);
    public Point getPosition();
    public void setPosition(Point point);
    public void movePosition(Point point);
}
