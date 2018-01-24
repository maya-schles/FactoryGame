package com.r3dtech.factory.graphics.tile_map;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import com.r3dtech.factory.MyGameImplementation;
import com.r3dtech.factory.framework.GameScreen;
import com.r3dtech.factory.graphics.GenericDrawer;
import com.r3dtech.factory.logic.loading_timers.GameItemTimersManager;
import com.r3dtech.factory.logic.tile_map.MapSegmentPerspective;
import com.r3dtech.factory.logic.tile_map.MapTile;
import com.r3dtech.factory.logic.tile_map.TileMap;
import com.r3dtech.factory.logic.tile_map.implementation.GameMapPerspective;

/**
 * This class is a GameScreen that relies on a mapView object.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 18/01/2018.
 */

public class MapScreen implements GameScreen{
    private static final int INVENTORY_BUTTON_WIDTH = 128;
    private static final int INVENTORY_BUTTON_HEIGHT = 128;

    private static final String INVENTORY_BUTTON_FILE = "/res/drawable/inventory.jpg";

    protected Canvas canvas;
    protected Drawable inventoryButton;
    protected MyGameImplementation game;
    private Rect bounds;
    private SpaceDrawable space = new SpaceDrawable();
    protected MapSegmentPerspective perspective;
    protected GenericDrawer<MapSegmentPerspective> drawer = new PerspectiveDrawer(new MapSegmentDrawer());
    private GameItemTimersManager harvestManager;



    public MapScreen(MyGameImplementation game, TileMap map) {
        this.game = game;
        harvestManager = game.getHarvestManager();

        canvas = new Canvas(game.getFrameBuffer());
        bounds = canvas.getClipBounds();
        drawer.setBounds(canvas.getClipBounds());

        inventoryButton = Drawable.createFromStream(this.getClass().
                getResourceAsStream(INVENTORY_BUTTON_FILE), "src");
        inventoryButton.setBounds(canvas.getClipBounds().right-INVENTORY_BUTTON_WIDTH,
                0, canvas.getClipBounds().right, INVENTORY_BUTTON_HEIGHT);

        perspective = new GameMapPerspective(map);

    }


    @Override
    public void paint() {
        // draw space background.
        space.setBounds(bounds);
        space.draw(canvas);

        // draw the map.
        drawer.draw(canvas, perspective);

        // draw the inventory button.
        inventoryButton.draw(canvas);
    }

    @Override
    public void onClick(int x, int y) {
        if (inventoryButton.getBounds().contains(x, y)) {
            game.setInventoryScreen();
            return;
        }


        Point tileLoc = perspective.getTileFromLoc(x - canvas.getWidth()/2, y - canvas.getHeight()/2);
        MapTile tile = perspective.getTile(tileLoc.x, tileLoc.y);
        if(tile == null) {
            return;
        }
        if (tile.getMachine() != null) {
            game.setMachineRotateScreen();
            //game.setMachineScreen(tile.getMachine());
            return;
        }
        if (perspective.getSmallDistFromDiscovered(tileLoc.x, tileLoc.y) < TileMap.SMALL_DISCOVERED_DIST) {
            harvestManager.addTimer(tile.tileType().getResource());
        }
    }

    @Override
    public void onScale(float scale) {
        perspective.zoom(scale);
    }

    @Override
    public void onScroll(float dx, float dy) {
        perspective.movePosFloat(dx, dy);
    }
}
