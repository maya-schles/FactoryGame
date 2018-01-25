package com.r3dtech.factory.graphics.map;

import android.graphics.Point;

import com.r3dtech.factory.MyGameImplementation;
import com.r3dtech.factory.graphics.DrawableButton;
import com.r3dtech.factory.logic.loading_timers.GameItemTimersManager;
import com.r3dtech.factory.logic.tile_map.MapTile;
import com.r3dtech.factory.logic.tile_map.TileMap;

/**
 * This class is the main map screen.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 25/01/2018.
 */

public class MapViewScreen extends MapScreen {
    private static final String INVENTORY_BUTTON_FILE = "/res/drawable/inventory.jpg";
    private static final String ROTATE_BUTTON_FILE = "/res/drawable/rotate.jpg";
    private static final String DELETE_BUTTON_FILE = "/res/drawable/delete.jpg";

    private GameItemTimersManager harvestManager;

    public MapViewScreen(MyGameImplementation game, TileMap map) {
        super(game, map);
        harvestManager = game.getHarvestManager();
    }

    @Override
    public void onClick(int x, int y) {
        super.onClick(x, y);
        Point tileLoc = perspective.getTileFromLoc(x - canvas.getWidth()/2, y - canvas.getHeight()/2);
        MapTile tile = perspective.getTile(tileLoc.x, tileLoc.y);
        if(tile == null) {
            return;
        }
        if (tile.getMachine() != null) {
            game.setMachineScreen(tile.getMachine());
            return;
        }
        if (perspective.getSmallDistFromDiscovered(tileLoc.x, tileLoc.y) < TileMap.SMALL_DISCOVERED_DIST) {
            harvestManager.addTimer(tile.tileType().getResource());
        }
    }

    @Override
    protected boolean drawArrows() {
        return false;
    }

    @Override
    protected DrawableButton[] getButtons() {
        DrawableButton inventoryButton = new MapDrawableButton(INVENTORY_BUTTON_FILE, game::setInventoryScreen);
        DrawableButton rotateButton = new MapDrawableButton(ROTATE_BUTTON_FILE, game::setMachineRotateScreen);
        DrawableButton deleteButton = new MapDrawableButton(DELETE_BUTTON_FILE, game::setMachineDeleteScreen);
        DrawableButton[] res = {inventoryButton, rotateButton, deleteButton};
        MapDrawableButton.setButtonBounds(canvas.getClipBounds(), res);
        return res;
    }
}
