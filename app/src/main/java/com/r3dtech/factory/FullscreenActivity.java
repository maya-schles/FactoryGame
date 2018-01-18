package com.r3dtech.factory;

import android.graphics.Point;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.r3dtech.factory.framework.ClickCallback;
import com.r3dtech.factory.framework.GameScreen;
import com.r3dtech.factory.framework.ScaleCallback;
import com.r3dtech.factory.framework.ScrollCallback;
import com.r3dtech.factory.framework.implementation.AndroidGame;
import com.r3dtech.factory.map_graphics.DrawableScreen;
import com.r3dtech.factory.map_graphics.MapViewDrawable;
import com.r3dtech.factory.tile_map.TileMap;
import com.r3dtech.factory.tile_map.implementation.GameMap;

import java.io.IOException;



/**
 * The app's main activity.
 */
public class FullscreenActivity extends AndroidGame {
    private MapViewDrawable mapView;

    private int screenWidth;
    private int screenHeight;

    private class mScaleCallback implements ScaleCallback {
        @Override
        public void onScale(float scale) {
            mapView.zoom(scale);
        }
    }

    private class mScrollCallback implements ScrollCallback {
        @Override
        public void onScroll(float dx, float dy) {
            mapView.movePosFloat(dx, dy);
        }
    }

    private class mClickCallback implements ClickCallback {
        @Override
        public void onClick(int x, int y) {
            Log.d("onClick ", mapView.getTileFromLoc(x - screenWidth/2,
                    y - screenHeight/2).tileType().getName());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hide();
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getRealSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
    }

    @Override
    protected void onResume() {
        super.onResume();
        hide();
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        renderView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    private TileMap createMap() {
        TileMap map;
        try {
            map = new GameMap(R.raw.world);
        }catch (IOException e) {
            throw new RuntimeException("Map file not found");
        }
        Log.d("WORLD MAP:", map.toString());
        return map;
    }
    @Override
    public GameScreen getInitScreen() {
        TileMap map = createMap();
        mapView = new MapViewDrawable(map, this);
        return new DrawableScreen(mapView, getFrameBuffer());
    }

    public void update(float deltaTime) {
        mapView.update();
    }

    @Override
    public ScaleCallback getScaleCallback() {
        return new mScaleCallback();
    }

    @Override
    public ScrollCallback getScrollCalback() {
        return new mScrollCallback();
    }

    @Override
    public ClickCallback getClickCallback() {
        return new mClickCallback();
    }
}
