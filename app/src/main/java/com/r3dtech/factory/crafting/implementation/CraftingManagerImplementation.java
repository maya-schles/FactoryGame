package com.r3dtech.factory.crafting.implementation;

import android.util.Pair;

import com.r3dtech.factory.crafting.CraftingManager;
import com.r3dtech.factory.crafting.Recipe;
import com.r3dtech.factory.crafting.RecipePool;
import com.r3dtech.factory.framework.Game;
import com.r3dtech.factory.inventory.GameItem;
import com.r3dtech.factory.inventory.Inventory;
import com.r3dtech.factory.loading_timers.GameItemTimersManager;
import com.r3dtech.factory.loading_timers.LoadingTimer;

import java.util.ArrayList;
import java.util.List;

/**
 * This is an implementation of the CraftingManager interface.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 22/01/2018.
 */

public class CraftingManagerImplementation extends GameItemTimersManager implements CraftingManager {
    private static final int MAX_PER_RECIPE = 10;

    private RecipePool recipePool;

    public CraftingManagerImplementation(Inventory inventory) {
        super(inventory);
        this.recipePool = new AndroidRecipePool();
    }
    @Override
    public Recipe[] getRecipes() {
        return recipePool.getRecipes();
    }

    @Override
    public boolean isAvailable(Recipe recipe) {
        for (Pair<GameItem, Integer> component : recipe.getComponents()) {
            if (!inventory.contains(component.first, component.second)) {
                return false;
            }
        }
        return true;
    }

    public Recipe getRecipe(GameItem item) {
        for (Recipe recipe : recipePool) {
            if (recipe.getProduct() == item) {
                return recipe;
            }
        }
        return null;
    }

    @Override
    public Recipe[] getAvailableRecipes() {
        List<Recipe> res = new ArrayList<>();
        for (Recipe recipe : recipePool) {
            if (isAvailable(recipe)) {
                res.add(recipe);
            }
        }
        Recipe[] resArray = new Recipe[res.size()];
        res.toArray(resArray);
        return resArray;
    }

    @Override
    public Recipe[] getUnavailableRecipes() {
        List<Recipe> res = new ArrayList<>();
        for (Recipe recipe : recipePool) {
            if (!isAvailable(recipe)) {
                res.add(recipe);
            }
        }
        Recipe[] resArray = new Recipe[res.size()];
        res.toArray(resArray);
        return resArray;
    }

    @Override
    public void addTimer(GameItem item) {
        if (getRecipe(item) != null && isAvailable(getRecipe(item))) {
            itemBuffer.add(item);
        }
    }

    @Override
    public void update(float deltaTime) {
        for (GameItem item: itemBuffer) {
            int i = item.toInt();
            if (timersNum[i] == 0) {
                loadingTimers[i].reset();
            }
            if (timersNum[i] < 10) {
                for(Pair<GameItem, Integer> component: getRecipe(item).getComponents()) {
                    inventory.decreaseAmount(component.first, component.second);
                }
            }
            timersNum[i] = Math.min(MAX_PER_RECIPE, timersNum[i]+1);
        }
        itemBuffer.clear();

        for(LoadingTimer timer: loadingTimers) {
            timer.update(deltaTime);
        }
    }
}
