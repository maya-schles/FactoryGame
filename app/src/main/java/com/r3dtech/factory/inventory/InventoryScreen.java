package com.r3dtech.factory.inventory;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

import com.r3dtech.factory.framework.Game;
import com.r3dtech.factory.framework.GameScreen;

/**
 * This class is used as a screen for the inventory;
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 19/01/2018.
 */

public class InventoryScreen implements GameScreen{
    private static final int SLOT_BEGIN_TOP = 128;
    private static final int SLOTS_PER_ROW = 5;
    private static final int TOP_DIST = 16;
    private static final int LEFT_DIST = 16;
    private static final int SLOT_SIZE = 128;
    private static final int TITLE_TEXT_SIZE = 64;
    private Rect[][] bounds;
    private Canvas canvas;
    private Paint slotPaint = new Paint();
    private GameItemDrawableCache drawableCache = new GameItemDrawableCache();
    private Inventory inventory;
    private Game game;
    private Paint textPaint = new Paint(Paint.FAKE_BOLD_TEXT_FLAG);
    private Paint numPaint = new Paint(Paint.FAKE_BOLD_TEXT_FLAG);

    public InventoryScreen(Bitmap frameBuffer, Inventory inventory, Game game) {
        this.game = game;

        this.inventory = inventory;
        canvas = new Canvas(frameBuffer);
        bounds = new Rect[(int) Math.ceil(inventory.getItemNum()/(float) SLOTS_PER_ROW)][SLOTS_PER_ROW];
        for (int i = 0; i < inventory.getItemNum(); i++) {
            int row = i/SLOTS_PER_ROW;
            int col = i%SLOTS_PER_ROW;
            int left = col*(LEFT_DIST+SLOT_SIZE) + LEFT_DIST;
            int top = SLOT_BEGIN_TOP+row*(TOP_DIST+SLOT_SIZE);
            bounds[row][col] = new Rect(left, top, left+SLOT_SIZE, top+SLOT_SIZE);
        }
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(TITLE_TEXT_SIZE);
        numPaint.setColor(Color.BLACK);
        numPaint.setTextSize(SLOT_SIZE/3);
        slotPaint.setColor(Color.WHITE);
        drawableCache.load();
    }

    @Override
    public void paint() {
        canvas.drawColor(Color.GRAY);
        canvas.drawText("Inventory", 0, textPaint.getTextSize(), textPaint);
        for (int i = 0; i < inventory.getItemNum(); i++) {
            int row = i/SLOTS_PER_ROW;
            int col = i%SLOTS_PER_ROW;
            canvas.drawRoundRect(new RectF(bounds[row][col]), bounds[row][col].height()/4,
                    bounds[row][col].height()/4, slotPaint);
            GameItem item = inventory.getItem(i);
            Drawable icon = drawableCache.getDrawable(item.toInt());
            icon.setBounds(bounds[row][col]);
            icon.draw(canvas);
            canvas.drawText(Integer.toString(inventory.getAmount(item)),
                    bounds[row][col].left, bounds[row][col].bottom, numPaint);
        }
    }

    @Override
    public void onClick(int x, int y) {
        game.setMainScreen();
    }
}
