package com.r3dtech.factory.Machines.graphics;

import android.content.res.AssetManager;
import android.graphics.Bitmap;

import com.r3dtech.factory.Machines.implementation.StoneFurnace;
import com.r3dtech.factory.framework.Game;

/**
 * This class is a screen for a furnace machine.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 22/01/2018.
 */

public class FurnaceMachineScreen extends MachineScreen {
    private StoneFurnace furnace;

    public FurnaceMachineScreen(Bitmap frameBuffer, Game game, AssetManager assets, StoneFurnace furnace) {
        super(frameBuffer, game, assets);
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
