package com.r3dtech.factory;

import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;

import com.r3dtech.factory.crafting.CraftingManager;
import com.r3dtech.factory.crafting.Recipe;
import com.r3dtech.factory.crafting.graphics.CraftingScreen;
import com.r3dtech.factory.crafting.graphics.RecipeScreen;
import com.r3dtech.factory.crafting.implementation.CraftingManagerImplementation;
import com.r3dtech.factory.resource_harvesting.ManualHarvestingManager;
import com.r3dtech.factory.framework.ClickCallback;
import com.r3dtech.factory.framework.GameScreen;
import com.r3dtech.factory.framework.ScaleCallback;
import com.r3dtech.factory.framework.ScreenOverlay;
import com.r3dtech.factory.framework.ScrollCallback;
import com.r3dtech.factory.framework.implementation.AndroidGame;
import com.r3dtech.factory.inventory.GameItem;
import com.r3dtech.factory.inventory.Inventory;
import com.r3dtech.factory.inventory.graphics.InventoryScreen;
import com.r3dtech.factory.tile_map.graphics.MapScreen;
import com.r3dtech.factory.tile_map.graphics.MapViewDrawable;
import com.r3dtech.factory.loading_timers.graphics.LoadingTimersOverlay;
import com.r3dtech.factory.tile_map.TileMap;
import com.r3dtech.factory.tile_map.implementation.GameMap;

import java.io.IOException;


/**
 * The app's main activity.
 */
public class GameImplementation extends AndroidGame {
    private MapViewDrawable mapView;
    private Inventory inventory = new Inventory();
    private ManualHarvestingManager harvestingManager = new ManualHarvestingManager(inventory);
    private CraftingManager craftingManager;

    private class mScaleCallback implements ScaleCallback {
        @Override
        public void onScale(float scale) {
            mapView.zoom(scale);
        }
    }

    private class mScrollCallback implements ScrollCallback {
        @Override
        public void onScroll(float dx, float dy) {
            mapView.movePosFloat(dx, dy);
        }
    }

    private class mClickCallback implements ClickCallback {
        @Override
        public void onClick(int x, int y) {
            getCurrentScreen().onClick(x, y);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getRealSize(size);
        try {
            inventory.loadFromFile(getFileIO());
        }
        catch (Exception e) {
            inventory.increaseAmount(GameItem.STONE, 10);
            //throw new RuntimeException("Couldn't load inventory from file");
        }
        craftingManager = new CraftingManagerImplementation(inventory);
        inventory.clear();
    }

    private TileMap createMap() {
        TileMap map;
        try {
            map = new GameMap();
        }catch (IOException e) {
            throw new RuntimeException("Map file not found");
        }
        Log.d("WORLD MAP:", map.toString());
        return map;
    }
    @Override
    public GameScreen getInitScreen() {
        TileMap map = createMap();
        mapView = new MapViewDrawable(map, this);
        return new MapScreen(mapView, getFrameBuffer(), this);
    }

    @Override
    public void update(float deltaTime) {
        mapView.update();
        harvestingManager.update(deltaTime);
        craftingManager.update(deltaTime);
    }

    @Override
    public ScaleCallback getScaleCallback() {
        return new mScaleCallback();
    }

    @Override
    public ScrollCallback getScrollCalback() {
        return new mScrollCallback();
    }

    @Override
    public ClickCallback getClickCallback() {
        return new mClickCallback();
    }

    @Override
    public ScreenOverlay getInitScreenOverlay() {
        return new LoadingTimersOverlay(getFrameBuffer(), harvestingManager, getAssets());
    }

    public void manualHarvestResource(GameItem resource) {
        if (resource != null) {
            harvestingManager.addTimer(resource);
        }
    }

    @Override
    public void setMainScreen() {
        setScreen(new MapScreen(mapView, getFrameBuffer(), this));
        setScreenOverlay(new LoadingTimersOverlay(getFrameBuffer(), harvestingManager, getAssets()));
    }

    @Override
    public void setInventoryScreen() {
        setScreen(new InventoryScreen(getFrameBuffer(), inventory, this, getAssets()));
        setScreenOverlay(new LoadingTimersOverlay(getFrameBuffer(), craftingManager, getAssets()));
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            inventory.saveToFile(getFileIO());
        } catch (IOException e) {
            Log.d("GAME ", "unable to load inventory to file");
        }
    }

    @Override
    public void setCraftingScreen() {
        setScreen(new CraftingScreen(getFrameBuffer(), this, getAssets(), craftingManager));
        setScreenOverlay(new LoadingTimersOverlay(getFrameBuffer(), craftingManager, getAssets()));
    }

    @Override
    public void setRecipeScreen(Recipe recipe) {
        setScreen(new RecipeScreen(getFrameBuffer(), this, getAssets(), craftingManager, recipe));
        setScreenOverlay(new LoadingTimersOverlay(getFrameBuffer(), craftingManager, getAssets()));
    }
}
