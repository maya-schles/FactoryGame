package com.r3dtech.factory.graphics.tile_map;

import android.graphics.Point;
import android.graphics.drawable.Drawable;

import com.r3dtech.factory.MyGameImplementation;
import com.r3dtech.factory.logic.loading_timers.GameItemTimersManager;
import com.r3dtech.factory.logic.tile_map.MapTile;
import com.r3dtech.factory.logic.tile_map.TileMap;

/**
 * This class is the main map screen.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 25/01/2018.
 */

public class MapViewScreen extends MapScreen {
    protected static final int BUTTON_WIDTH = 128;
    protected static final int BUTTON_HEIGHT = 128;

    private static final String INVENTORY_BUTTON_FILE = "/res/drawable/inventory.jpg";
    private static final String ROTATE_BUTTON_FILE = "/res/drawable/rotate.jpg";
    private static final String DELETE_BUTTON_FILE = "/res/drawable/delete.jpg";

    private Drawable inventoryButton;
    private Drawable rotateButton;
    private Drawable deleteButton;

    private GameItemTimersManager harvestManager;

    public MapViewScreen(MyGameImplementation game, TileMap map) {
        super(game, map);
        harvestManager = game.getHarvestManager();

        inventoryButton = Drawable.createFromStream(this.getClass().
                getResourceAsStream(INVENTORY_BUTTON_FILE), "src");
        inventoryButton.setBounds(canvas.getClipBounds().right-BUTTON_WIDTH,
                0, canvas.getClipBounds().right, BUTTON_HEIGHT);

        rotateButton = Drawable.createFromStream(this.getClass().
                getResourceAsStream(ROTATE_BUTTON_FILE), "src");
        rotateButton.setBounds(inventoryButton.getBounds().left - BUTTON_WIDTH,
                0, inventoryButton.getBounds().left, BUTTON_HEIGHT);

        deleteButton = Drawable.createFromStream(this.getClass().
                getResourceAsStream(DELETE_BUTTON_FILE), "src");
        deleteButton.setBounds(rotateButton.getBounds().left - BUTTON_WIDTH,
                0, rotateButton.getBounds().left, BUTTON_HEIGHT);
    }

    @Override
    public void paint() {
        super.paint();
        inventoryButton.draw(canvas);
        rotateButton.draw(canvas);
        deleteButton.draw(canvas);
    }

    @Override
    public void onClick(int x, int y) {
        if (inventoryButton.getBounds().contains(x, y)) {
            game.setInventoryScreen();
            return;
        }
        if(rotateButton.getBounds().contains(x, y)) {
            game.setMachineRotateScreen();
        }
        if(deleteButton.getBounds().contains(x, y)) {
            game.setMachineDeleteScreen();
        }
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
}
