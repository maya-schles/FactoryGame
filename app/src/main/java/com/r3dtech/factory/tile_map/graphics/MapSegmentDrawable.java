package com.r3dtech.factory.tile_map.graphics;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.r3dtech.factory.Utils;
import com.r3dtech.factory.tile_map.MapSegment;
import com.r3dtech.factory.tile_map.MapTile;
import com.r3dtech.factory.tile_map.TileMap;
import com.r3dtech.factory.tile_map.TileType;
import com.r3dtech.factory.tile_map.implementation.Constants;
import com.r3dtech.factory.tile_map.implementation.GameMapSegment;

/**
 * This class is a drawable container for the GameMapSegment interface.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 14/01/2018.
 */

public class MapSegmentDrawable extends Drawable implements MapSegment {
    private MapSegment mapSegment;
    private Rect bounds;
    private TileDrawableCache drawableCache;
    private SimpleBitmapDrawable[][] fogDrawables = {
            {new FogDrawable(0, GrassDrawable.getMainColor()),
                    new FogDrawable(1, GrassDrawable.getMainColor()),
                    new FogDrawable(2, GrassDrawable.getMainColor())},
            {new FogDrawable(0, GrassDrawable.getMainColor()),
                    new FogDrawable(1, GrassDrawable.getMainColor()),
                    new FogDrawable(2, GrassDrawable.getMainColor())},
            {new FogDrawable(0, StoneDrawable.getMainColor()),
                    new FogDrawable(1, StoneDrawable.getMainColor()),
                    new FogDrawable(2, StoneDrawable.getMainColor())}};

    public MapSegmentDrawable(MapSegment mapSegment) {
        super();
        Utils.loadHash();
        this.mapSegment = mapSegment;
        drawableCache = new TileDrawableCache();
        drawableCache.load();
    }

    public MapSegmentDrawable(TileMap map) {
        this(new GameMapSegment(map));
    }
    @Override
    public TileMap getMap() {
        return mapSegment.getMap();
    }

    @Override
    public int width() {
        return mapSegment.width();
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
    public Point getPos() {
        return mapSegment.getPos();
    }

    @Override
    public void setPos(Point pos) {
        mapSegment.setPos(pos);
    }

    @Override
    public MapTile getTile(int x, int y) {
        return mapSegment.getTile(x, y);
    }

    @Override
    public int tiledHeight() {
        return mapSegment.tiledHeight();
    }

    @Override
    public int tiledWidth() {
        return mapSegment.tiledWidth();
    }

    @Override
    public int simulatedHeight() {
        return mapSegment.simulatedHeight();
    }

    @Override
    public int simulatedWidth() {
        return mapSegment.simulatedWidth();
    }

    @Override
    public Point getPosOffset() {
        return mapSegment.getPosOffset();
    }

    @Override
    public int getLeftDist() {
        return mapSegment.getLeftDist();
    }

    @Override
    public int getTopDist() {
        return mapSegment.getTopDist();
    }

    @Override
    public void movePos(int dx, int dy) {
        mapSegment.movePos(dx, dy);
    }

    @Override
    public MapTile getTileFromLoc(int x, int y) {
        return mapSegment.getTileFromLoc(x, y);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        Point viewCenter = new Point(bounds.width()/2, bounds.height()/2);
        Point offset = getPosOffset();
        offset.negate();
        Point centerPoint = Utils.offset(viewCenter, offset);
        Rect centeredRect = Utils.createCenteredRect(centerPoint, simulatedWidth(),
                simulatedHeight());
        for (int x = -1; x <= tiledWidth(); x++) {
            for (int y = -1; y <= tiledHeight(); y++) {
                MapTile tile = getTile(x, y);
                if (tile == null) {
                    continue;
                }
                SimpleBitmapDrawable drawable;
                Rect dstRect = new Rect(x*Constants.TILE_SIZE, y*Constants.TILE_SIZE,
                        (x+1)*Constants.TILE_SIZE, (y+1)*Constants.TILE_SIZE);
                dstRect.offset(centeredRect.left, centeredRect.top);
                drawable = drawableCache.getDrawable(tile.tileType().toInt(), tile.getVer());
                if(!isDiscovered(x, y)) {
                    SimpleBitmapDrawable fogDrawable = fogDrawables[(Utils.hash(
                            x+topLeftTile().x,
                            y+topLeftTile().y))%fogDrawables.length][tile.tileType().toInt()];
                    if (getSmallDistFromDiscovered(x, y) < 5) {
                        Log.d("Im here!", "dbug");
                    }
                    drawable = SimpleBitmapDrawable.add(fogDrawable, drawable,
                            (getSmallDistFromDiscovered(x,y)*46));
                }
                drawable.setBounds(dstRect);
                drawable.draw(canvas);
            }
        }
    }

    @Override
    public void setAlpha(int i) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        this.bounds = bounds;
        mapSegment.setSizeX(bounds.width());
        mapSegment.setSizeY(bounds.height());

    }

    @Override
    public void update() {
        mapSegment.update();
    }

    @Override
    public boolean isDiscovered(int x, int y) {
        return mapSegment.isDiscovered(x, y);
    }

    @Override
    public Point topLeftTile() {
        return mapSegment.topLeftTile();
    }

    @Override
    public boolean isLocDiscovered(int x, int y) {
        return mapSegment.isLocDiscovered(x, y);
    }

    @Override
    public int getSmallDistFromDiscovered(int x, int y) {
        return mapSegment.getSmallDistFromDiscovered(x, y);
    }
}
