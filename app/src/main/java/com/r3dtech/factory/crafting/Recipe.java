package com.r3dtech.factory.crafting;

import android.util.Pair;

import com.r3dtech.factory.inventory.GameItem;

/**
 * This interface represents a crafting recipe.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 21/01/2018.
 */

public interface Recipe {
    GameItem getProduct();
    Pair<GameItem, Integer>[] getComponents();
}
