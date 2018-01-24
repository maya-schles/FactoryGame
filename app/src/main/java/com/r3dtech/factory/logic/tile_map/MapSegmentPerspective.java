package com.r3dtech.factory.logic.tile_map;


/**
 * This interface is for a map segment with a scale ability.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 23/01/2018.
 */

public interface MapSegmentPerspective extends MapSegment{
    void movePosFloat(float dx, float dy);
    void zoom(float zoom);
    float getScale();
}
