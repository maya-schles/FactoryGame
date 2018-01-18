package com.r3dtech.factory;

import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import com.r3dtech.factory.framework.GameScreen;
import com.r3dtech.factory.framework.implementation.AndroidFastRenderView;
import com.r3dtech.factory.framework.implementation.AndroidGame;
import com.r3dtech.factory.map_graphics.DrawableScreen;
import com.r3dtech.factory.map_graphics.MapViewDrawable;
import com.r3dtech.factory.tile_map.TileMap;
import com.r3dtech.factory.tile_map.implementation.GameMap;

import java.io.IOException;

import static android.view.MotionEvent.INVALID_POINTER_ID;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AndroidGame {
    private MapViewDrawable mapView;
    private AndroidFastRenderView mContentView;

    private static final int CLICK_ACTION_THRESHOLD = 5;
    private ScaleGestureDetector scaleDetector;
    private int activePointerId = INVALID_POINTER_ID;
    private float mLastTouchX;
    private  float mLastTouchY;
    private float startX;
    private float startY;
    private int screenWidth;
    private int screenHeight;

    private class ScaleListener
            extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mapView.zoom(scaleDetector.getScaleFactor());
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        scaleDetector = new ScaleGestureDetector(this,new ScaleListener());
        mContentView = new AndroidFastRenderView(this);
        setContentView(mContentView);
        hide();
    }

    private void dragMotionMove(MotionEvent ev) {
        final int action = ev.getActionMasked();

        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                final int pointerIndex = ev.getActionIndex();
                final float x = ev.getX(pointerIndex);
                final float y = ev.getY(pointerIndex);

                // Remember where we started (for dragging)
                mLastTouchX = x;
                mLastTouchY = y;
                // Save the ID of this pointer (for dragging)
                activePointerId = ev.getPointerId(0);
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                // Find the index of the active pointer and fetch its position
                final int pointerIndex = ev.findPointerIndex(activePointerId);

                final float x = ev.getX(pointerIndex);
                final float y = ev.getY(pointerIndex);

                // Calculate the distance moved
                final float dx = x - mLastTouchX;
                final float dy = y - mLastTouchY;

                // Move Map
                mapView.movePosFloat(dx, dy);

                // Remember this touch position for the next move event
                mLastTouchX = x;
                mLastTouchY = y;

                break;
            }

            case MotionEvent.ACTION_UP: {
                activePointerId = INVALID_POINTER_ID;
                break;
            }

            case MotionEvent.ACTION_CANCEL: {
                activePointerId = INVALID_POINTER_ID;
                break;
            }

            case MotionEvent.ACTION_POINTER_UP: {

                final int pointerIndex = ev.getActionIndex();
                final int pointerId = ev.getPointerId(pointerIndex);

                if (pointerId == activePointerId) {
                    // This was our active pointer going up. Choose a new
                    // active pointer and adjust accordingly.
                    final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
                    mLastTouchX = ev.getX(newPointerIndex);
                    mLastTouchY = ev.getY(newPointerIndex);
                    activePointerId = ev.getPointerId(newPointerIndex);
                }
                break;
            }
        }
    }

    private boolean isAClick(float startX, float endX, float startY, float endY) {
        float differenceX = Math.abs(startX - endX);
        float differenceY = Math.abs(startY - endY);
        return !(differenceX > CLICK_ACTION_THRESHOLD || differenceY > CLICK_ACTION_THRESHOLD);
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        scaleDetector.onTouchEvent(ev);
        dragMotionMove(ev);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = ev.getX();
                startY = ev.getY();
                break;
            case MotionEvent.ACTION_UP:
                float endX = ev.getX();
                float endY = ev.getY();
                if (isAClick(startX, endX, startY, endY)) {
                    Log.d("ON_TOUCH_EVENT:", "clicked " +
                            mapView.getTileFromLoc((int) startX -screenWidth/2,
                                    (int) startY - screenHeight/2).tileType().getName());
                }
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mContentView.resume();
        hide();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mContentView.pause();
    }
    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
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
}
