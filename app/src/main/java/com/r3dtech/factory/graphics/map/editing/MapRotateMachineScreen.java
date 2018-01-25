package com.r3dtech.factory.graphics.map.editing;

import android.graphics.Point;

import com.r3dtech.factory.MyGameImplementation;
import com.r3dtech.factory.logic.tile_map.MapTile;
import com.r3dtech.factory.logic.tile_map.TileMap;

/**
 * This class is the screen for the machine rotations.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 25/01/2018.
 */

public class MapRotateMachineScreen extends MapEditScreen{
    public MapRotateMachineScreen(MyGameImplementation game, TileMap map) {
        super(game, map);
    }

    public void onGetClick(int x, int y) {
        Point clickTileLoc = perspective.getTileFromLoc(x-canvas.getWidth()/2, y-canvas.getHeight()/2);
        MapTile tile = perspective.getTile(clickTileLoc.x, clickTileLoc.y);
        if (tile != null && tile.getMachine() != null) {
            tile.getMachine().rotate();
        }
    }

    @Override
    protected boolean drawArrows() {
        return true;
    }

    @Override
    void done() {

    }

    @Override
    void cancel() {
        // Todo: implement method.
    }
}
