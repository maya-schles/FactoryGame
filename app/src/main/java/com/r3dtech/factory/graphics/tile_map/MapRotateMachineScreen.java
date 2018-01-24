package com.r3dtech.factory.graphics.tile_map;

import android.graphics.Point;
import android.graphics.drawable.Drawable;

import com.r3dtech.factory.MyGameImplementation;
import com.r3dtech.factory.logic.tile_map.TileMap;

/**
 * This class is the screen for the machine rotations.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 25/01/2018.
 */

public class MapRotateMachineScreen extends MapScreen{
    private static final int DONE_BUTTON_WIDTH = 128;
    private static final int DONE_BUTTON_HEIGHT = 128;

    private static final String DONE_BUTTON_FILE = "/res/drawable/done.jpg";

    public MapRotateMachineScreen(MyGameImplementation game, TileMap map) {
        super(game, map);

        inventoryButton = Drawable.createFromStream(this.getClass().
                getResourceAsStream(DONE_BUTTON_FILE), "src");
        inventoryButton.setBounds(canvas.getClipBounds().right- DONE_BUTTON_WIDTH,
                0, canvas.getClipBounds().right, DONE_BUTTON_HEIGHT);

        drawer = new PerspectiveDrawer(new MapSegmentDrawer(true));
        drawer.setBounds(canvas.getClipBounds());
    }

    @Override
    public void onClick(int x, int y) {
        if (inventoryButton.getBounds().contains(x, y)) {
            game.setMapScreen();
            return;
        }
        Point clickTileLoc = perspective.getTileFromLoc(x-canvas.getWidth()/2, y-canvas.getHeight()/2);
        perspective.getTile(clickTileLoc.x, clickTileLoc.y).getMachine().rotate();
    }
}
