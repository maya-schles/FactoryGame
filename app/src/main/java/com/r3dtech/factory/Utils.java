package com.r3dtech.factory;

import android.graphics.Point;
import android.graphics.Rect;

import com.r3dtech.factory.tile_map.implementation.Constants;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * A class for general utility function.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 14/01/2018.
 */

public class Utils {
    private static Map<Integer, Integer> hash = new HashMap<>();
    public static Rect createCenteredRect(Point center, int width, int height) {
        int left = center.x - width/2;
        int top = center.y - height/2;
        int right = center.x + width/2;
        int bottom = center.y + height/2;
        return new Rect(left, top, right, bottom);
    }

    public static Point offset(Point a, Point b) {
        return new Point(a.x + b.x, a.y + b.y);
    }

    public static void loadHash() {
        Random random = new Random();
        for (int i = 0; i < Constants.MAP_HEIGHT; i++) {
            hash.put(i, random.nextInt());
        }
    }
    public static int hash(int a, int b) {
        a = hash.get(a);
        b = hash.get(b);
        return Math.abs(hash.get(Math.abs(a*b)%Constants.MAP_HEIGHT));
    }
}
