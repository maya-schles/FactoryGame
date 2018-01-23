package com.r3dtech.factory.inventory.graphics;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;

import com.r3dtech.factory.framework.Game;
import com.r3dtech.factory.inventory.GameItem;
import com.r3dtech.factory.inventory.Inventory;

/**
 * This class is used as a screen for the inventory;
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 19/01/2018.
 */

public class InventoryScreen extends InventoryMenuScreen{
    protected Rect[][] bounds;
    protected Inventory inventory;
    protected int selected = -1;


    public InventoryScreen(Bitmap frameBuffer, Inventory inventory, Game game, AssetManager assets) {
        super(frameBuffer, game, assets, Tab.INVENTORY);
        this.game = game;
        this.inventory = inventory;
        bounds = new Rect[(int) Math.ceil(inventory.getSlotsMax()/SLOTS_PER_ROW)][SLOTS_PER_ROW];
        bounds = generateBounds(inventory.getSlotsMax());
    }

    @Override
    public void paint() {
        super.paint();
        for (int i = 0; i < inventory.getSlotsMax(); i++) {
            int row = i/SLOTS_PER_ROW;
            int col = i%SLOTS_PER_ROW;
            GameItem item = inventory.getItem(i);
            drawSlot(bounds[row][col], i < inventory.getItemNum(), item, i == selected);
            if (item != null) {
                canvas.drawText(Integer.toString(inventory.getAmount(item)),
                        bounds[row][col].centerX(), bounds[row][col].bottom + numPaint.getTextSize(), numPaint);
            }

        }
    }

    @Override
    protected Rect getFirstSlot() {
        return FIRST_SLOT;
    }
}
