package com.r3dtech.factory.inventory.graphics;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;

import com.r3dtech.factory.framework.Game;
import com.r3dtech.factory.framework.GameScreen;
import com.r3dtech.factory.inventory.GameItem;
import com.r3dtech.factory.inventory.GameItemDrawableCache;

/**
 * This is class is a screen for an inventory menu.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 22/01/2018.
 */

public class InventoryMenuScreen implements GameScreen {
    protected static final int SLOTS_PER_ROW = 5;
    private static final int SLOT_BEGIN_TOP = 128;
    private static final int SLOT_BEGIN_LEFT = 64;
    protected static final int SLOT_SIZE = 160;
    protected static final int TOP_DIST = 64;
    protected static final int LEFT_DIST = 32;
    protected static final Rect FIRST_SLOT = new Rect(
            SLOT_BEGIN_LEFT,
            SLOT_BEGIN_TOP,
            SLOT_BEGIN_LEFT+SLOT_SIZE,
            SLOT_BEGIN_TOP+SLOT_SIZE);


    private static final int BUTTON_WIDTH = 384;
    private static final int BUTTON_HEIGHT = 96;
    private static final int MAP_BUTTON_WIDTH = 128;
    private static final int MAP_BUTTON_HEIGHT = 128;
    private static final int MAP_BUTTON_RIGHT_DIST = 16;
    private static final int SLOT_OUTLINE_WIDTH = 8;
    private static final int NUM_TEXT_SIZE = 48;
    private static final int TEXT_SIZE = 32;

    protected MenuButton inventoryButton;
    protected MenuButton craftingButton;
    protected Game game;
    protected Canvas canvas;
    private Drawable mapButtom;

    protected GameItemDrawableCache drawableCache = new GameItemDrawableCache();

    private Paint slotPaint = new Paint();
    private Paint emptySlotPaint = new Paint();
    private Paint slotOutlinePaint = new Paint();

    protected Paint numPaint = new Paint(Paint.FAKE_BOLD_TEXT_FLAG);
    protected Paint textPaint = new Paint(Paint.FAKE_BOLD_TEXT_FLAG);

    public enum Tab {
        INVENTORY,
        CRAFTING
    }

    public InventoryMenuScreen(Bitmap frameBuffer, Game game, AssetManager assets, Tab tab) {
        this.game = game;
        switch (tab) {
            case INVENTORY:
                inventoryButton = new MenuButton("Inventory", true, assets);
                craftingButton = new MenuButton("Crafting", false, assets);
                break;
            case CRAFTING:
                inventoryButton = new MenuButton("Inventory", false, assets);
                craftingButton = new MenuButton("Crafting", true, assets);
                break;
        }

        Rect buttonBounds = new Rect(0, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
        inventoryButton.setBounds(buttonBounds);
        buttonBounds.offset(BUTTON_WIDTH, 0);
        craftingButton.setBounds(buttonBounds);
        canvas = new Canvas(frameBuffer);

        mapButtom = Drawable.createFromStream(this.getClass().
                getResourceAsStream("/res/drawable/map_icon.jpg"), "src");
        mapButtom.setBounds(canvas.getWidth() - MAP_BUTTON_WIDTH - MAP_BUTTON_RIGHT_DIST,
                0, canvas.getWidth() - MAP_BUTTON_RIGHT_DIST, MAP_BUTTON_HEIGHT);


        emptySlotPaint.setColor(Color.rgb(0xa0, 0xa0, 0xa0));
        slotPaint.setColor(Color.WHITE);
        slotOutlinePaint.setColor(Color.rgb(0x11, 0x11, 0x11));
        slotOutlinePaint.setStyle(Paint.Style.STROKE);
        slotOutlinePaint.setStrokeWidth(SLOT_OUTLINE_WIDTH);

        numPaint.setColor(Color.BLACK);
        numPaint.setTextSize(NUM_TEXT_SIZE);
        numPaint.setTextAlign(Paint.Align.CENTER);
        Typeface numFont = Typeface.createFromAsset(assets, "num_font.ttf");
        numPaint.setTypeface(numFont);

        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(TEXT_SIZE);
        textPaint.setTextAlign(Paint.Align.CENTER);
        Typeface textFont = Typeface.createFromAsset(assets, "text_font.ttf");
        textPaint.setTypeface(textFont);

        drawableCache.load();
    }

    @Override
    public void paint() {
        canvas.drawColor(Color.GRAY);
        inventoryButton.draw(canvas);
        craftingButton.draw(canvas);
        mapButtom.draw(canvas);
    }

    @Override
    public void onClick(int x, int y) {
        if (mapButtom.getBounds().contains(x, y)) {
            game.setMainScreen();
        }
        if (inventoryButton.getBounds().contains(x, y)) {
            game.setInventoryScreen();
        }

        if (craftingButton.getBounds().contains(x, y)) {
            game.setCraftingScreen();
        }
    }

    protected void drawSlot(Rect bounds, boolean isFull, GameItem item) {
        canvas.drawRoundRect(new RectF(bounds), bounds.height()/4, bounds.height()/4, slotOutlinePaint);
        if (isFull) {
            canvas.drawRoundRect(new RectF(bounds), bounds.height() / 4, bounds.height() / 4, slotPaint);
        } else {
            canvas.drawRoundRect(new RectF(bounds), bounds.height() / 4, bounds.height() / 4, emptySlotPaint);
        }
        Drawable icon = drawableCache.getDrawable(item.toInt());
        icon.setBounds(bounds);
        icon.draw(canvas);
    }

    protected static Rect[][] generateBounds(Rect startBound, int slotsNum) {
        Rect bound = new Rect(startBound);
        Rect[][] bounds = new Rect[(int) Math.ceil(slotsNum/(float) SLOTS_PER_ROW)][SLOTS_PER_ROW];
        for (int i = 0; i < slotsNum; i++) {
            int row = i/SLOTS_PER_ROW;
            int col = i%SLOTS_PER_ROW;
            bounds[row][col] = new Rect(bound);
            if (col < SLOTS_PER_ROW - 1) {
                bound.offset(SLOT_SIZE + LEFT_DIST, 0);
            } else {
                bound.offsetTo(FIRST_SLOT.left, bound.top + SLOT_SIZE + TOP_DIST);
            }
        }
        return bounds;
    }
}
