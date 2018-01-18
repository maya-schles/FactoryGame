package com.r3dtech.factory.framework.implementation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.r3dtech.factory.FullscreenActivity;
import com.r3dtech.factory.framework.GameScreen;
import com.r3dtech.factory.map_graphics.MapViewDrawable;


/**
 * A SurfaceView class to create graphics and update UI quickly on android.
 */

public class AndroidFastRenderView extends SurfaceView implements Runnable {
    Thread renderThread = null;
    SurfaceHolder holder;
    volatile boolean running = false;
    private AndroidGame game;

    public AndroidFastRenderView(AndroidGame game) {
        super(game);
        this.game = game;
        holder = getHolder();
    }

    public void resume() {
        running = true;
        renderThread = new Thread(this);
        renderThread.start();
    }

    @Override
    public void run() {
        Rect dstRect = new Rect();
        long startTime = System.currentTimeMillis();

        while(running) {
            if (!holder.getSurface().isValid()) {
                continue;
            }

            float deltaTime = (System.currentTimeMillis() - startTime) /10;
            startTime = System.currentTimeMillis();

            deltaTime = Math.min(deltaTime, (float) 3.15);

            game.update(deltaTime);
            game.getCurrentScreen().paint();

            Canvas canvas = holder.lockCanvas();
            canvas.getClipBounds(dstRect);
            canvas.drawBitmap(game.getFrameBuffer(), null, dstRect, null);
            holder.unlockCanvasAndPost(canvas);
        }
    }

    public void pause() {
        running = false;
        while (true) {
            try {
                renderThread.join();
                break;
            } catch (InterruptedException e) {
                // Retry
            }
        }
    }
}
