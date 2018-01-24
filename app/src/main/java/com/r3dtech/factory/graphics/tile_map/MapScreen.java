package com.r3dtech.factory.graphics.tile_map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.r3dtech.factory.MyGameImplementation;
import com.r3dtech.factory.framework.GameScreen;
import com.r3dtech.factory.graphics.GenericDrawer;
import com.r3dtech.factory.logic.tile_map.MapSegmentPerspective;
import com.r3dtech.factory.logic.tile_map.TileMap;
import com.r3dtech.factory.logic.tile_map.implementation.GameMapPerspective;

/**
 * This class is a GameScreen that shows a tile map perspective.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 18/01/2018.
 */

public abstract class MapScreen implements GameScreen{
    protected Canvas canvas;
    protected MyGameImplementation game;
    private Rect bounds;
    protected GenericDrawer<MapSegmentPerspective> drawer;
    private SpaceDrawable space = new SpaceDrawable();
    protected MapSegmentPerspective perspective;

    public MapScreen(MyGameImplementation game, TileMap map) {
        this.game = game;

        canvas = new Canvas(game.getFrameBuffer());
        bounds = canvas.getClipBounds();
        drawer = new PerspectiveDrawer(new MapSegmentDrawer(drawArrows()));
        drawer.setBounds(canvas.getClipBounds());

        perspective = new GameMapPerspective(map);

    }

    protected abstract boolean drawArrows();

    @Override
    public void paint() {
        // draw space background.
        space.setBounds(bounds);
        space.draw(canvas);

        // draw the map.
        drawer.draw(canvas, perspective);
    }

    @Override
    public void onScale(float scale) {
        perspective.zoom(scale);
    }

    @Override
    public void onScroll(float dx, float dy) {
        perspective.movePosFloat(dx, dy);
    }
}
