package com.r3dtech.factory.logic.crafting;

import com.r3dtech.factory.logic.inventory.GameItem;
import com.r3dtech.factory.logic.inventory.ItemStack;

/**
 * This class represents a crafting recipe.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 23/01/2018.
 */

public class Recipe {
    private GameItem product;
    private ItemStack[] components;

    public Recipe(GameItem product, ItemStack[] components) {
        this.product = product;
        this.components = components;
    }

    public GameItem getProduct() {
        return product;
    }

    public ItemStack[] getComponents() {
        return components;
    }
}
