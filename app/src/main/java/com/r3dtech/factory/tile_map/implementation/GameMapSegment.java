package com.r3dtech.factory.tile_map.implementation;

import android.graphics.Point;
import android.util.Log;

import com.r3dtech.factory.tile_map.MapSegment;
import com.r3dtech.factory.tile_map.MapTile;
import com.r3dtech.factory.tile_map.TileMap;

/**
 * This class represents a small segment of a game map.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 14/01/2018.
 */

public class GameMapSegment implements MapSegment {
    private TileMap map;
    private int sizeX;
    private int sizeY;
    private Point pos;
    private Point newPos;

    public GameMapSegment(TileMap map, int sizeX, int sizeY, Point pos) {
        this.map = map;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.pos = pos;
        newPos = pos;
    }

    public GameMapSegment(TileMap map) {
        this(map, Constants.DEFAULT_SEG_WIDTH, Constants.DEFAULT_SEG_HEIGHT,
                Constants.DEFAULT_SEG_POS);
    }

    @Override
    public TileMap getMap() {
        return map;
    }

    @Override
    public int width() {
        return sizeX;
    }

    @Override
    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    @Override
    public int height() {
        return sizeY;
    }

    @Override
    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    @Override
    public Point getPos() {
        return pos;
    }

    @Override
    public void setPos(Point pos) {
        this.pos = pos;
    }

    public void movePos(int dx, int dy) {
        pos.x = pos.x - dx;
        pos.y = pos.y - dy;
        Log.d("MOVINGGGGGGGGG-------------------------MOVING", "moved");
    }

    public void updatePos() {

    }

    private Point topLeftTile() {
        int x = (int) (pos.x/(float) Constants.TILE_SIZE -tiledWidth()/2f);
        int y = (int) (pos.y/(float) Constants.TILE_SIZE -tiledHeight()/2f);
        return new Point(x, y);
    }
    @Override
    public MapTile getTile(int x, int y) {
        int tileX = x + topLeftTile().x;
        int tileY= y + topLeftTile().y;
        return map.getTile(tileX, tileY);
    }

    @Override
    public int tiledHeight() {
        return (int) Math.ceil(height()/(float) Constants.TILE_SIZE);
    }

    @Override
    public int tiledWidth() {
        return (int) Math.ceil(width()/(float) Constants.TILE_SIZE);
    }

    @Override
    public int simulatedHeight() {
        return tiledHeight()*Constants.TILE_SIZE;
    }

    @Override
    public int simulatedWidth() {
        return tiledWidth()*Constants.TILE_SIZE;
    }

    @Override
    public Point getPosOffset() {
        int offsetX = pos.x  - getLeftDist() -  Math.round(simulatedWidth()/2f);
        int offsetY = pos.y - getTopDist() - Math.round(simulatedHeight()/2f);
        return new Point(offsetX,offsetY);
    }

    @Override
    public int getLeftDist() {
        return topLeftTile().x*Constants.TILE_SIZE;
    }

    @Override
    public int getTopDist() {
        return topLeftTile().y*Constants.TILE_SIZE;
    }
}
