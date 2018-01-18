package com.r3dtech.factory.framework.implementation;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import com.r3dtech.factory.framework.ClickCallback;
import com.r3dtech.factory.framework.GameInput;
import com.r3dtech.factory.framework.ScaleCallback;
import com.r3dtech.factory.framework.ScrollCallback;

import static android.view.MotionEvent.INVALID_POINTER_ID;

/**
 * This class is an implementation of GameInput on android.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 18/01/2018.
 */

public class AndroidInput implements GameInput, View.OnTouchListener {
    private static final int CLICK_ACTION_THRESHOLD = 5;

    private ScaleGestureDetector scaleDetector;

    private ScaleCallback scaleCallback;
    private ScrollCallback scrollCallback;
    private ClickCallback clickCallback;

    private float startX;
    private float startY;

    private int activePointerId = INVALID_POINTER_ID;
    private float mLastTouchX;
    private  float mLastTouchY;

    public AndroidInput(Context context, View view) {
        view.setOnTouchListener(this);
        scaleDetector = new ScaleGestureDetector(context, new ScaleListener());
    }

    private boolean isAClick(float startX, float endX, float startY, float endY) {
        float differenceX = Math.abs(startX - endX);
        float differenceY = Math.abs(startY - endY);
        return !(differenceX > CLICK_ACTION_THRESHOLD || differenceY > CLICK_ACTION_THRESHOLD);
    }

    private void clickDetection(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = ev.getX();
                startY = ev.getY();
                break;
            case MotionEvent.ACTION_UP:
                float endX = ev.getX();
                float endY = ev.getY();
                if (isAClick(startX, endX, startY, endY)) {
                            clickCallback.onClick((int) startX ,(int) startY);
                }
        }
    }

    private void scrollDetection(MotionEvent ev) {
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

                scrollCallback.onScroll(dx, dy);

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

    private class ScaleListener
            extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scaleCallback.onScale(scaleDetector.getScaleFactor());
            return true;
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent ev) {
        clickDetection(ev);
        scrollDetection(ev);
        scaleDetector.onTouchEvent(ev);
        return true;
    }

    @Override
    public void setScaleCallback(ScaleCallback callback) {
        scaleCallback = callback;
    }

    @Override
    public void setScrollCallback(ScrollCallback callback) {
        scrollCallback = callback;
    }

    @Override
    public void setClickCallback(ClickCallback callback) {
        clickCallback = callback;
    }
}
