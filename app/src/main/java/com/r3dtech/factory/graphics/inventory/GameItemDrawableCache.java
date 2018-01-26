package com.r3dtech.factory.graphics.inventory;

import android.graphics.drawable.Drawable;

import com.r3dtech.factory.graphics.machines.machine_drawables.BurnerHarvesterDrawable;
import com.r3dtech.factory.graphics.machines.machine_drawables.StoneFurnaceDrawable;
import com.r3dtech.factory.logic.inventory.GameItem;

/**
 * This class is used as a cache for the item icons drawables.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 23/01/2018.
 */

public class GameItemDrawableCache {
    private Drawable[] drawables = new Drawable[GameItem.values().length];

    public void load() {
        for (int item = 0; item < drawables.length; item++) {
            if (item == GameItem.BURNER_HARVESTER.toInt()) {
                drawables[item] = new BurnerHarvesterDrawable(0);
            } else {
                drawables[item] = Drawable.createFromStream(this.getClass().getResourceAsStream(
                        "/res/drawable/icon_" + GameItem.values()[item].getName() + ".jpg"), "src");
            }
        }
    }


    public Drawable getDrawable(int gameItem) {
        return drawables[gameItem];
    }

}
