package com.r3dtech.factory;


import android.os.Bundle;
import android.util.Log;

import com.r3dtech.factory.framework.GameScreen;
import com.r3dtech.factory.framework.ScreenOverlay;
import com.r3dtech.factory.framework.implementation.AndroidEmptyOverlay;
import com.r3dtech.factory.framework.implementation.AndroidGame;
import com.r3dtech.factory.graphics.DrawableCaches;
import com.r3dtech.factory.graphics.SimpleBitmapDrawable;
import com.r3dtech.factory.graphics.crafting.CraftingScreen;
import com.r3dtech.factory.graphics.crafting.RecipeScreen;
import com.r3dtech.factory.graphics.inventory.InventoryScreen;
import com.r3dtech.factory.graphics.loading_timers.LoadingTimersOverlay;
import com.r3dtech.factory.graphics.machines.MachineScreen;
import com.r3dtech.factory.graphics.machines.machine_drawables.StoneFurnaceScreen;
import com.r3dtech.factory.graphics.tile_map.MapMachinePlaceScreen;
import com.r3dtech.factory.logic.crafting.CraftingManager;
import com.r3dtech.factory.logic.crafting.Recipe;
import com.r3dtech.factory.logic.inventory.GameItem;
import com.r3dtech.factory.logic.inventory.Inventory;
import com.r3dtech.factory.logic.loading_timers.GameItemTimersManager;
import com.r3dtech.factory.logic.machines.Machine;
import com.r3dtech.factory.logic.machines.StoneFurnace;
import com.r3dtech.factory.logic.tile_map.TileMap;
import com.r3dtech.factory.graphics.tile_map.MapScreen;
import com.r3dtech.factory.logic.tile_map.implementation.GameMap;

import java.io.IOException;


/**
 * This is the main game. it ties everything together.
 */
public class MyGameImplementation extends AndroidGame implements MyGame {
    private TileMap map;
    private Inventory inventory = new Inventory();
    private GameItemTimersManager.GameItemTimerCallback addItemCallback = new GameItemTimersManager.GameItemTimerCallback() {
        @Override
        public void onTimerDone(GameItem item) {
            inventory.increaseAmount(item, 1);
        }
    };

    private GameItemTimersManager harvestManager = new GameItemTimersManager(addItemCallback);

    private CraftingManager craftingManager = new CraftingManager(addItemCallback, inventory);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            inventory.loadFromFile(getFileIO());
        } catch (Exception e) {
            Log.d("INVENTORY_LOADING", "couldn't load inventory from file");
        }
        map = generateMap();
        if (inventory.getAmount(GameItem.STONE_FURNACE) == 0) {
            inventory.increaseAmount(GameItem.STONE_FURNACE, 1);
        }

        DrawableCaches.load();
        onPostCreate();
    }

    @Override
    protected void onPause() {
        super.onPause();
        String TAG = "ON_PAUSE";
        try {
            inventory.saveToFile(getFileIO());
        } catch (IOException e) {
            Log.d(TAG, "couldn't save inventory");
        }
        try {
            ((GameMap) map).saveMachines(getFileIO());
        } catch (IOException e) {
            Log.d(TAG, "couldn't save machines");
        }
    }

    private TileMap generateMap() {
        String TAG = "MAP_GENERATION";
        GameMap map;
        try {
            map = new GameMap();
        } catch (IOException e) {
            Log.d(TAG, "Failed to load game map "+e.getMessage());
            return null;
        }
        try {
            map.loadMachines(getFileIO());
        } catch (IOException e) {
            Log.d(TAG, "couldn't load machines, couldn't read file");
        } catch (ClassNotFoundException e) {
            Log.d(TAG, "couldn't load machines, couldn't generate a machine");
        }
        return map;
    }

    private void updateMachines(float deltaTime) {
        for(Machine machine: map.getMachines()) {
            machine.process(deltaTime);
        }
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        harvestManager.update(deltaTime);
        craftingManager.update(deltaTime);
        updateMachines(deltaTime);
    }

    @Override
    public GameScreen getInitScreen() {
        return new MapScreen(this, map);
    }

    @Override
    public ScreenOverlay getInitScreenOverlay() {
        return new LoadingTimersOverlay(getFrameBuffer(), harvestManager, getAssets());
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public GameItemTimersManager getHarvestManager() {
        return harvestManager;
    }

    @Override
    public CraftingManager getCraftingManager() {
        return craftingManager;
    }

    @Override
    public void setMapScreen() {
        setScreen(getInitScreen());
        setScreenOverlay(getInitScreenOverlay());
    }

    @Override
    public void setInventoryScreen() {
        setScreen(new InventoryScreen(this));
        setScreenOverlay(new LoadingTimersOverlay(getFrameBuffer(), craftingManager, getAssets()));
    }

    @Override
    public void setCraftingScreen() {
        setScreen(new CraftingScreen(this));
        setScreenOverlay(new LoadingTimersOverlay(getFrameBuffer(), craftingManager, getAssets()));
    }

    @Override
    public void setRecipeScreen(Recipe recipe) {
        setScreen(new RecipeScreen(this, recipe));
        setScreenOverlay(new LoadingTimersOverlay(getFrameBuffer(), craftingManager, getAssets()));
    }

    @Override
    public void setMachineScreen(Machine machine) {
        GameScreen screen = null;
        switch (Machine.MachineType.fromInt(machine.getType())) {
            case STONE_FURNACE:
                screen = new StoneFurnaceScreen(this, (StoneFurnace) machine);
                break;
        }
        setScreen(screen);
        setScreenOverlay(new AndroidEmptyOverlay());
    }

    @Override
    public void setMachinePlaceScreen(Machine.MachineType type) {
        setScreen(new MapMachinePlaceScreen(this, map, type));
        setScreenOverlay(new AndroidEmptyOverlay());
    }
}
