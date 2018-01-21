package com.r3dtech.factory.tile_map.graphics;

import android.graphics.drawable.Drawable;

import com.r3dtech.factory.tile_map.TileType;
import com.r3dtech.factory.tile_map.implementation.Constants;

import java.lang.reflect.Constructor;

/**
 * This class is used as a cache to store the tile drawables.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 16/01/2018.
 */

public class TileDrawableCache {
    private SimpleBitmapDrawable[][] drawables = new SimpleBitmapDrawable[TileType.values().length][Constants.TILE_VARIETY];
    Class[] drawableClasses = { ForestDrawable.class, GrassDrawable.class, StoneDrawable.class, FogDrawable.class};
    void load() {
        for (int type = 0; type < drawables.length; type++) {
            for (int ver = 0; ver < drawables[0].length; ver++) {
                if (drawableClasses[type] != null) {
                    try {
                        Constructor<SimpleBitmapDrawable> constructor = drawableClasses[type].getConstructor(Integer.class);
                        drawables[type][ver] = constructor.newInstance(ver);
                    } catch (Exception e) {
                        continue;
                    }
                }
            }
        }
    }

    SimpleBitmapDrawable getDrawable(int tileType, int ver) {
        return drawables[tileType][ver];
    }
}
