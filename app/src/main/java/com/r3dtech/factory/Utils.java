package com.r3dtech.factory;

import android.graphics.Point;
import android.graphics.Rect;

import java.util.Arrays;
import java.util.List;

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
    public static Point offset(Point a, Point b) {
        return new Point(a.x + b.x, a.y + b.y);
    }
    public static <T> T[] concat(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }
    public static int[] IntegerListToIntArray(List<Integer> list) {
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}
