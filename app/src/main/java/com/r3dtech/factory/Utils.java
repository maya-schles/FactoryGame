package com.r3dtech.factory;

import android.graphics.Point;
import android.graphics.Rect;
import android.util.SparseIntArray;

import com.r3dtech.factory.logic.tile_map.implementation.Constants;

import java.util.List;
import java.util.Random;

/**
 * A class for general utility function.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 14/01/2018.
 */

public class Utils {
    private static SparseIntArray hash = new SparseIntArray();
    private static boolean isHashLoaded = loadHash();

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

    private static boolean loadHash() {
        Random random = new Random();
        for (int i = 0; i < Math.max(Constants.MAP_WIDTH, Constants.MAP_HEIGHT); i++) {
            hash.put(i, random.nextInt());
        }
        return true;
    }

    public static int hash(int a, int b) {
        a = hash.get(a);
        b = hash.get(b);
        return Math.abs(hash.get(Math.abs(a*b)%Constants.MAP_HEIGHT));
    }

    public static int[] IntegerListToIntArray(List<Integer> list) {
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}
