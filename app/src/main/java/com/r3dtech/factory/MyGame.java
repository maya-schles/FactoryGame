package com.r3dtech.factory;

import android.content.res.AssetManager;

import com.r3dtech.factory.framework.Game;
import com.r3dtech.factory.logic.crafting.CraftingManager;
import com.r3dtech.factory.logic.crafting.Recipe;
import com.r3dtech.factory.logic.inventory.Inventory;
import com.r3dtech.factory.logic.loading_timers.GameItemTimersManager;
import com.r3dtech.factory.logic.machines.Machine;
import com.r3dtech.factory.logic.machines.MachineType;

/**
 * This interface is the interface that binds al aspects of the game together.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 24/01/2018.
 */

public interface MyGame extends Game{
    Inventory getInventory();
    CraftingManager getCraftingManager();
    GameItemTimersManager getHarvestManager();
    AssetManager getAssets();
    void setMapScreen();
    void setInventoryScreen();
    void setCraftingScreen();
    void setRecipeScreen(Recipe recipe);
    void setMachineScreen(Machine machine);
    void setMachinePlaceScreen(MachineType type);
    void setMachineRotateScreen();
    void setMachineDeleteScreen();
}
