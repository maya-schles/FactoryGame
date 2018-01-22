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
    private Rect[][] bounds;
    private Inventory inventory;


    public InventoryScreen(Bitmap frameBuffer, Inventory inventory, Game game, AssetManager assets) {
        super(frameBuffer, game, assets, Tab.INVENTORY);
        this.game = game;
        this.inventory = inventory;
        bounds = new Rect[(int) Math.ceil(GameItem.values().length/(float) SLOTS_PER_ROW)][SLOTS_PER_ROW];
        bounds = generateBounds(FIRST_SLOT, GameItem.values().length);
    }

    @Override
    public void paint() {
        super.paint();
        for (int i = 0; i < inventory.getItemNum(); i++) {
            int row = i/SLOTS_PER_ROW;
            int col = i%SLOTS_PER_ROW;
            GameItem item = inventory.getItem(i);
            drawSlot(bounds[row][col], true, item);
            canvas.drawText(Integer.toString(inventory.getAmount(item)),
                    bounds[row][col].centerX(), bounds[row][col].bottom + numPaint.getTextSize(), numPaint);

        }
    }
}
