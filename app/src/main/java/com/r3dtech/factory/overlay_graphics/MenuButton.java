package com.r3dtech.factory.overlay_graphics;

import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * This class is a drawable of a menu button.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 21/01/2018.
 */

public class MenuButton extends Drawable {
    private static final int TEXT_SIZE = 64;
    private static final int OUTLINE_WIDTH = 8;
    private static final int SIDE_BUFFER = 4;
    private String text;
    private Paint textPaint = new Paint(Paint.FAKE_BOLD_TEXT_FLAG);
    private Paint outlinePaint = new Paint();

    public MenuButton(String text, boolean isSelected, AssetManager assets) {
        this.text = text;
        int color = isSelected?Color.YELLOW:Color.BLACK;
        outlinePaint.setColor(color);
        outlinePaint.setStyle(Paint.Style.STROKE);
        outlinePaint.setStrokeWidth(OUTLINE_WIDTH);
        textPaint.setColor(color);
        textPaint.setTextSize(TEXT_SIZE);
        Typeface textFont = Typeface.createFromAsset(assets, "text_font.ttf");
        textPaint.setTypeface(textFont);

    }
    @Override
    public void draw(@NonNull Canvas canvas) {
        Rect bounds = getBounds();
        Rect outlineRect = new Rect(
                bounds.left+OUTLINE_WIDTH/2+SIDE_BUFFER,
                bounds.top+OUTLINE_WIDTH/2+SIDE_BUFFER,
                bounds.right-OUTLINE_WIDTH/2-SIDE_BUFFER,
                bounds.bottom-OUTLINE_WIDTH/2-SIDE_BUFFER);
        canvas.drawRect(outlineRect, outlinePaint);
        canvas.drawText(text, outlineRect.left, bounds.bottom-(bounds.height()-textPaint.getTextSize())/2, textPaint);
    }

    @Override
    public void setAlpha(int i) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }
}
