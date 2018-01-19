package com.r3dtech.factory.inventory;

import android.graphics.drawable.Drawable;


/**
 * This class is used as a cache for GameItem drawables.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 19/01/2018.
 */

public class GameItemDrawableCache {
    private Drawable[] drawables = new Drawable[GameItem.values().length];

    public void load() {
        for (int item = 0; item < drawables.length; item++) {
                drawables[item] = Drawable.createFromStream(this.getClass().getResourceAsStream(
                        "/res/drawable/icon_"+GameItem.values()[item].getName()+".jpg"), "src");
        }
    }


    public Drawable getDrawable(int gameItem) {
        return drawables[gameItem];
    }
}
