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
import com.r3dtech.factory.inventory.Inventory;

/**
 * This class is used as a screen for the inventory;
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 19/01/2018.
 */

public class InventoryScreen implements GameScreen{
    private static final int SLOT_BEGIN_TOP = 128;
    private static final int SLOTS_PER_ROW = 5;
    private static final int TOP_DIST = 64;
    private static final int TEXT_BOT_DIST = 16;
    private static final int LEFT_DIST = 32;
    private static final int SLOT_SIZE = 160;
    private static final int TITLE_TEXT_SIZE = 64;
    private static final int TITLE_LEFT_DIST = 16;
    private static final int MAP_BUTTON_WIDTH = 128;
    private static final int MAP_BUTTON_HEIGHT = 128;
    private static final int MAP_BUTTON_RIGHT_DIST = 16;
    private static final int SLOT_OUTLINE_WIDTH = 8;
    private Rect[][] bounds;
    private Canvas canvas;
    private GameItemDrawableCache drawableCache = new GameItemDrawableCache();
    private Inventory inventory;
    private Game game;
    private Paint slotPaint = new Paint();
    private Paint slotOutlinePaint = new Paint();
    private Paint textPaint = new Paint(Paint.FAKE_BOLD_TEXT_FLAG);
    private Paint numPaint = new Paint(Paint.FAKE_BOLD_TEXT_FLAG);
    private Drawable mapButtom;

    public InventoryScreen(Bitmap frameBuffer, Inventory inventory, Game game, AssetManager assets) {
        this.game = game;

        this.inventory = inventory;
        canvas = new Canvas(frameBuffer);
        bounds = new Rect[(int) Math.ceil(GameItem.values().length/(float) SLOTS_PER_ROW)][SLOTS_PER_ROW];
        for (int i = 0; i < GameItem.values().length; i++) {
            int row = i/SLOTS_PER_ROW;
            int col = i%SLOTS_PER_ROW;
            int left = col*(LEFT_DIST+SLOT_SIZE) + LEFT_DIST;
            int top = SLOT_BEGIN_TOP+row*(TOP_DIST+SLOT_SIZE);
            bounds[row][col] = new Rect(left, top, left+SLOT_SIZE, top+SLOT_SIZE);
        }
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(TITLE_TEXT_SIZE);
        Typeface textFont = Typeface.createFromAsset(assets, "text_font.ttf");
        textPaint.setTypeface(textFont);
        numPaint.setColor(Color.BLACK);
        numPaint.setTextSize(TOP_DIST- TEXT_BOT_DIST);
        numPaint.setTextAlign(Paint.Align.CENTER);
        Typeface numFont = Typeface.createFromAsset(assets, "num_font.ttf");
        numPaint.setTypeface(numFont);
        slotPaint.setColor(Color.WHITE);
        slotOutlinePaint.setColor(Color.rgb(0x11, 0x11, 0x11));
        slotOutlinePaint.setStyle(Paint.Style.STROKE);
        slotOutlinePaint.setStrokeWidth(SLOT_OUTLINE_WIDTH);
        drawableCache.load();

        mapButtom = Drawable.createFromStream(this.getClass().
                getResourceAsStream("/res/drawable/map_icon.jpg"), "src");
        mapButtom.setBounds(canvas.getWidth()-MAP_BUTTON_WIDTH-MAP_BUTTON_RIGHT_DIST,
                0, canvas.getWidth()-MAP_BUTTON_RIGHT_DIST, MAP_BUTTON_HEIGHT);
    }

    private void drawSlot(Rect bounds, boolean isEmpty) {
        canvas.drawRoundRect(new RectF(bounds), bounds.height()/4, bounds.height()/4, slotOutlinePaint);
        if (! isEmpty) {
            canvas.drawRoundRect(new RectF(bounds), bounds.height() / 4, bounds.height() / 4, slotPaint);
        }

    }
    @Override
    public void paint() {
        canvas.drawColor(Color.GRAY);
        canvas.drawText("Inventory", TITLE_LEFT_DIST, textPaint.getTextSize(), textPaint);
        for (int i = 0; i < GameItem.values().length; i++) {
            int row = i/SLOTS_PER_ROW;
            int col = i%SLOTS_PER_ROW;
            drawSlot(bounds[row][col], !(i < inventory.getItemNum()));
            if (i < inventory.getItemNum()) {
                GameItem item = inventory.getItem(i);
                Drawable icon = drawableCache.getDrawable(item.toInt());
                icon.setBounds(bounds[row][col]);
                icon.draw(canvas);
                canvas.drawText(Integer.toString(inventory.getAmount(item)),
                        bounds[row][col].centerX(), bounds[row][col].bottom + numPaint.getTextSize(), numPaint);
            }

        }

        mapButtom.draw(canvas);
    }

    @Override
    public void onClick(int x, int y) {
        if (mapButtom.getBounds().contains(x, y)) {
            game.setMainScreen();
        }
    }
}
