package com.r3dtech.factory.graphics.tile_map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.r3dtech.factory.graphics.GenericDrawer;
import com.r3dtech.factory.logic.tile_map.MapSegment;
import com.r3dtech.factory.logic.tile_map.MapSegmentPerspective;

/**
 * This class is used to draw a segment perspective.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 23/01/2018.
 */

public class PerspectiveDrawer extends GenericDrawer<MapSegmentPerspective> {
    private GenericDrawer<MapSegment> drawer = new MapSegmentDrawer();

    @Override
    public void draw(Canvas canvas, MapSegmentPerspective object) {
        float scale = object.getScale();
        int scaledWidth = (int) (bounds.width()/scale);
        int scaledHeight = (int) (bounds.height()/scale);

        drawer.setBounds(new Rect(0, 0, scaledWidth, scaledHeight));

        canvas.save();
        canvas.translate(bounds.left, bounds.top);
        canvas.scale(scale, scale);
        drawer.draw(canvas, object);
        canvas.restore();
    }
}
