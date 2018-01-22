package com.r3dtech.factory.Machines.graphics;

import com.r3dtech.factory.Machines.Machine;
import com.r3dtech.factory.tile_map.TileType;
import com.r3dtech.factory.tile_map.graphics.FogDrawable;
import com.r3dtech.factory.tile_map.graphics.ForestDrawable;
import com.r3dtech.factory.tile_map.graphics.GrassDrawable;
import com.r3dtech.factory.tile_map.graphics.SimpleBitmapDrawable;
import com.r3dtech.factory.tile_map.graphics.StoneDrawable;
import com.r3dtech.factory.tile_map.implementation.Constants;

import java.lang.reflect.Constructor;

/**
 * This class is used as a cache for the machines' drawable.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 22/01/2018.
 */

public class MachineDrawableCache {
    private SimpleBitmapDrawable[] drawables = new SimpleBitmapDrawable[Machine.MachineType.values().length];
    Class[] drawableClasses = {StoneFurnaceDrawable.class};
    public void load() {
        for (int type = 0; type < drawables.length; type++) {
            try {
                Constructor<SimpleBitmapDrawable> constructor = drawableClasses[type].getConstructor();
                drawables[type] = constructor.newInstance();
            } catch (Exception e) {
                continue;
            }

        }
    }

    public SimpleBitmapDrawable getDrawable(int MachineType) {
        return drawables[MachineType];
    }
}
