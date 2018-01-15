package com.r3dtech.factory.tile_map;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

import com.r3dtech.factory.FullscreenActivity;
import com.r3dtech.factory.R;

import java.util.Arrays;

/**
 * An enum for the possible types of world tiles in the game map.
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 13/01/2018.
 */

public enum TileType {
    GRASS(R.drawable.grass),
    DIRT(R.drawable.dirt),
    EMPTY(R.drawable.empty);

    private BitmapDrawable bitmap;
    private TileType(int id) {
        Resources res = FullscreenActivity.context.getResources();
        Bitmap btmp = BitmapFactory.decodeResource(res, id);
        bitmap = new BitmapDrawable(res, btmp);
    }

    public BitmapDrawable bitmapDrawable() {
        return bitmap;
    }

    public static TileType fromInt(int i) {
        return values()[i];
    }

    public static TileType[] fromInt(int[] arr) {
        TileType[] res = new TileType[arr.length];
        for (int i = 0; i< arr.length; i++) {
            res[i] = fromInt(arr[i]);
        }
        return res;
    }

    public static TileType[][] fromInt(int[][] arr) {
        TileType[][] res = new TileType[arr.length][arr[0].length];
        for (int i = 0; i< arr.length; i++) {
            res[i] = fromInt(arr[i]);
        }
        return res;
    }

    public int toInt() {
        return Arrays.asList(values()).indexOf(this);
    }
}
