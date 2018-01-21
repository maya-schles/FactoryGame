package com.r3dtech.factory.crafting.implementation;

import android.util.Pair;

import com.r3dtech.factory.crafting.Recipe;
import com.r3dtech.factory.inventory.GameItem;

/**
 * This class implements the Recipe Interface.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 21/01/2018.
 */

public class RecipeImplementation implements Recipe{
    private GameItem product;
    private Pair<GameItem, Integer>[] components;

    public RecipeImplementation(GameItem product, Pair<GameItem, Integer>[] components) {
        this.product = product;
        this.components = components;
    }

    @Override
    public GameItem getProduct() {
        return product;
    }

    @Override
    public Pair<GameItem, Integer>[] getComponents() {
        return components;
    }
}
