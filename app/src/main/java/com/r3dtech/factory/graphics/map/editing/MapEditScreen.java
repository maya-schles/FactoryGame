package com.r3dtech.factory.graphics.map.editing;

import android.graphics.Color;

import com.r3dtech.factory.MyGameImplementation;
import com.r3dtech.factory.graphics.DrawableButton;
import com.r3dtech.factory.graphics.SimpleBitmapDrawable;
import com.r3dtech.factory.graphics.map.MapDrawableButton;
import com.r3dtech.factory.graphics.map.MapScreen;
import com.r3dtech.factory.logic.tile_map.TileMap;

/**
 * This class is a master class for all map editing screens.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 25/01/2018.
 */

public abstract class MapEditScreen extends MapScreen {
    private static final String DONE_BUTTON_FILE = "/res/drawable/done.jpg";

    private static final int[][] cancelButtonBitmap  = {
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 1, 1, 1, 1, 0, 1},
            {1, 1, 0, 1, 1, 0, 1, 1},
            {1, 1, 1, 0, 0, 1, 1, 1},
            {1, 1, 1, 0, 0, 1, 1, 1},
            {1, 1, 0, 1, 1, 0, 1, 1},
            {1, 0, 1, 1, 1, 1, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},};
    private static final int[] cancelButtonColors = {Color.RED};

    private boolean isScreenDone = false;

    MapEditScreen(MyGameImplementation game, TileMap map) {
        super(game, map);
    }

    @Override
    public void paint() {
        super.paint();
    }

    @Override
    public void onClick(int x, int y) {
        super.onClick(x, y);
        if (!isScreenDone) {
            onGetClick(x, y);
        }
        else {
            game.setMapScreen();
        }
    }

    abstract void onGetClick(int x, int y);

    private void doneMaster() {
        done();
        isScreenDone = true;
    }

    private void cancelMaster() {
        cancel();
        isScreenDone = true;
    }

    abstract void done();
    abstract void cancel();

    @Override
    protected DrawableButton[] getButtons() {
        DrawableButton doneButton = new MapDrawableButton(DONE_BUTTON_FILE, this::doneMaster);
        DrawableButton cancelButton = new MapDrawableButton(
                new SimpleBitmapDrawable(cancelButtonBitmap, cancelButtonColors), this::cancelMaster);
        DrawableButton[] res = {doneButton, cancelButton};
        MapDrawableButton.setButtonBounds(canvas.getClipBounds(), res);
        return res;
    }
}
