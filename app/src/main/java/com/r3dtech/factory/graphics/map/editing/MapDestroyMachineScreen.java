package com.r3dtech.factory.graphics.map.editing;

import android.graphics.Point;

import com.r3dtech.factory.MyGameImplementation;
import com.r3dtech.factory.logic.inventory.ItemStack;
import com.r3dtech.factory.logic.machines.Machine;
import com.r3dtech.factory.logic.machines.MachineState;
import com.r3dtech.factory.logic.tile_map.MapTile;
import com.r3dtech.factory.logic.tile_map.TileMap;

import java.util.ArrayList;
import java.util.List;


/**
 * This is a screen for destroying machines.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 25/01/2018.
 */

public class MapDestroyMachineScreen extends MapEditScreen {
    private List<MapTile> tileList = new ArrayList<>();

    public MapDestroyMachineScreen(MyGameImplementation game, TileMap map) {
        super(game, map);
    }

    public void onGetClick(int x, int y) {
        Point clickTileLoc = perspective.getTileFromLoc(x-canvas.getWidth()/2, y-canvas.getHeight()/2);
        MapTile clickedTile = perspective.getTile(clickTileLoc.x, clickTileLoc.y);

        if (tileList.contains(clickedTile)) {
            clickedTile.getMachine().setState(MachineState.NORMAL);
            tileList.remove(clickedTile);
        }
        else {
            if (clickedTile.getMachine() != null) {
                clickedTile.getMachine().setState(MachineState.RED);
                tileList.add(clickedTile);
            }
        }
    }

    @Override
    protected boolean drawArrows() {
        return false;
    }

    @Override
    void done() {
        for (MapTile tile : tileList) {
            Machine machine = tile.getMachine();
            game.getInventory().increaseAmount(Machine.getItem(machine.getType()), 1);
            for (ItemStack stack : machine.getItems()) {
                game.getInventory().addItemStack(stack);
            }
            tile.setMachine(null);
        }
    }

    @Override
    void cancel() {
        for (MapTile tile : tileList) {
            tile.getMachine().setState(MachineState.NORMAL);
        }
    }
}
