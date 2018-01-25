package com.r3dtech.factory.graphics.map;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import com.r3dtech.factory.Utils;
import com.r3dtech.factory.graphics.DrawableCaches;
import com.r3dtech.factory.graphics.GenericDrawer;
import com.r3dtech.factory.graphics.SimpleBitmapDrawable;
import com.r3dtech.factory.graphics.machines.ArrowBitmapDrawable;
import com.r3dtech.factory.graphics.map.tile_drawables.FogDrawable;
import com.r3dtech.factory.logic.machines.Machine;
import com.r3dtech.factory.logic.tile_map.MapSegment;
import com.r3dtech.factory.logic.tile_map.MapTile;
import com.r3dtech.factory.logic.tile_map.TileMap;
import com.r3dtech.factory.logic.tile_map.TileType;
import com.r3dtech.factory.logic.tile_map.implementation.Constants;

import java.util.Random;

/**
 * This class is a drawer object for a MapSegment.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 23/01/2018.
 */

public class MapSegmentDrawer extends GenericDrawer<MapSegment> {
    private SimpleBitmapDrawable[][][] foggedTiles = new SimpleBitmapDrawable[
            TileType.values().length][Constants.TILE_VARIETY][TileMap.SMALL_DISCOVERED_DIST+1];

    private boolean drawArrows;

    MapSegmentDrawer() {
        this(false);
    }

    MapSegmentDrawer(boolean drawArrows) {
        initFoggedTiles();
        this.drawArrows = drawArrows;
    }
    private void initFoggedTiles() {
        SimpleBitmapDrawable[] fogDrawables = {new FogDrawable(0), new FogDrawable(1), new FogDrawable(2)};
        float fogScale = 255/TileMap.SMALL_DISCOVERED_DIST;
        for (int i = 0; i < TileType.values().length; i++) {
            for (int j = 0; j < Constants.TILE_VARIETY; j++) {
                for (int k = 0; k <= TileMap.SMALL_DISCOVERED_DIST; k++) {
                    foggedTiles[i][j][k] =  fogTile(fogDrawables, DrawableCaches.getTile(i, j), (int) (k*fogScale));
                }
            }
        }
    }

    private SimpleBitmapDrawable fogTile(SimpleBitmapDrawable[] fogDrawables, SimpleBitmapDrawable tile, int alpha) {
        Random random = new Random();
        SimpleBitmapDrawable fog = fogDrawables[Math.abs(random.nextInt(Constants.TILE_VARIETY))];
        return SimpleBitmapDrawable.add(fog, tile, alpha);
    }

    private Rect getCenteredViewRect(MapSegment segment) {
        Point viewCenter = new Point(bounds.width()/2, bounds.height()/2);
        Point offset = segment.getPosOffset();
        offset.negate();
        Point centerPoint = Utils.offset(viewCenter, offset);
        return Utils.createCenteredRect(
                centerPoint,
                segment.tiledWidth()*Constants.TILE_SIZE,
                segment.tiledHeight()*Constants.TILE_SIZE);
    }
    @Override
    public void draw(Canvas canvas, MapSegment object) {
        object.setSizeX(bounds.width());
        object.setSizeY(bounds.height());

        Rect centeredRect = getCenteredViewRect(object);
        for (int x = -1; x <= object.tiledWidth(); x++) {
            for (int y = -1; y <= object.tiledHeight(); y++) {
                MapTile tile = object.getTile(x, y);
                if (tile == null) {
                    continue;
                }

                Rect dstRect = new Rect(x * Constants.TILE_SIZE, y * Constants.TILE_SIZE,
                        (x + 1) * Constants.TILE_SIZE, (y + 1) * Constants.TILE_SIZE);
                dstRect.offset(centeredRect.left, centeredRect.top);

                Drawable drawable = foggedTiles
                        [tile.tileType().toInt()]
                        [tile.getVer()]
                        [object.getSmallDistFromDiscovered(x, y)];

                drawable.setBounds(dstRect);
                drawable.draw(canvas);

                Machine machine = tile.getMachine();
                if (machine != null) {
                    int machineDrawableInt = machine.getType().toInt() + machine.getState().getOffset();
                    Drawable machineDrawable = DrawableCaches.getMachine(machineDrawableInt);
                    machineDrawable.setBounds(dstRect);
                    machineDrawable.draw(canvas);
                    if (drawArrows) {
                        Drawable arrow = new ArrowBitmapDrawable(tile.getMachine().getOutputDirection());
                        arrow.setBounds(dstRect);
                        arrow.draw(canvas);
                    }
                }
            }
        }
    }
}

