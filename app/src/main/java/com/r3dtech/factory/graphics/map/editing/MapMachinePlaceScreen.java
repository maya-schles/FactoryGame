package com.r3dtech.factory.graphics.map.editing;

import android.graphics.Point;

import com.r3dtech.factory.MyGameImplementation;
import com.r3dtech.factory.logic.machines.Machine;
import com.r3dtech.factory.logic.machines.MachineState;
import com.r3dtech.factory.logic.machines.MachineType;
import com.r3dtech.factory.logic.tile_map.MapTile;
import com.r3dtech.factory.logic.tile_map.TileMap;

/**
 * This class is the screen for placing machines.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 24/01/2018.
 */

public class MapMachinePlaceScreen extends MapEditScreen{
    private Machine machine;
    private MapTile machineLoc = null;

    public MapMachinePlaceScreen(MyGameImplementation game, TileMap map, MachineType type) {
        super(game, map);
        machine = Machine.createMachine(type);
    }

    public void onGetClick(int x, int y) {
        Point clickTileLoc = perspective.getTileFromLoc(x-canvas.getWidth()/2, y-canvas.getHeight()/2);
        MapTile newMachineLoc = perspective.getTile(clickTileLoc.x, clickTileLoc.y);
        if (newMachineLoc == null || newMachineLoc.getMachine() != null) {
            return;
        }
        if (machineLoc != null) {
            machineLoc.setMachine(null);
        }
        machineLoc = newMachineLoc;
        if (perspective.isDiscovered(clickTileLoc.x, clickTileLoc.y)) {
            machine.setState(MachineState.GREEN);
        }
        else {
            machine.setState(MachineState.RED);
        }
        machineLoc.setMachine(machine);
    }

    @Override
    protected boolean drawArrows() {
        return false;
    }

    @Override
    void done() {
        game.getInventory().decreaseAmount(Machine.getItem(machine.getType()),1);
        machine.setState(MachineState.NORMAL);
    }

    @Override
    void cancel() {
        if (machineLoc != null) {
            machineLoc.setMachine(null);
        }
    }
}

