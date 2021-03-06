package com.r3dtech.factory.graphics;


import android.graphics.drawable.Drawable;

import com.r3dtech.factory.graphics.inventory.GameItemDrawableCache;
import com.r3dtech.factory.graphics.machines.machine_drawables.MachineDrawableCache;
import com.r3dtech.factory.graphics.map.tile_drawables.TileDrawableCache;
import com.r3dtech.factory.logic.machines.OutputDirection;

/**
 * This class holds all the drawable caches.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 23/01/2018.
 */

public class DrawableCaches {
    private static TileDrawableCache tileDrawableCache = new TileDrawableCache();
    private static GameItemDrawableCache itemDrawableCache = new GameItemDrawableCache();
    private static MachineDrawableCache machineDrawableCache = new MachineDrawableCache();

    public static boolean load() {
        tileDrawableCache.load();
        itemDrawableCache.load();
        machineDrawableCache.load();
        return true;
    }

    public static SimpleBitmapDrawable getTile(int type, int ver) {
        return tileDrawableCache.getDrawable(type, ver);
    }

    public static Drawable getItemIcon(int item) {
        return itemDrawableCache.getDrawable(item);
    }

    public static Drawable getMachine(int machine, OutputDirection direction) {
        return machineDrawableCache.getDrawable(machine, direction);
    }
}
