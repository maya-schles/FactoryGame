package com.r3dtech.factory.graphics.machines.machine_drawables;

import android.graphics.Color;

import com.r3dtech.factory.graphics.SimpleBitmapDrawable;
import com.r3dtech.factory.logic.inventory.ItemStack;
import com.r3dtech.factory.logic.machines.Machine;

import java.lang.reflect.Constructor;

/**
 * This class is used as a cache for the machines' drawable.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 22/01/2018.
 */
public class MachineDrawableCache {
    public static int GREEN_TYPE_OFFSET = 100;
    private static int COLOR_OFFSET_ALPHA = 127;
    private SimpleBitmapDrawable[] drawables = new SimpleBitmapDrawable[Machine.MachineType.values().length];
    private SimpleBitmapDrawable[] greenDrawables = new SimpleBitmapDrawable[Machine.MachineType.values().length];
    private Class[] drawableClasses = {StoneFurnaceDrawable.class};

    public void load() {
        for (int type = 0; type < drawables.length; type++) {
            try {
                Constructor<SimpleBitmapDrawable> constructor = drawableClasses[type].getConstructor();
                drawables[type] = constructor.newInstance();
            } catch (Exception e) {

            }
        }

        for (int type = 0; type < drawables.length; type++) {
            greenDrawables[type] = drawables[type].offsetColor(Color.GREEN, COLOR_OFFSET_ALPHA);
        }
    }

    public SimpleBitmapDrawable getDrawable(int machineType) {
        if (machineType >= GREEN_TYPE_OFFSET) {
            return greenDrawables[machineType-GREEN_TYPE_OFFSET];
        } else {
            return drawables[machineType];
        }
    }
}
