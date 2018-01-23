package com.r3dtech.factory.Machines.graphics;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.r3dtech.factory.framework.Game;
import com.r3dtech.factory.inventory.Inventory;
import com.r3dtech.factory.inventory.graphics.InventoryScreen;

/**
 * This class is a general screen for a machine.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 22/01/2018.
 */

public class MachineScreen extends InventoryScreen {
    protected static final int SLOT_BEGIN_TOP = 64;
    protected static final int SLOT_BEGIN_LEFT = 64;

    private class EmptyDrawable extends Drawable {
        public EmptyDrawable() {
            setBounds(new Rect(0, 0, 0, 0));
        }

        @Override
        public void draw(@NonNull Canvas canvas) {

        }

        @Override
        public void setAlpha(int i) {

        }

        @Override
        public void setColorFilter(@Nullable ColorFilter colorFilter) {

        }

        @Override
        public int getOpacity() {
            return PixelFormat.TRANSPARENT;
        }
    }

    public MachineScreen(Bitmap frameBuffer, Inventory inventory, Game game, AssetManager assets) {
        super(frameBuffer, inventory, game, assets);
        inventoryButton = new EmptyDrawable();
        craftingButton = new EmptyDrawable();
    }

    @Override
    protected Rect getFirstSlot() {
        Rect firstSlot = new Rect(SLOT_BEGIN_LEFT, SLOT_BEGIN_TOP,
                SLOT_BEGIN_LEFT+SLOT_SIZE, SLOT_BEGIN_TOP+SLOT_SIZE);
        firstSlot.offset(0, canvas.getHeight()/2);
        return firstSlot;
    }

    @Override
    public void onClick(int x, int y) {
        super.onClick(x, y);
        for (int i = 0; i < inventory.getItemNum(); i++) {
            int row = i/SLOTS_PER_ROW;
            int col = i%SLOTS_PER_ROW;
            if (bounds[row][col].contains(x, y)) {
                selected = i;
            }
        }
    }
}
