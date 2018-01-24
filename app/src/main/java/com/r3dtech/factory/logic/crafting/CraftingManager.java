package com.r3dtech.factory.logic.crafting;

import com.r3dtech.factory.logic.inventory.GameItem;
import com.r3dtech.factory.logic.inventory.Inventory;
import com.r3dtech.factory.logic.inventory.ItemStack;
import com.r3dtech.factory.logic.loading_timers.GameItemTimersManager;
import com.r3dtech.factory.logic.loading_timers.LoadingTimer;

import java.util.ArrayList;
import java.util.List;

/**
 * This class manages the crafting.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 23/01/2018.
 */

public class CraftingManager extends GameItemTimersManager {
    private RecipePool recipePool = new RecipePool();
    private Inventory inventory;

    public CraftingManager(GameItemTimerCallback callback, Inventory inventory) {
        super(callback);
        this.inventory = inventory;
    }

    public Recipe[] getRecipes() {
        return recipePool.getRecipes();
    }

    public boolean isAvailable(Recipe recipe) {
        for (ItemStack component : recipe.getComponents()) {
            if (!inventory.contains(component.getItem(), component.getAmount())) {
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
                for(ItemStack component: getRecipe(item).getComponents()) {
                    inventory.decreaseAmount(component.getItem(), component.getAmount());
                }
            }
            timersNum[i] = Math.min(MAX_PER_ITEM, timersNum[i]+1);
        }
        itemBuffer.clear();

        for(LoadingTimer timer: loadingTimers) {
            timer.update(deltaTime);
        }
    }
}
