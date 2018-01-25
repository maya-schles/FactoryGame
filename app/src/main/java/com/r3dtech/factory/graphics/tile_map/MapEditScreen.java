package com.r3dtech.factory.graphics.tile_map;

import android.graphics.Color;
import android.graphics.drawable.Drawable;

import com.r3dtech.factory.MyGameImplementation;
import com.r3dtech.factory.graphics.SimpleBitmapDrawable;
import com.r3dtech.factory.logic.tile_map.TileMap;

/**
 * This class is a master class for all map editing screens.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 25/01/2018.
 */

public abstract class MapEditScreen extends MapScreen {
    private static final int BUTTON_WIDTH = 128;
    private static final int BUTTON_HEIGHT = 128;
    private static final String DONE_BUTTON_FILE = "/res/drawable/done.jpg";

    private Drawable doneButton;
    private Drawable cancelButton;

    MapEditScreen(MyGameImplementation game, TileMap map) {
        super(game, map);

        doneButton= Drawable.createFromStream(this.getClass().
                getResourceAsStream(DONE_BUTTON_FILE), "src");
        doneButton.setBounds(canvas.getClipBounds().right- BUTTON_WIDTH,
                0, canvas.getClipBounds().right, BUTTON_HEIGHT);

        int[][] bitmap  = {
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 1, 1, 1, 1, 0, 1},
                {1, 1, 0, 1, 1, 0, 1, 1},
                {1, 1, 1, 0, 0, 1, 1, 1},
                {1, 1, 1, 0, 0, 1, 1, 1},
                {1, 1, 0, 1, 1, 0, 1, 1},
                {1, 0, 1, 1, 1, 1, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1},};
        int[] colors = {Color.RED};
        cancelButton = new SimpleBitmapDrawable(bitmap, colors);
        cancelButton.setBounds(doneButton.getBounds().left - BUTTON_WIDTH,
                0, doneButton.getBounds().left, BUTTON_HEIGHT);
    }

    @Override
    public void paint() {
        super.paint();
        doneButton.draw(canvas);
        cancelButton.draw(canvas);
    }

    @Override
    public void onClick(int x, int y) {
        if (doneButton.getBounds().contains(x,y)) {
            done();
            game.setMapScreen();
            return;
        }
        if (cancelButton.getBounds().contains(x, y)) {
            cancel();
            game.setMapScreen();
            return;
        }
        onGetClick(x, y);
    }

    abstract void onGetClick(int x, int y);

    abstract void done();
    abstract void cancel();
}
