package com.r3dtech.factory.crafting;

import com.r3dtech.factory.inventory.GameItem;
import com.r3dtech.factory.loading_timers.TimersManager;

/**
 * This interface manages the crafting.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 22/01/2018.
 */

public interface CraftingManager extends TimersManager{
    Recipe[] getRecipes();
    Recipe[] getAvailableRecipes();
    Recipe[] getUnavailableRecipes();
    Recipe getRecipe(GameItem item);
}
