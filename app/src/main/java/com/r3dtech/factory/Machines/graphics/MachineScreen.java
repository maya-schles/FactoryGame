package com.r3dtech.factory.Machines.graphics;

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
 * This class is a general screen for a machine.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 22/01/2018.
 */

public class MachineScreen implements GameScreen {
    private static final int MAP_BUTTON_WIDTH = 128;
    private static final int MAP_BUTTON_HEIGHT = 128;
    private static final int MAP_BUTTON_RIGHT_DIST = 16;
    private static final int SLOT_OUTLINE_WIDTH = 8;
    private static final int NUM_TEXT_SIZE = 48;
    private static final int TEXT_SIZE = 32;

    protected Game game;
    protected Canvas canvas;
    private Drawable mapButtom;

    protected GameItemDrawableCache drawableCache = new GameItemDrawableCache();

    private Paint slotPaint = new Paint();
    private Paint emptySlotPaint = new Paint();
    private Paint slotOutlinePaint = new Paint();

    protected Paint numPaint = new Paint(Paint.FAKE_BOLD_TEXT_FLAG);
    protected Paint textPaint = new Paint(Paint.FAKE_BOLD_TEXT_FLAG);

    public MachineScreen(Bitmap frameBuffer, Game game, AssetManager assets) {
        this.canvas = new Canvas(frameBuffer);
        this.game = game;

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

    @Override
    public void paint() {
        canvas.drawColor(Color.GRAY);
        mapButtom.draw(canvas);
    }

    @Override
    public void onClick(int x, int y) {
        if (mapButtom.getBounds().contains(x, y)) {
            game.setMainScreen();
        }
    }
}
