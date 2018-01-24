package com.r3dtech.factory.graphics.tile_map;

import android.graphics.Point;
import android.graphics.drawable.Drawable;

import com.r3dtech.factory.MyGameImplementation;
import com.r3dtech.factory.graphics.machines.machine_drawables.MachineDrawableCache;
import com.r3dtech.factory.logic.machines.GhostMachine;
import com.r3dtech.factory.logic.machines.Machine;
import com.r3dtech.factory.logic.tile_map.MapTile;
import com.r3dtech.factory.logic.tile_map.TileMap;

/**
 * This class is the screen for placing machines.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 24/01/2018.
 */

public class MapMachinePlaceScreen extends MapScreen{
    private static final int DONE_BUTTON_WIDTH = 128;
    private static final int DONE_BUTTON_HEIGHT = 128;

    private static final String DONE_BUTTON_FILE = "/res/drawable/done.jpg";
    private Machine.MachineType type;
    private MapTile machineLoc = null;

    public MapMachinePlaceScreen(MyGameImplementation game, TileMap map, Machine.MachineType type) {
        super(game, map);
        this.type = type;

        inventoryButton = Drawable.createFromStream(this.getClass().
                getResourceAsStream(DONE_BUTTON_FILE), "src");
        inventoryButton.setBounds(canvas.getClipBounds().right- DONE_BUTTON_WIDTH,
                0, canvas.getClipBounds().right, DONE_BUTTON_HEIGHT);
    }

    @Override
    public void onClick(int x, int y) {
        if (inventoryButton.getBounds().contains(x, y)) {
            machineLoc.setMachine(Machine.createMachine(type));
            game.setMapScreen();
            return;
        }

        Point clickTileLoc = perspective.getTileFromLoc(x-canvas.getWidth()/2, y-canvas.getHeight()/2);
        if (machineLoc != null) {
            machineLoc.setMachine(null);
        }
        machineLoc = perspective.getTile(clickTileLoc.x, clickTileLoc.y);
        machineLoc.setMachine(
                new GhostMachine(type.toInt()+ MachineDrawableCache.GREEN_TYPE_OFFSET));
    }
}

