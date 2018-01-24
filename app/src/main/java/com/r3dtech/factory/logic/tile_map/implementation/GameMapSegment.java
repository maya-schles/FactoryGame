package com.r3dtech.factory.logic.tile_map.implementation;

import android.graphics.Point;

import com.r3dtech.factory.logic.machines.Machine;
import com.r3dtech.factory.logic.tile_map.MapSegment;
import com.r3dtech.factory.logic.tile_map.MapTile;
import com.r3dtech.factory.logic.tile_map.TileMap;

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

    private GameMapSegment(TileMap map, int sizeX, int sizeY, Point pos) {
        this.map = map;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.pos = pos;
    }

    GameMapSegment(TileMap map) {
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

    private void setPos(Point pos) {
        this.pos.x = Math.max(0, Math.min(Constants.WORLD_WIDTH, pos.x));
        this.pos.y = Math.max(0, Math.min(Constants.WORLD_HEIGHT, pos.y));
    }

    public void movePos(int dx, int dy) {
        setPos(new Point(pos.x - dx, pos.y - dy));
    }

    @Override
    public Point topLeftTile() {
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
        int tileY = y + topLeftTile().y;
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
    public Point getPosOffset() {
        int offsetX = pos.x  - getLeftDist() -  Math.round(tiledWidth()*Constants.TILE_SIZE/2f);
        int offsetY = pos.y - getTopDist() - Math.round(tiledHeight()*Constants.TILE_SIZE/2f);
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
    public Point getTileFromLoc(int x, int y) {
        Point tile = map.getTileFromLoc(x+pos.x, y+pos.y);
        return new Point(tile.x - topLeftTile().x, tile.y - topLeftTile().y);
    }

    @Override
    public boolean isDiscovered(int x, int y) {
        return map.isDiscovered(x + topLeftTile().x, y + topLeftTile().y);
    }

    @Override
    public int getSmallDistFromDiscovered(int x, int y) {
        return map.getSmallDistFromDiscovered(x + topLeftTile().x, y + topLeftTile().y);
    }

    @Override
    public void addMachine(int x, int y, Machine machine) {
        map.addMachine(x + topLeftTile().x, y + topLeftTile().y, machine);
    }

    @Override
    public Machine[] getMachines() {
        return map.getMachines();
    }
}
