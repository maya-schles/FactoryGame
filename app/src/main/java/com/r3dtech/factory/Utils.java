package com.r3dtech.factory;

import android.graphics.Point;
import android.graphics.Rect;

/**
 * A class for general utility function.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 14/01/2018.
 */

public class Utils {
    public static Rect createCenteredRect(Point center, int width, int height) {
        int left = center.x - width/2;
        int top = center.y - height/2;
        int right = center.x + width/2;
        int bottom = center.y + height/2;
        return new Rect(left, top, right, bottom);
    }

    public static Point pointMul(Point point, float scalar) {
        return new Point((int) (point.x*scalar), (int) (point.y*scalar));
    }

    public static Point offset(Point a, Point b) {
        return new Point(a.x + b.x, a.y + b.y);
    }
}
