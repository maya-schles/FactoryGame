package com.r3dtech.factory.tile_map.graphics;

import android.graphics.drawable.Drawable;

import com.r3dtech.factory.tile_map.TileType;
import com.r3dtech.factory.tile_map.implementation.Constants;

/**
 * This class is used as a cache to store the tile drawables.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 16/01/2018.
 */

public class TileDrawableCache {
    private Drawable[][] drawables = new Drawable[TileType.values().length][Constants.TILE_VARIETY];
    void load() {
        for (int type = 0; type < drawables.length; type++) {
            for (int ver = 0; ver < drawables[0].length; ver++) {
                if (type == TileType.GRASS.toInt()) {
                    drawables[type][ver] = new GrassDrawable(ver);
                }
                else if (type == TileType.STONE.toInt()) {
                    drawables[type][ver] = new StoneDrawable(ver);
                }
                else {
                    drawables[type][ver] = Drawable.createFromStream(this.getClass().getResourceAsStream(
                            "/res/drawable/" + TileType.values()[type].getName() + ver + ".jpg"), "src");
                }
            }
        }
    }

    Drawable getDrawable(int tileType, int ver) {
        return drawables[tileType][ver];
    }
}
