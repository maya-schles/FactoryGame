package com.r3dtech.factory.tile_map.implementation;

import android.graphics.Point;

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
    private int newSizeX;
    private int sizeY;
    private int newSizeY;
    private Point pos;
    private Point newPos;

    public GameMapSegment(TileMap map, int sizeX, int sizeY, Point pos) {
        this.map = map;
        this.sizeX = sizeX;
        this.newSizeX = sizeX;
        this.sizeY = sizeY;
        this.newSizeY = sizeY;
        this.pos = pos;
        this.newPos = new Point(pos);
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
        newSizeX = sizeX;
    }

    @Override
    public int height() {
        return sizeY;
    }

    @Override
    public void setSizeY(int sizeY) {
        this.newSizeY = sizeY;
    }

    @Override
    public Point getPos() {
        return pos;
    }

    @Override
    public void setPos(Point pos) {
        this.newPos.x = Math.max(0, Math.min(Constants.WORLD_WIDTH, pos.x));
        this.newPos.y = Math.max(0, Math.min(Constants.WORLD_HEIGHT, pos.y));
    }

    public void movePos(int dx, int dy) {
        setPos(new Point(newPos.x - dx, newPos.y - dy));
    }

    private Point topLeftTile() {
        int x = (int) (pos.x - width()/2f)/Constants.TILE_SIZE;
        int y = (int) (pos.y - height()/2f)/Constants.TILE_SIZE;
        return new Point(x, y);
    }

    private Point bottomRightTile() {
        int x = (int) Math.ceil(pos.x + width()/2f)/Constants.TILE_SIZE;
        int y = (int) Math.ceil(pos.y + height()/2f)/Constants.TILE_SIZE;
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
        return bottomRightTile().y - topLeftTile().y + 1;
    }

    @Override
    public int tiledWidth() {
        return bottomRightTile().x - topLeftTile().x + 1;
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

    @Override
    public void update() {
        this.sizeX = newSizeX;
        this.sizeY = newSizeY;
        this.pos.x = newPos.x;
        this.pos.y = newPos.y;

    }
}
