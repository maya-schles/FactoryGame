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
    private float zoom = 1;
    private boolean needZoom = false;
    private float dx, dy;
    private boolean needPos = false;

    public AndroidFastRenderView(Context context, MapViewDrawable drawable, int width, int height) {
        super(context);
        this.drawable = drawable;
        this.frameBuffer = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        this.holder = getHolder();
        this.bufferCanvas = new Canvas(frameBuffer);
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

            drawable.setBounds(bufferCanvas.getClipBounds());
            if (needZoom) {
                drawable.zoom(zoom);
                needZoom = false;
                this.zoom = 1;
            }
            if (needPos) {
                drawable.movePos(dx, dy);
                needPos = false;
                this.dx = 0;
                this.dy = 0;
            }
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

    public void zoom(float zoom) {
        this.zoom *= zoom;
        needZoom = true;
    }

    public void movePos(float dx, float dy) {
        this.dx += dx;
        this.dy += dy;
        needPos = true;
    }
}
