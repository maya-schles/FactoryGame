package com.r3dtech.factory.map_graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;

import com.r3dtech.factory.GameImplementation;
import com.r3dtech.factory.framework.Game;
import com.r3dtech.factory.framework.GameScreen;
import com.r3dtech.factory.overlay_graphics.EmptyOverlay;

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
            ((GameImplementation) game).manualHarvestResource(
                    mapView.getTileFromLoc(x - canvas.getWidth()/2, y-canvas.getHeight()/2).tileType().getResource());
        }
    }
}
