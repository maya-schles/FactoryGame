package com.r3dtech.factory.map_graphics;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.r3dtech.factory.framework.Game;
import com.r3dtech.factory.framework.GameScreen;
import com.r3dtech.factory.tile_map.MapSegment;
import com.r3dtech.factory.tile_map.MapTile;
import com.r3dtech.factory.tile_map.TileMap;
import com.r3dtech.factory.tile_map.implementation.Constants;

import java.util.Random;

/**
 * This class represents a drawable map display.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 15/01/2018.
 */

public class MapViewDrawable extends Drawable implements MapSegment{
    private MapSegmentDrawable mapSegment;
    private float scale = 2;
    private float newScale = scale;
    private Rect bounds;
    private SpaceDrawable space = new SpaceDrawable();
    private Game game;

    public MapViewDrawable(MapSegmentDrawable mapSegment, Game game) {
        Random random = new Random();
        this.mapSegment = mapSegment;
        this.game = game;
    }

    public MapViewDrawable(TileMap map, Game game) {
        this(new MapSegmentDrawable(map), game);
    }

    @Override
    public TileMap getMap() {
        return mapSegment.getMap();
    }

    @Override
    public MapTile getTile(int x, int y) {
        return mapSegment.getTile(x, y);
    }

    @Override
    public void setSizeX(int sizeX) {
        mapSegment.setSizeX(sizeX);
    }

    @Override
    public int height() {
        return mapSegment.height();
    }

    @Override
    public void setSizeY(int sizeY) {
        mapSegment.setSizeY(sizeY);
    }

    @Override
    public int width() {
        return mapSegment.width();
    }

    @Override
    public int tiledHeight() {
        return mapSegment.tiledHeight();
    }

    @Override
    public Point getPos() {
        return mapSegment.getPos();
    }

    @Override
    public int tiledWidth() {
        return mapSegment.tiledWidth();
    }

    @Override
    public void setPos(Point pos) {
        mapSegment.setPos(pos);
    }

    @Override
    public int simulatedHeight() {
        return mapSegment.simulatedHeight();
    }

    @Override
    public Point getPosOffset() {
        return mapSegment.getPosOffset();
    }

    @Override
    public int simulatedWidth() {
        return mapSegment.simulatedWidth();
    }

    @Override
    public int getLeftDist() {
        return mapSegment.getLeftDist();
    }

    @Override
    public int getTopDist() {
        return mapSegment.getTopDist();
    }

    public void zoom(float scale) {
        setScale(newScale*scale);
    }

    @Override
    public void movePos(int dx, int dy) {
        mapSegment.movePos(dx, dy);
    }

    public void movePosFloat(float dx, float dy) {
        movePos((int) (dx/scale), (int) (dy/scale));
    }

    public void setScale(float scale) {
        newScale = scale;
        newScale = Math.max(1.5f, Math.min(5f,this.newScale));
    }

    public float getScale() {
        return scale;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        int scaledWidth = (int) (bounds.width()/scale);
        int scaledHeight = (int) (bounds.height()/scale);
        mapSegment.setBounds(0, 0, scaledWidth, scaledHeight);
        space.setBounds(bounds);
        space.draw(canvas);
        canvas.save();
        canvas.translate(bounds.left, bounds.top);
        canvas.scale(scale, scale);
        mapSegment.draw(canvas);
        canvas.restore();
    }

    @Override
    public void setAlpha(int i) {
        mapSegment.setAlpha(i);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mapSegment.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return mapSegment.getOpacity();
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        this.bounds = bounds;
    }

    @Override
    public void update() {
        scale = newScale;
        mapSegment.update();
    }

    @Override
    public MapTile getTileFromLoc(int x, int y) {
        return mapSegment.getTileFromLoc((int) (x/scale), (int) (y/scale));
    }
}
