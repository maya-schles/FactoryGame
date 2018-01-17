package com.r3dtech.factory;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.r3dtech.factory.map_graphics.MapViewDrawable;


/**
 * A SurfaceView class to create graphics and update UI quickly on android.
 */

public class AndroidFastRenderView extends SurfaceView implements Runnable {
    Bitmap frameBuffer;
    private MapViewDrawable drawable;
    Thread renderThread = null;
    SurfaceHolder holder;
    volatile boolean running = false;
    private Canvas bufferCanvas;
    private FullscreenActivity game;

    public AndroidFastRenderView(Context context, MapViewDrawable drawable, int width, int height, FullscreenActivity game) {
        super(context);
        this.drawable = drawable;
        this.frameBuffer = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        this.holder = getHolder();
        this.bufferCanvas = new Canvas(frameBuffer);
        this.game = game;
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

            drawable.setBounds(bufferCanvas.getClipBounds());
            drawable.draw(bufferCanvas);

            Canvas canvas = holder.lockCanvas();
            canvas.getClipBounds(dstRect);
            canvas.drawBitmap(frameBuffer, null, dstRect, null);
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
