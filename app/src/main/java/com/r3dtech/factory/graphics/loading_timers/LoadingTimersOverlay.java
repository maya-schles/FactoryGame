package com.r3dtech.factory.graphics.loading_timers;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

import com.r3dtech.factory.framework.ScreenOverlay;
import com.r3dtech.factory.graphics.DrawableCaches;
import com.r3dtech.factory.graphics.GenericDrawer;
import com.r3dtech.factory.logic.inventory.GameItem;
import com.r3dtech.factory.logic.loading_timers.LoadingTimer;
import com.r3dtech.factory.logic.loading_timers.TimersManager;

/**
 * This class is a screen overlay for a timers manager.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 23/01/2018.
 */

public class LoadingTimersOverlay implements ScreenOverlay {
        private static final int TIMER_WIDTH = 384;
        private static final int TIMER_HEIGHT = 96;
        private static final int TIMERS_DIST = 16;

        private TimersManager timersManager;
        private Canvas canvas;
        private Paint numPaint = new Paint(Paint.FAKE_BOLD_TEXT_FLAG);

        public LoadingTimersOverlay(Bitmap frameBuffer, TimersManager timersManager, AssetManager assets) {
            this.timersManager = timersManager;
            canvas = new Canvas(frameBuffer);
            Typeface numFont = Typeface.createFromAsset(assets, "num_font.ttf");
            numPaint.setTypeface(numFont);
            numPaint.setTextSize(TIMER_HEIGHT/2);
        }

        @Override
        public void paint() {
            // The first timer's top bounds
            int timerTop = canvas.getClipBounds().bottom - TIMERS_DIST - TIMER_HEIGHT;
            // The first timer's bounds
            Rect timerBounds = new Rect(0, timerTop, TIMER_WIDTH, timerTop + TIMER_HEIGHT);


            LoadingTimer[] timers = timersManager.getTimers();
            for (int i = 0; i < timers.length; i++) {
                GameItem item = GameItem.fromInt(i);
                if (timersManager.getTimersNum(item) > 0) {
                    // Draw timer
                    GenericDrawer<LoadingTimer> drawer = new LoadingTimerDrawer(DrawableCaches.getItemIcon(i));
                    drawer.setBounds(timerBounds);
                    drawer.draw(canvas, timers[i]);
                    // Draw the timers number
                    canvas.drawText(Integer.toString(this.timersManager.getTimersNum(item)),
                            timerBounds.left, timerBounds.bottom, numPaint);
                    // update timer bounds
                    timerBounds.offset(0, -timerBounds.height()-TIMERS_DIST);
                }
            }
        }
}
