package com.r3dtech.factory.graphics.map.tile_drawables;

import android.util.Log;

import com.r3dtech.factory.logic.tile_map.TileType;
import com.r3dtech.factory.graphics.SimpleBitmapDrawable;
import com.r3dtech.factory.logic.tile_map.implementation.Constants;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * This class is used as a cache to store the tile drawables.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 16/01/2018.
 */

public class TileDrawableCache {
    private static final String TAG = "TILE_DRAWABLE_CACHE";
    private static final Class[] drawableClasses = {
            ForestDrawable.class,
            GrassDrawable.class,
            StoneDrawable.class,
            CoalDrawable.class,
            IronDrawable.class};

    private SimpleBitmapDrawable[][] drawables = new SimpleBitmapDrawable[TileType.values().length][Constants.TILE_VARIETY];

    public void load() {
        for (int type = 0; type < drawables.length; type++) {
            for (int ver = 0; ver < drawables[0].length; ver++) {
                if (drawableClasses[type] != null) {
                    try {
                        Constructor<SimpleBitmapDrawable> constructor = drawableClasses[type].getConstructor(Integer.class);
                        drawables[type][ver] = constructor.newInstance(ver);
                    } catch (NoSuchMethodException e) {
                        Log.d(TAG, "Couldn't find constructor in class " + drawableClasses[type]);
                    } catch (IllegalAccessException e) {
                        Log.d(TAG, "Couldn't access constructor in class " + drawableClasses[type]);
                    } catch (InvocationTargetException e) {
                        Log.d(TAG, "Constructor threw except. class " + drawableClasses[type]);
                    } catch (InstantiationException e) {
                        Log.d(TAG, "Couldn't instantiate class " + drawableClasses[type]);
                    }
                }
            }
        }
    }

    public SimpleBitmapDrawable getDrawable(int tileType, int ver) {
        return drawables[tileType][ver];
    }
}
