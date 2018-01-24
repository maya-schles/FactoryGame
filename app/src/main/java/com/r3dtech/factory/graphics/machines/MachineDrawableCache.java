package com.r3dtech.factory.graphics.machines;

import com.r3dtech.factory.graphics.SimpleBitmapDrawable;
import com.r3dtech.factory.logic.machines.Machine;

import java.lang.reflect.Constructor;

/**
 * This class is used as a cache for the machines' drawable.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 22/01/2018.
 */
public class MachineDrawableCache {
    private SimpleBitmapDrawable[] drawables = new SimpleBitmapDrawable[Machine.MachineType.values().length];
    private Class[] drawableClasses = {StoneFurnaceDrawable.class};

    public void load() {
        for (int type = 0; type < drawables.length; type++) {
            try {
                Constructor<SimpleBitmapDrawable> constructor = drawableClasses[type].getConstructor();
                drawables[type] = constructor.newInstance();
            } catch (Exception e) {

            }

        }
    }

    public SimpleBitmapDrawable getDrawable(int MachineType) {
        return drawables[MachineType];
    }
}
