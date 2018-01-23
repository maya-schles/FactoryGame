package com.r3dtech.factory.Machines.graphics;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Rect;

import com.r3dtech.factory.Machines.implementation.StoneFurnace;
import com.r3dtech.factory.framework.Game;

/**
 * This class is a screen for a furnace machine.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 22/01/2018.
 */

public class FurnaceMachineScreen extends MachineScreen {
    private StoneFurnace furnace;
    private Rect inputSlot = new Rect(SLOT_BEGIN_LEFT, SLOT_BEGIN_TOP,
            SLOT_BEGIN_LEFT+SLOT_SIZE, SLOT_BEGIN_TOP +SLOT_SIZE);
    private Rect fuelSlot = new Rect();
    private Rect resultSlot = new Rect();

    public FurnaceMachineScreen(Bitmap frameBuffer, Game game, AssetManager assets, StoneFurnace furnace) {
        super(frameBuffer,game.getInventory(), game, assets);
        this.furnace = furnace;
    }

    @Override
    public void paint() {
        super.paint();
    }

    @Override
    public void onClick(int x, int y) {
        super.onClick(x, y);
    }
}
