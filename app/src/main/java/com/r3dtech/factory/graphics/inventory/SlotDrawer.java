package com.r3dtech.factory.graphics.inventory;

import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;

import com.r3dtech.factory.graphics.DrawableCaches;
import com.r3dtech.factory.graphics.GenericDrawer;

/**
 * This class is used to draw a slot.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 23/01/2018.
 */

public class SlotDrawer extends GenericDrawer<SlotBundle> {
    private static final int SLOT_OUTLINE_WIDTH = 8;
    private static final int NUM_TEXT_SIZE = 48;
    private static final int TEXT_SIZE = 32;

    private Paint slotPaint = new Paint();
    private Paint emptySlotPaint = new Paint();
    private Paint slotOutlinePaint = new Paint();
    private Paint selectedSlotOutlinePaint = new Paint();

    private Paint numPaint = new Paint(Paint.FAKE_BOLD_TEXT_FLAG);
    private Paint textPaint = new Paint(Paint.FAKE_BOLD_TEXT_FLAG);

    public SlotDrawer(AssetManager assets) {
        loadPaints(assets);
    }

    private void loadPaints(AssetManager assets) {
        emptySlotPaint.setColor(Color.rgb(0xa0, 0xa0, 0xa0));
        slotPaint.setColor(Color.WHITE);
        slotOutlinePaint.setColor(Color.rgb(0x11, 0x11, 0x11));
        slotOutlinePaint.setStyle(Paint.Style.STROKE);
        slotOutlinePaint.setStrokeWidth(SLOT_OUTLINE_WIDTH);
        selectedSlotOutlinePaint.setColor(Color.YELLOW);
        selectedSlotOutlinePaint.setStyle(Paint.Style.STROKE);
        selectedSlotOutlinePaint.setStrokeWidth(SLOT_OUTLINE_WIDTH);

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
    }

    @Override
    public void draw(Canvas canvas, SlotBundle object) {
        setBounds(object.slot.getBounds());
        if (object.isSelected) {
            canvas.drawRoundRect(new RectF(bounds), bounds.height()/4, bounds.height()/4, selectedSlotOutlinePaint);
        } else {
            canvas.drawRoundRect(new RectF(bounds), bounds.height() / 4, bounds.height() / 4, slotOutlinePaint);
        }
        if (object.isFull) {
            canvas.drawRoundRect(new RectF(bounds), bounds.height() / 4, bounds.height() / 4, slotPaint);
        } else {
            canvas.drawRoundRect(new RectF(bounds), bounds.height() / 4, bounds.height() / 4, emptySlotPaint);
        }
        if (!object.itemStack.isEmpty()) {
            Drawable icon = DrawableCaches.getItemIcon(object.itemStack.getItem().toInt());
            icon.setBounds(bounds);
            icon.draw(canvas);

            switch (object.textOrNum) {
                case NUM:
                    canvas.drawText(Integer.toString(object.itemStack.getAmount()),
                            object.slot.centerX(), object.slot.bottom()+numPaint.getTextSize(),
                            numPaint);
                    break;
                case TEXT:
                    canvas.drawText(object.itemStack.getItem().getName(),
                            object.slot.centerX(), object.slot.bottom()+textPaint.getTextSize(),
                            textPaint);
                    break;
            }
        }
    }
}
