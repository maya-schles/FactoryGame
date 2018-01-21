package com.r3dtech.factory.tile_map.graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import com.r3dtech.factory.GameImplementation;
import com.r3dtech.factory.framework.Game;
import com.r3dtech.factory.framework.GameScreen;
import com.r3dtech.factory.resource_harvesting.graphics.EmptyOverlay;
import com.r3dtech.factory.tile_map.MapTile;

/**
 * This class is a GameScreen that relies on a mapView object.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 18/01/2018.
 */

public class MapScreen implements GameScreen{
    private static final int INVENTORY_BUTTON_WIDTH = 128;
    private static final int INVENTORY_BUTTON_HEIGHT = 128;
    private MapViewDrawable mapView;
    private Canvas canvas;
    private Drawable inventoryButton;
    private Game game;

    public MapScreen(MapViewDrawable drawable, Bitmap frameBuffer, Game game) {
        this.game = game;
        this.mapView = drawable;
        canvas = new Canvas(frameBuffer);
        drawable.setBounds(canvas.getClipBounds());
        inventoryButton = Drawable.createFromStream(this.getClass().
                getResourceAsStream("/res/drawable/inventory.jpg"), "src");
        inventoryButton.setBounds(canvas.getWidth()-INVENTORY_BUTTON_WIDTH,
                0, canvas.getWidth(), INVENTORY_BUTTON_HEIGHT);
    }

    @Override
    public void paint() {
        mapView.draw(canvas);
        inventoryButton.draw(canvas);
    }

    @Override
    public void onClick(int x, int y) {
        if (inventoryButton.getBounds().contains(x, y)) {
            game.setInventoryScreen();
            game.setScreenOverlay(new EmptyOverlay());
        }
        else {
            MapTile tile = mapView.getTileFromLoc(x - canvas.getWidth()/2, y-canvas.getHeight()/2);
            if (tile != null &&
                    mapView.isLocDiscovered(x - canvas.getWidth()/2, y-canvas.getHeight()/2)) {
                ((GameImplementation) game).manualHarvestResource(
                        tile.tileType().getResource());
            }
        }
    }
}
