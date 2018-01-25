package com.r3dtech.factory.graphics.map;


import com.r3dtech.factory.MyGameImplementation;
import com.r3dtech.factory.graphics.GenericDrawer;
import com.r3dtech.factory.graphics.ScreenWithButtons;
import com.r3dtech.factory.logic.tile_map.MapSegmentPerspective;
import com.r3dtech.factory.logic.tile_map.TileMap;
import com.r3dtech.factory.logic.tile_map.implementation.GameMapPerspective;

/**
 * This class is a GameScreen that shows a tile map perspective.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 18/01/2018.
 */

public abstract class MapScreen extends ScreenWithButtons{
    private GenericDrawer<MapSegmentPerspective> drawer;
    private SpaceDrawable space = new SpaceDrawable();
    protected MapSegmentPerspective perspective;

    public MapScreen(MyGameImplementation game, TileMap map) {
        super(game);
        drawer = new PerspectiveDrawer(new MapSegmentDrawer(drawArrows()));
        drawer.setBounds(canvas.getClipBounds());
        perspective = new GameMapPerspective(map);
        space.setBounds(canvas.getClipBounds());
    }

    protected abstract boolean drawArrows();

    @Override
    public void paint() {
        space.draw(canvas);
        drawer.draw(canvas, perspective);
        super.drawButtons();
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
